package com.company;

import java.util.Random;

public class PaperTechnician extends Thread{

    private String technicianName;
    private ServicePrinter printer;

    public PaperTechnician(ThreadGroup threadGroup, String threadName, String technicianName, ServicePrinter printer){
        super(threadGroup, threadName);
        this.technicianName = technicianName;
        this.printer = printer;
    }

    @Override
    public void run() {
        Random rand = new Random();

        for (int i = 0; i < 3; i++) {
            printer.refillPaper();

            try {
                sleep(rand.nextInt(5000) + 1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }

        System.out.println("Technician "+ technicianName + "has finished refilling paper");
    }
}
