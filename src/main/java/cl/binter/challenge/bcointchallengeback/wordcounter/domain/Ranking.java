package cl.binter.challenge.bcointchallengeback.wordcounter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Ranking {
    @NonNull
    private Integer id;
    @NonNull
    private String title;
    private List<RankingItem> ranking;
}
