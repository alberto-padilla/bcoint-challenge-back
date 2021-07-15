package cl.binter.challenge.bcointchallengeback.wordcounter.controller;

import cl.binter.challenge.bcointchallengeback.exception.NotFoundException;
import cl.binter.challenge.bcointchallengeback.wordcounter.domain.Ranking;
import cl.binter.challenge.bcointchallengeback.wordcounter.domain.TextComplete;
import cl.binter.challenge.bcointchallengeback.wordcounter.service.WordCounterService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class WordCounterController {

    private final WordCounterService WordCounterService;

    public WordCounterController(WordCounterService WordCounterService) {
        this.WordCounterService = WordCounterService;
    }

    @GetMapping("/text")
    public ResponseEntity<TextComplete> getText(){
        log.info("GET /text");
        return new ResponseEntity<>(WordCounterService.getTextComplete(
                WordCounterService.getText().orElseThrow(NotFoundException::new)
        ), HttpStatus.OK);
    }

    @GetMapping("/text/{id}")
    public ResponseEntity<TextComplete> getText(@PathVariable Integer id){
        log.info("GET /text/{}", id);
        return new ResponseEntity<>(WordCounterService.getTextComplete(
                WordCounterService.getText(id).orElseThrow(NotFoundException::new)
        ), HttpStatus.OK);
    }

    @GetMapping("/text/{id}/ranking")
    public ResponseEntity<Ranking> getRanking(@PathVariable Integer id){
        log.info("GET /text/{}/ranking", id);
        return new ResponseEntity<>(WordCounterService.getRanking(
                WordCounterService.getTextComplete(
                WordCounterService.getText(id).orElseThrow(NotFoundException::new)
                )
        ), HttpStatus.OK);
    }

}
