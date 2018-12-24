package com.company;

public class Document {
    private String studentName;
    private String documentName;
    private int documentPages;

    public Document(String studentName, String documentName, int documentPages){
        this.studentName = studentName;
        this.documentName = documentName;
        this.documentPages = documentPages;
    }

    public int getDocumentPages() {
        return documentPages;
    }

    public void setDocumentPages(int documentPages) {
        this.documentPages = documentPages;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}
