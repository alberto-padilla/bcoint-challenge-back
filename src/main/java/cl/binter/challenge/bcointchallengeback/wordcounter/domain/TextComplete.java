package cl.binter.challenge.bcointchallengeback.wordcounter.domain;

import cl.binter.challenge.bcointchallengeback.wordcounter.model.TextItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextComplete {
    private Integer id;
    private String title;
    private String text;
    @JsonIgnore
    private String cleanText;
    private List<TextItem> textItemList;
}
