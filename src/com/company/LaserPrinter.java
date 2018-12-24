package com.company;

public class LaserPrinter implements ServicePrinter {

    //region Members
    private String printerName;
    private int printerID;
    private int printerPaperLevel;
    private int tonerLevel;
    private int documentsPrinted;
    //endregion

    //region Constructor
    public LaserPrinter(String printerName, int printerID, int printerPaperLevel, int tonerLevel, int documentsPrinted){
        this.printerName = printerName;
        this.printerID = printerID;
        this.printerPaperLevel = printerPaperLevel;
        this.tonerLevel = tonerLevel;
        this.documentsPrinted = documentsPrinted;
    }
    //endregion

    //region Properties
    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public int getPrinterID() {
        return printerID;
    }

    public void setPrinterID(int printerID) {
        this.printerID = printerID;
    }

    public int getPrinterPaperLevel() {
        return printerPaperLevel;
    }

    public void setPrinterPaperLevel(int printerPaperLevel) {
        this.printerPaperLevel = printerPaperLevel;
    }

    public int getTonerLevel() {
        return tonerLevel;
    }

    public void setTonerLevel(int tonerLevel) {
        this.tonerLevel = tonerLevel;
    }

    public int getDocumentsPrinted() {
        return documentsPrinted;
    }

    public void setDocumentsPrinted(int documentsPrinted) {
        this.documentsPrinted = documentsPrinted;
    }
    //endregion

    //region Class methods
    @Override
    public String toString() {
        return "[ PrinterID: " + printerID +
                ", Paper Level: " + printerPaperLevel +
                ". Toner Level: " + tonerLevel +
                "Documents Printed: " + documentsPrinted ;
    }

    public synchronized void printDocument(Document document){
        while ((document.getDocumentPages() < printerPaperLevel) || (document.getDocumentPages() < tonerLevel)){
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

        System.out.println("Document printed");

        notifyAll();
    }

    public synchronized void refillPaper() {
        while (printerPaperLevel > 200) {
            System.out.println("Paper full");
            try {
                wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printerPaperLevel = printerPaperLevel + 50;

        notifyAll();
    }

    public synchronized void replaceTonerCartridge(){
        while (tonerLevel > 10) {
            System.out.println("Toner full");
            try {
                wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tonerLevel = 500;

        notifyAll();
    }
    //endregion
}
