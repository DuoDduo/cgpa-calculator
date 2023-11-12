package Assignment;

import java.util.Scanner;

class Course {
    private String name;
    private int units;
    private double score;

    public Course(String name, int units, double score) {
        this.name = name;
        this.units = units;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGradePoint() {
        if (score >= 70) {
            return 5;
        } else if (score >= 60) {
            return 4;
        } else if (score >= 50) {
            return 3;
        } else if (score >= 45) {
            return 2;
        } else if (score >= 40) {
            return 1;
        } else {
            return 0;
        }
    }
}

class Student {
    private String name;
    private Course[] courses;

    public Student(String name) {
        this.name = name;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public double calculateGradePointAverage() {
        double totalGradePoints = 0;
        double totalUnits = 0;


        for (Course course : courses) {

            totalGradePoints += course.getGradePoint() * course.getUnits();

            totalUnits += course.getUnits();
        }


        if (totalUnits == 0) {
            return 0;
        } else {

            double gpa;
            gpa = totalGradePoints / totalUnits;
            return gpa;
        }
    }
}
public class CGPACalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = input.nextLine();

        Course[] coursesSemester1 = inputCourses(input, "first");
        Course[] coursesSemester2 = inputCourses(input, "second");

        Student student = new Student(name);
        Course[] allCourses = combineCourses(coursesSemester1, coursesSemester2);
        student.setCourses(allCourses);

        double gradePointAverageSemester1 = calculateGradePointAverage(coursesSemester1);
        double gradePointAverageSemester2 = calculateGradePointAverage(coursesSemester2);
        double cgpa = (gradePointAverageSemester1 + gradePointAverageSemester2) / 2;

        System.out.println("Your grade point average for semester 1 is:" + gradePointAverageSemester1);
        System.out.println("Your grade point average for semester 2 is: "+ gradePointAverageSemester2);
        System.out.println("Dear "+ name+" Your CGPA is: "+ cgpa);
    }

    private static Course[] inputCourses(Scanner input, String semesterName) {
        System.out.println("Enter the number of courses for "+ semesterName + "semester: ");
        int numCourses = input.nextInt();

        Course[] courses = new Course[numCourses];

        for (int i = 0; i < numCourses; i++) {
            input.nextLine();
            System.out.println("Enter name of course " + (i+1));
            String courseName = input.nextLine();

            System.out.println("Enter units for course " + (i+1));
            int courseUnits = input.nextInt();

            System.out.println("Enter score for course " + (i+1));
            double courseScore = input.nextDouble();

            courses[i] = new Course(courseName, courseUnits, courseScore);
        }

        return courses;
    }

    private static Course[] combineCourses(Course[] courses1, Course[] courses2) {
        Course[] allCourses = new Course[courses1.length + courses2.length];
        System.arraycopy(courses1, 0, allCourses, 0, courses1.length);
        System.arraycopy(courses2, 0, allCourses, courses1.length, courses2.length);
        return allCourses;
    }

    private static double calculateGradePointAverage(Course[] courses) {
        Student student = new Student("");
        student.setCourses(courses);
        return student.calculateGradePointAverage();
    }
}

