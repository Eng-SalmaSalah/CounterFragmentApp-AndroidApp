package com.salma.mydiary.model;
public class Note {
    private String noteName;
    private String noteBody;

    public Note() {
    }

    public Note(String noteName, String noteBody) {
        this.noteName = noteName;
        this.noteBody = noteBody;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getNoteBody() {
        return noteBody;
    }
}
