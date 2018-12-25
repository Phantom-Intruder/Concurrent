package com.company;

class Document {

    //region Members
    private String studentName;
    private String documentName;
    private int documentPages;
    //endregion

    //region Constructor
    Document(String studentName, String documentName, int documentPages){
        this.studentName = studentName;
        this.documentName = documentName;
        this.documentPages = documentPages;
    }
    //endregion

    //region Class methods
    int getDocumentPages() {
        return documentPages;
    }

    String getStudentName() {
        return studentName;
    }

    String getDocumentName() {
        return documentName;
    }
    //endregion

}
