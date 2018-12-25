package com.company;

import java.util.Random;

public class PaperTechnician extends Thread{

    //region Members
    private String technicianName;
    private LaserPrinter printer;
    private ThreadGroup threadGroup;
    //endregion

    //region Constructor
    PaperTechnician(ThreadGroup threadGroup, String threadName, String technicianName, LaserPrinter printer){
        super(threadGroup, threadName);
        this.threadGroup = threadGroup;
        this.technicianName = technicianName;
        this.printer = printer;
    }
    //endregion

    //region Thread methods
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

        System.out.println("Technician "+ technicianName + "has finished.");
    }
    //endregion
}
