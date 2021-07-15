package cl.binter.challenge.bcointchallengeback.wordcounter.service;

import cl.binter.challenge.bcointchallengeback.wordcounter.domain.TextComplete;
import cl.binter.challenge.bcointchallengeback.wordcounter.model.TextItem;
import cl.binter.challenge.bcointchallengeback.wordcounter.domain.Ranking;

import java.util.Optional;

public interface WordCounterService {

    Optional<TextItem> getText();
    Optional<TextItem> getText(Integer id);
    Optional<TextItem> getText(Integer id, Integer page);
    TextComplete getTextComplete(TextItem textItem);
    Ranking getRanking(TextComplete textComplete);
}
