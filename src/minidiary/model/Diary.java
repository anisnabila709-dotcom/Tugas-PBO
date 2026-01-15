package minidiary.model;

import java.time.LocalDate;

public class Diary {

    private int id;
    private int userId;
    private String title;
    private LocalDate date;
    private String content;

    public Diary() {
    }

    public Diary(int id, int userId, String title, LocalDate date, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.date = date;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
