package com.company;

public class LaserPrinter implements ServicePrinter{

    //region Members
    private String printerName;
    private int printerID;
    private int printerPaperLevel;
    private int tonerLevel;
    private int documentsPrinted;
    private ThreadGroup studentGroup;
    //endregion

    //region Constructor
    LaserPrinter(String printerName, int printerID, int printerPaperLevel, int tonerLevel, int documentsPrinted, ThreadGroup studentGroup){
        this.printerName = printerName;
        this.printerID = printerID;
        this.printerPaperLevel = printerPaperLevel;
        this.tonerLevel = tonerLevel;
        this.documentsPrinted = documentsPrinted;
        this.studentGroup = studentGroup;
    }
    //endregion

    //region Class methods
    @Override
    public String toString() {
        return "[ PrinterID: " + printerID + printerName +
                ", Paper Level: " + printerPaperLevel +
                ". Toner Level: " + tonerLevel +
                ". Documents Printed: " + documentsPrinted +
                " ]";
    }

    public synchronized void printDocument(Document document){
        while ((printerPaperLevel < document.getDocumentPages()) || (tonerLevel < document.getDocumentPages())){
            System.out.println("Not enough resources");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        documentsPrinted++;
        printerPaperLevel = printerPaperLevel - document.getDocumentPages();
        tonerLevel = tonerLevel - document.getDocumentPages();

        System.out.println("Document " + document.getDocumentName() + " printed for student " + document.getStudentName() + ". Document number " + documentsPrinted);

        notifyAll();
    }

    public synchronized void refillPaper() {
        while (printerPaperLevel > 200 && studentGroup.activeCount() > 0) {
            System.out.println("Paper not replaced, paper level: " + printerPaperLevel);
            try {
                wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (studentGroup.activeCount() > 0){
            System.out.println("Printer paper level: " + printerPaperLevel + ". Student count " + studentGroup.activeCount() + ". Paper replaced");
            printerPaperLevel = printerPaperLevel + 50;
        }

        notifyAll();
    }

    public synchronized void replaceTonerCartridge(){
        while (tonerLevel > 10 && studentGroup.activeCount() > 0) {
            System.out.println("Toner not replaced. Toner level: " + tonerLevel);
            try {
                wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (studentGroup.activeCount() > 0) {
            System.out.println("Printer toner level: " + tonerLevel + ". Student count " + studentGroup.activeCount() + ". Toner replaced");
            tonerLevel = 500;
        }

        notifyAll();
    }
    //endregion

}
