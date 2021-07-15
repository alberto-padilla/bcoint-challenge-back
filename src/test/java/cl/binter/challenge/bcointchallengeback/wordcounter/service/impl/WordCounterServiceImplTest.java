package cl.binter.challenge.bcointchallengeback.wordcounter.service.impl;

import cl.binter.challenge.bcointchallengeback.wordcounter.domain.Ranking;
import cl.binter.challenge.bcointchallengeback.wordcounter.domain.TextComplete;
import cl.binter.challenge.bcointchallengeback.wordcounter.model.TextItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WordCounterServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WordCounterServiceImpl wordCounterService;

    TextItem randomText = TextItem.builder()
            .id(1)
            .page(1)
            .total_pages(2)
            .title("title")
            .text("th1$ i`s @ T#ext !@#$%^&*()_+{}")
            .build();

    @Before
    public void setUp(){
        when(restTemplate.getForEntity(
                Mockito.anyString(), Mockito.any()))
                .thenReturn(new ResponseEntity<>(randomText, HttpStatus.OK));
    }

    @Test
    public void whenGetTextReturnTheSame() {
        assertEquals(Optional.of(randomText), wordCounterService.getText());
        assertEquals(Optional.of(randomText), wordCounterService.getText(1));
        assertEquals(Optional.of(randomText), wordCounterService.getText(1, 1));
    }

    @Test
    public void whenGetCompleteTextThenConcatenateText() {
        TextComplete textComplete = wordCounterService.getTextComplete(wordCounterService.getText().get());
        assertEquals(randomText.getText().concat(randomText.getText()), textComplete.getText());
    }

    @Test
    public void WhenGeneratingRankingMostOccurrencesWordGoesFirst() {
        TextComplete textComplete = TextComplete.builder()
                .id(1)
                .title("")
                .text("uno dos dos tres tres tres")
                .build();
        Ranking ranking = wordCounterService.getRanking(textComplete);
        assertEquals("tres", ranking.getRanking().get(0).getWord());
        assertEquals("dos", ranking.getRanking().get(1).getWord());
        assertEquals("uno", ranking.getRanking().get(2).getWord());
    }
}