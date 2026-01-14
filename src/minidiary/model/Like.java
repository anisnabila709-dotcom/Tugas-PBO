package minidiary.model;

public class Like {

    private int id;
    private int userId;
    private int diaryId;

    public Like() {
    }

    public Like(int id, int userId, int diaryId) {
        this.id = id;
        this.userId = userId;
        this.diaryId = diaryId;
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

    public void setUserId(int us
