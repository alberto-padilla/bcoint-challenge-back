package cl.binter.challenge.backendapadilla.wordcounter.utils;

import cl.binter.challenge.backendapadilla.wordcounter.domain.RankingItem;

import java.util.Comparator;

public class RankingItemComparator implements Comparator<RankingItem> {

    @Override
    public int compare(RankingItem o1, RankingItem o2) {
        return o2.getOccurrences() - o1.getOccurrences();
    }
}
