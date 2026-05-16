import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    // Inner class (no conflict now)
    static class Data {
        String name;
        double grade;

        Data(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Data> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        // Input
        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1));

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter grade: ");
            double grade = sc.nextDouble();
            sc.nextLine();

            students.add(new Data(name, grade));
        }

        // Calculations
        double total = 0;
        double highest = students.get(0).grade;
        double lowest = students.get(0).grade;

        for (Data s : students) {
            total += s.grade;

            if (s.grade > highest) highest = s.grade;
            if (s.grade < lowest) lowest = s.grade;
        }

        double average = total / students.size();

        // Output
        System.out.println("\n===== Student Report =====");
        for (Data s : students) {
            System.out.println("Name: " + s.name + " | Grade: " + s.grade);
        }

        System.out.println("\n===== Summary =====");
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score: " + lowest);

        sc.close();
    }
}