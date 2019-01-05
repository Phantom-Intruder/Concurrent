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
    LaserPrinter(String printerName, int printerID, int documentsPrinted, ThreadGroup studentGroup){
        this.printerName = printerName;
        this.printerID = printerID;
        this.printerPaperLevel = Full_Paper_Tray;
        this.tonerLevel = Full_Toner_Level;
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
        while ((printerPaperLevel < document.getNumberOfPages()) || (tonerLevel < document.getNumberOfPages())){
            System.out.println("Not enough resources");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        documentsPrinted++;
        printerPaperLevel = printerPaperLevel - document.getNumberOfPages();
        tonerLevel = tonerLevel - document.getNumberOfPages();

        System.out.println("Document " + document.getDocumentName() + " printed for student " + document.getUserID() + ". Document number " + documentsPrinted);

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
            printerPaperLevel = printerPaperLevel + SheetsPerPack;
        }

        notifyAll();
    }

    public synchronized void replaceTonerCartridge(){
        while (tonerLevel > Minimum_Toner_Level && studentGroup.activeCount() > 0) {
            System.out.println("Toner not replaced. Toner level: " + tonerLevel);
            try {
                wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (studentGroup.activeCount() > 0) {
            System.out.println("Printer toner level: " + tonerLevel + ". Student count " + studentGroup.activeCount() + ". Toner replaced");
            tonerLevel = PagesPerTonerCartridge;
        }

        notifyAll();
    }
    //endregion

}
