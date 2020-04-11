package org.example;

import org.example.model.Student;

public class Main {

    public static void main(String[] args) {
        //addData();
        showData();
    }

    private static void showData() {
        DataService dataService = new DataService();
        String groupName = "Java";
        System.out.println("Students list:");
        System.out.println(dataService.getStudentsByGroup(groupName));

        String studentName = "Ben";
        System.out.println("Group list:");
        System.out.println(dataService.getGroupsByStudentName(studentName));
    }

    private static DataService addData() {
        DataService dataService = new DataService();
        dataService.addGroup(1, "Java");
        dataService.addGroup(4, "UI");
        dataService.addGroup(5, "QA");

        Student student1 = new Student("Alex");
        student1.addGroup(dataService.getGroup(1));
        student1.addGroup(dataService.getGroup(5));
        dataService.addStudent(student1);

        Student student2 = new Student("Ben");
        student2.addGroup(dataService.getGroup(1));
        student2.addGroup(dataService.getGroup(5));
        dataService.addStudent(student2);

        Student student3 = new Student("Carl");
        student3.addGroup(dataService.getGroup(4));
        dataService.addStudent(student3);
        return dataService;
    }


}
