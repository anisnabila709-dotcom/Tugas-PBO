package minidiary.model;

public class Paragraph {

    private int id;
    private int diaryId;
    private String content;

    public Paragraph() {
    }

    public Paragraph(int id, int diaryId, String content) {
        this.id = id;
        this.diaryId = diaryId;
        this.content = content;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
