package cl.binter.challenge.backendapadilla.wordcounter.service;

import cl.binter.challenge.backendapadilla.wordcounter.domain.TextComplete;
import cl.binter.challenge.backendapadilla.wordcounter.model.TextItem;
import cl.binter.challenge.backendapadilla.wordcounter.domain.Ranking;

import java.util.Optional;

public interface TextService {

    Optional<TextItem> getText();
    Optional<TextItem> getText(Integer id);
    Optional<TextItem> getText(Integer id, Integer page);
    TextComplete getTextComplete(TextItem textItem);
    Ranking generateRanking(TextComplete textComplete);
}
