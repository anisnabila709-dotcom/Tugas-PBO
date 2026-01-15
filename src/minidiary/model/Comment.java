package minidiary.model;

public class Comment {

    private int id;
    private int userId;
    private int diaryId;
    private int paragraphId; // komentar per paragraf
    private String content;

    public Comment() {
    }

    public Comment(int id, int userId, int diaryId, int paragraphId, String content) {
        this.id = id;
        this.userId = userId;
        this.diaryId = diaryId;
        this.paragraphId = paragraphId;
        this.content = content;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public int getParagraphId() {
        return paragraphId;
    }

    public void setParagraphId(int paragraphId) {
        this.paragraphId = paragraphId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
