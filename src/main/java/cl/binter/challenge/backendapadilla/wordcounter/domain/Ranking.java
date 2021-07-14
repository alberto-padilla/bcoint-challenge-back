package cl.binter.challenge.backendapadilla.wordcounter.domain;

import java.util.List;

public class Ranking {
    private Integer id;
    private String title;
    private List<RankingItem> ranking;

    public Ranking(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RankingItem> getRanking() {
        return ranking;
    }

    public void setRanking(List<RankingItem> ranking) {
        this.ranking = ranking;
    }
}
