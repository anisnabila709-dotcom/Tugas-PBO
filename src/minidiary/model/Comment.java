package minidiary.model;

public class Comment {

    private int id;
    private int userId;
    private int diaryId;
    private String content;
    private String username; 

    public Comment() {
    }

    public Comment(int id, int userId, int diaryId, String content, String username) {
        this.id = id;
        this.userId = userId;
        this.diaryId = diaryId;
        this.content = content;
        this.username = username;
    }

    // ===== GETTER & SETTER =====
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

    public String getUsername() {   
        return username;
    }
    public void setUsername(String username) {  
        this.username = username;
    }
}
