package cl.binter.challenge.backendapadilla.wordcounter.domain;

public class RankingItem {
    private Integer position;
    private String word;
    private Integer occurrences;

    public RankingItem() {
        super();
    }

    public RankingItem(String word) {
        this.word = word;
        this.occurrences = 1;
    }

    public RankingItem(Integer position, String word, Integer occurrences) {
        this.position = position;
        this.word = word;
        this.occurrences = occurrences;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(Integer occurrences) {
        this.occurrences = occurrences;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "position=" + position +
                ", word='" + word + '\'' +
                ", occurrences='" + occurrences + '\'' +
                '}';
    }

}
