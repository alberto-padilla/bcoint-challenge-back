package cl.binter.challenge.backendapadilla.wordcounter.service.impl;

import cl.binter.challenge.backendapadilla.wordcounter.domain.TextComplete;
import cl.binter.challenge.backendapadilla.wordcounter.model.TextItem;
import cl.binter.challenge.backendapadilla.wordcounter.domain.Ranking;
import cl.binter.challenge.backendapadilla.wordcounter.domain.RankingItem;
import cl.binter.challenge.backendapadilla.wordcounter.service.TextService;
import cl.binter.challenge.backendapadilla.wordcounter.utils.RankingItemComparator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class TextServiceImpl implements TextService {

    private final RestTemplate restTemplate;

    public TextServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<TextItem> getText() {
        String resourceUrl = "http://localhost:8080/generator/word-counter/text";
        ResponseEntity<TextItem> responseEntity = restTemplate.getForEntity(resourceUrl, TextItem.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

    @Override
    public Optional<TextItem> getText(Integer id) {
        String resourceUrl = "http://localhost:8080/generator/word-counter/text?id=" + id + "&page=" + 1;
        ResponseEntity<TextItem> responseEntity = restTemplate.getForEntity(resourceUrl, TextItem.class);
        return Optional.ofNullable(responseEntity.getBody());
    }

    @Override
    public Optional<TextItem> getText(Integer id, Integer page) {
        String resourceUrl = "http://localhost:8080/generator/word-counter/text?id=" + id + "&page=" + page;
        ResponseEntity<TextItem> responseEntity = restTemplate.getForEntity(resourceUrl, TextItem.class);
        return Optional.ofNullable(responseEntity.getBody());
    }


    @Override
    public TextComplete getTextComplete(TextItem textItem) {
        TextComplete textComplete = new TextComplete();
        textComplete.setId(textItem.getId());
        textComplete.setTitle(textItem.getTitle());
        textComplete.setText(textItem.getText());

        List<TextItem> textPages = new ArrayList<>();
        textPages.add(textItem);
        if (textItem.getTotal_pages() > 1) {
            for (int i = textItem.getPage() + 1; i <= textItem.getTotal_pages(); i++) {
                Optional<TextItem> t = getText(textItem.getId(), i);
                textComplete.setText(textComplete.getText().concat("\r\n").concat(t.get().getText()));
                textPages.add(t.get());
            }
        }

        textComplete.setTextItemList(textPages);

        return textComplete;
    }

    @Override
    public Ranking generateRanking(TextComplete textComplete) {
        Ranking ranking = new Ranking(textComplete.getId(), textComplete.getTitle());
        HashMap<String, RankingItem> wordsMap = new HashMap<>();

        for (TextItem textItem : textComplete.getTextItemList()) {
            String cleanText = textItem.getText().replaceAll("[,.;:?¿]", "");
            String[] words = cleanText.split(" ");

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

        AtomicInteger position = new AtomicInteger();
        List<RankingItem> rankingItemList = wordsMap.values()
                .stream()
                .sorted(new RankingItemComparator())
                .peek(ri -> ri.setPosition(position.incrementAndGet()))
                .collect(Collectors.toList());
        ranking.setRanking(rankingItemList);

        return ranking;
    }
}
