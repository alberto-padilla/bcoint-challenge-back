package cl.binter.challenge.bcointchallengeback.wordcounter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextItem {
    private Integer id;
    private String title;
    private Integer page;
    private Integer total_pages;
    private String text;
}
