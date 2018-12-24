package com.company;

import java.util.Random;

public class Student extends Thread{

    private String studentName;
    private LaserPrinter printer;

    public Student(ThreadGroup threadGroup, String threadName, String studentName, LaserPrinter printer){
        super(threadGroup, threadName);
        this.studentName = studentName;
        this.printer = printer;
    }

    @Override
    public void run() {
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            Document document = new Document(this.studentName, "cwk" + i, rand.nextInt(50) + 1);
            this.printer.printDocument(document);
            System.out.println(this);

            try {
                sleep(rand.nextInt(5000) + 1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }

        System.out.println("Student "+ studentName + "has finished printing");
    }
}
