package cl.binter.challenge.bcointchallengeback.wordcounter.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankingItem {
    private Integer position;
    private String word;
    private Integer occurrences;

    public RankingItem(String word) {
        this.word = word;
        this.occurrences = 1;
    }
}
