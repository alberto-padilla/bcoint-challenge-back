package cl.binter.challenge.backendapadilla.wordcounter.controller;

import cl.binter.challenge.backendapadilla.exception.NotFoundException;
import cl.binter.challenge.backendapadilla.wordcounter.domain.Ranking;
import cl.binter.challenge.backendapadilla.wordcounter.domain.TextComplete;
import cl.binter.challenge.backendapadilla.wordcounter.service.TextService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {

    private final TextService textService;

    public TextController(TextService textService) {
        this.textService = textService;
    }

    @GetMapping("/text/{id}")
    public ResponseEntity<TextComplete> getTextComplete(@PathVariable(required = false) Integer id){
        return new ResponseEntity<>(textService.getTextComplete(
                textService.getText(id).orElseThrow(NotFoundException::new)
        ), HttpStatus.OK);
    }

    @GetMapping("/text/{id}/ranking")
    public ResponseEntity<Ranking> getRanking(@PathVariable Integer id){
        return new ResponseEntity<>(textService.generateRanking(
                textService.getTextComplete(
                textService.getText(id).orElseThrow(NotFoundException::new)
                )
        ), HttpStatus.OK);
    }

}
