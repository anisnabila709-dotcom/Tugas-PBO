package minidiary.model;

public class Comment {

    private int id;
    private int userId;
    private int diaryId;
    private String content;

    public Comment() {
    }

    public Comment(int id, int userId, int diaryId, String content) {
        this.id = id;
        this.userId = userId;
        this.diaryId = diaryId;
        this.content = content;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
