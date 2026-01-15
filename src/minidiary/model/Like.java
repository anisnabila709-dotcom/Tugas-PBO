package minidiary.model;

/**
 * Model Like
 */
public class Like {

    private int id;
    private int diaryId;
    private int userId;

    // constructor kosong
    public Like() {
    }

    // constructor lengkap
    public Like(int id, int diaryId, int userId) {
        this.id = id;
        this.diaryId = diaryId;
        this.userId = userId;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
