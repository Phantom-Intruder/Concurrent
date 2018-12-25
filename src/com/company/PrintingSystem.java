package com.company;

public class PrintingSystem {

    public static void main(String[] args) {

        //region ThreadGroups
        ThreadGroup studentThreadGroup = new ThreadGroup("Student_ThreadGroup");
        ThreadGroup technicianThreadGroup = new ThreadGroup("Technician_ThreadGroup");
        System.out.println("Thread groups created");
        //endregion

        //region Printer
        LaserPrinter laserPrinter = new LaserPrinter("printer1",
                1,
                250,
                500,
                0,
                studentThreadGroup);
        System.out.println("Printer object created");
        //endregion

        //region Students
        Student student1 = new Student(studentThreadGroup,
                "student1Thread",
                "JoeBloggs",
                laserPrinter);

        Student student2 = new Student(studentThreadGroup,
                "student2Thread",
                "SueBloggs",
                laserPrinter);

        Student student3 = new Student(studentThreadGroup,
                "student3Thread",
                "MoeBloggs",
                laserPrinter);

        Student student4 = new Student(studentThreadGroup,
                "student4Thread",
                "PoeBloggs",
                laserPrinter);

        System.out.println("Student threads created");
        //endregion

        //region Technicians
        PaperTechnician paperTechnician = new PaperTechnician(technicianThreadGroup,
                "paperTechnicianThread",
                "PaperBloggs",
                laserPrinter);

        TonerTechnician tonerTechnician = new TonerTechnician(technicianThreadGroup,
                "tonerTechnicianThread",
                "TonerBloggs",
                laserPrinter);

        System.out.println("Technician threads created");
        //endregion

        //region Starting threads
        student1.start();
        student2.start();
        student3.start();
        student4.start();
        System.out.println("Student threads started");

        tonerTechnician.start();
        paperTechnician.start();
        System.out.println("Technician threads started");
        //endregion

        //region Thread joining
        try {
            student1.join();
            student2.join();
            student3.join();
            student4.join();

            tonerTechnician.join();
            paperTechnician.join();
            System.out.println("Threads finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //endregion

        System.out.println(laserPrinter.toString());
    }

}
