package com.company;

public class PrintingSystem {

    public static void main(String[] args) {

        //region Printer
        LaserPrinter laserPrinter = new LaserPrinter("printer1",
                1,
                250,
                500,
                0);

        Printer printer = new LaserPrinter()
        //endregion

        //region ThreadGroups
        ThreadGroup studentThreadGroup = new ThreadGroup("Student_ThreadGroup");
        ThreadGroup technicianThreadGroup = new ThreadGroup("Technician_ThreadGroup");
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
        //endregion

        //region Stating threads

        //endregion
    }
}
