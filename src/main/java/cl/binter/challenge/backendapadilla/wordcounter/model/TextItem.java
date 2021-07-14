package cl.binter.challenge.backendapadilla.wordcounter.model;

public class TextItem {
    private Integer id;
    private String title;
    private Integer page;
    private Integer total_pages;
    private String text;

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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TextItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", page='" + page + '\'' +
                ", total_pages=" + total_pages +
                ", text='" + text + '\'' +
                '}';
    }
}
