package com.company;

public class Document {
    private String studentName;
    private String documentName;
    private int documentPages;

    Document(String studentName, String documentName, int documentPages){
        this.studentName = studentName;
        this.documentName = documentName;
        this.documentPages = documentPages;
    }

    int getDocumentPages() {
        return documentPages;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDocumentName() {
        return documentName;
    }
}
