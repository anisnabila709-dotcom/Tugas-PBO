package minidiary.enums;

public enum DiaryMode {

    WRITE("Write Diary"),
    READ("Read Diary"),
    EDIT("Edit Diary");

    private final String title;

    DiaryMode(String title) {
        this.title = title;
    }

    // dipakai oleh View untuk judul window
    public String getTitle() {
        return title;
    }

    // aksi untuk cek behavior
    public boolean isEditable() {
        return this == WRITE || this == EDIT;
    }

    public boolean isReadOnly() {
        return this == READ;
    }
}
