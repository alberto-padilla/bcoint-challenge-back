package cl.binter.challenge.backendapadilla.wordcounter.domain;

import cl.binter.challenge.backendapadilla.wordcounter.model.TextItem;

import java.util.List;

public class TextComplete {

    private Integer id;
    private String title;
    private String text;
    private List<TextItem> textItemList;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TextItem> getTextItemList() {
        return textItemList;
    }

    public void setTextItemList(List<TextItem> textItemList) {
        this.textItemList = textItemList;
    }
}
