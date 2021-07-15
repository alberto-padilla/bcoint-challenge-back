package cl.binter.challenge.bcointchallengeback.wordcounter.controller;

import cl.binter.challenge.bcointchallengeback.exception.NotFoundException;
import cl.binter.challenge.bcointchallengeback.wordcounter.domain.Ranking;
import cl.binter.challenge.bcointchallengeback.wordcounter.domain.TextComplete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class WordCounterController {

    private final cl.binter.challenge.bcointchallengeback.wordcounter.service.WordCounterService WordCounterService;

    public WordCounterController(cl.binter.challenge.bcointchallengeback.wordcounter.service.WordCounterService WordCounterService) {
        this.WordCounterService = WordCounterService;
    }

    @GetMapping("/text")
    public ResponseEntity<TextComplete> getText(){
        return new ResponseEntity<>(WordCounterService.getTextComplete(
                WordCounterService.getText().orElseThrow(NotFoundException::new)
        ), HttpStatus.OK);
    }

    @GetMapping("/text/{id}")
    public ResponseEntity<TextComplete> getText(@PathVariable Integer id){
        return new ResponseEntity<>(WordCounterService.getTextComplete(
                WordCounterService.getText(id).orElseThrow(NotFoundException::new)
        ), HttpStatus.OK);
    }

    @GetMapping("/text/{id}/ranking")
    public ResponseEntity<Ranking> getRanking(@PathVariable Integer id){
        return new ResponseEntity<>(WordCounterService.getRanking(
                WordCounterService.getTextComplete(
                WordCounterService.getText(id).orElseThrow(NotFoundException::new)
                )
        ), HttpStatus.OK);
    }

}
