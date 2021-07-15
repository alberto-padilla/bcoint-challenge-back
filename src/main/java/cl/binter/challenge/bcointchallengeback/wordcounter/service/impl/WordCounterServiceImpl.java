package cl.binter.challenge.bcointchallengeback.wordcounter.service.impl;

import cl.binter.challenge.bcointchallengeback.wordcounter.domain.TextComplete;
import cl.binter.challenge.bcointchallengeback.wordcounter.model.TextItem;
import cl.binter.challenge.bcointchallengeback.wordcounter.domain.Ranking;
import cl.binter.challenge.bcointchallengeback.wordcounter.domain.RankingItem;
import cl.binter.challenge.bcointchallengeback.wordcounter.service.WordCounterService;
import cl.binter.challenge.bcointchallengeback.wordcounter.utils.RankingItemComparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WordCounterServiceImpl implements WordCounterService {

    public static final String REGEX_SPECIAL_CHART = "[,.;:?¿]";
    public static final String CHAR_NEW_LINE = "\\n";

    @Value("${api.generator.wordcounter.path}")
    private String API_GENERATOR_WORDCOUNTER_PATH;

    @Value("${api.generator.wordcounter.resource.text.path}")
    private String API_GENERATOR_WORDCOUNTER_RESOURCE_TEXT_PATH;

    private final RestTemplate restTemplate;

    public WordCounterServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<TextItem> getText() {
        log.debug("Getting random text");
        String resourceUrl = String.format("%s%s", API_GENERATOR_WORDCOUNTER_PATH, API_GENERATOR_WORDCOUNTER_RESOURCE_TEXT_PATH);
        return callTextApi(resourceUrl);
    }

    @Override
    public Optional<TextItem> getText(Integer id) {
        log.debug("Getting text with id {}", id);
        String resourceUrl = String.format("%s%s?id=%d&page=%d", API_GENERATOR_WORDCOUNTER_PATH, API_GENERATOR_WORDCOUNTER_RESOURCE_TEXT_PATH, id, 1);
        return callTextApi(resourceUrl);
    }

    @Override
    public Optional<TextItem> getText(Integer id, Integer page) {
        log.debug("Getting text with id {} and page {}", id, page);
        String resourceUrl = String.format("%s%s?id=%d&page=%d", API_GENERATOR_WORDCOUNTER_PATH, API_GENERATOR_WORDCOUNTER_RESOURCE_TEXT_PATH, id, page);
        return callTextApi(resourceUrl);
    }

    private Optional<TextItem> callTextApi(String resourceUrl) {
        return Optional.ofNullable(restTemplate
                .getForEntity(resourceUrl, TextItem.class).getBody());
    }


    @Override
    public TextComplete getTextComplete(TextItem textItem) {
        log.debug("Getting full text for id {}", textItem.getId());
        StringBuilder strBuilder = new StringBuilder(textItem.getText());

        List<TextItem> textPages = new ArrayList<>();
        textPages.add(textItem);

        if (textItem.getTotal_pages() > 1) {
            for (int i = textItem.getPage() + 1; i <= textItem.getTotal_pages(); i++) {
                Optional<TextItem> t = getText(textItem.getId(), i);
                strBuilder.append(t.get().getText());
                textPages.add(t.get());
            }
        }

        return TextComplete.builder()
                .id(textItem.getId())
                .title(textItem.getTitle())
                .text(strBuilder.toString())
                .textItemList(textPages)
                .build();
    }

    @Override
    public Ranking getRanking(TextComplete textComplete) {
        log.debug("Getting Rank for text with id {}", textComplete.getId());
        HashMap<String, RankingItem> wordsMap = new HashMap<>();
        fillWordsMap(textComplete, wordsMap);

        return Ranking.builder()
                .id(textComplete.getId())
                .title(textComplete.getTitle())
                .ranking(definePositionAndOrderRanking(wordsMap))
                .build();
    }

    private List<RankingItem> definePositionAndOrderRanking(HashMap<String, RankingItem> wordsMap) {
        AtomicInteger position = new AtomicInteger();
        return wordsMap.values()
                .stream()
                .sorted(new RankingItemComparator())
                .peek(ri -> ri.setPosition(position.incrementAndGet()))
                .collect(Collectors.toList());
    }

    private void fillWordsMap(TextComplete textComplete, HashMap<String, RankingItem> wordsMap) {
        String[] words = cleanText(textComplete.getText()).split(" ");

        for (String strWord : words) {
            String key = strWord.toUpperCase();
            RankingItem rankingItem = wordsMap.get(key);

            if (rankingItem == null) {
                rankingItem = new RankingItem(strWord);
            } else {
                rankingItem.setOccurrences(rankingItem.getOccurrences() + 1);
            }

            wordsMap.put(key, rankingItem);
        }
    }

    private String cleanText(String text) {
        String cleanText = text.replaceAll(REGEX_SPECIAL_CHART, "");
        return cleanText.replaceAll(CHAR_NEW_LINE, " ");
    }
}
