package com.company;

import java.util.Random;

public class Student extends Thread{

    //region Members
    private String studentName;
    private LaserPrinter printer;
    private ThreadGroup threadGroup;
    //endregion

    //region Constructor
    Student(ThreadGroup threadGroup, String threadName, String studentName, LaserPrinter printer){
        super(threadGroup, threadName);
        this.threadGroup = threadGroup;
        this.studentName = studentName;
        this.printer = printer;
    }
    //endregion

    //region Thread methods
    @Override
    public void run() {
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            Document document = new Document(this.studentName, "cwk" + i, rand.nextInt(20) + 1);
            this.printer.printDocument(document);

            try {
                sleep(rand.nextInt(5000) + 1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }

        System.out.println("Student "+ studentName + " has finished printing.");
    }
    //endregion
}
