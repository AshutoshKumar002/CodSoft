import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    String code, title, description, schedule;
    int capacity;
    int enrolledStudents;

    Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    boolean hasAvailableSlots() {
        return enrolledStudents < capacity;
    }

    void enrollStudent() {
        enrolledStudents++;
    }

    void dropStudent() {
        enrolledStudents--;
    }
}

class Student {
    String id, name;
    ArrayList<String> registeredCourses;

    Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

public class CourseRegistrationSystem {
    static HashMap<String, Course> courseDatabase = new HashMap<>();
    static HashMap<String, Student> studentDatabase = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        courseDatabase.put("CSE101", new Course("CSE101", "Introduction to Computer Science", "Basics of Computer Science", 50, "Mon-Wed-Fri 10:00-11:00"));
        courseDatabase.put("CSE102", new Course("CSE102", "Data Structures", "Advanced data structures", 40, "Tue-Thu 09:00-10:30"));
        
        studentDatabase.put("S001", new Student("S001", "Ashutosh Kumar"));
        studentDatabase.put("S002", new Student("S002", "Rahul Singh"));

        while (true) {
            System.out.println("\n--- Student Course Registration System ---");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    listAvailableCourses();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    viewRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    static void listAvailableCourses() {
        System.out.println("\n--- Available Courses ---");
        for (Course course : courseDatabase.values()) {
            System.out.println("Course Code: " + course.code);
            System.out.println("Title: " + course.title);
            System.out.println("Description: " + course.description);
            System.out.println("Capacity: " + course.capacity);
            System.out.println("Enrolled Students: " + course.enrolledStudents);
            System.out.println("Schedule: " + course.schedule);
            System.out.println("Available Slots: " + (course.capacity - course.enrolledStudents));
            System.out.println();
        }
    }

    static void registerCourse() {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.next();
        Student student = studentDatabase.get(studentId);
        
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.next();
        Course course = courseDatabase.get(courseCode);
        
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }
        
        if (!course.hasAvailableSlots()) {
            System.out.println("No available slots in this course!");
            return;
        }
        
        course.enrollStudent();
        student.registerCourse(courseCode);
        System.out.println("Course registered successfully!");
    }

    static void dropCourse() {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.next();
        Student student = studentDatabase.get(studentId);
        
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.next();
        Course course = courseDatabase.get(courseCode);
        
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }
        
        if (!student.registeredCourses.contains(courseCode)) {
            System.out.println("Student is not registered in this course!");
            return;
        }
        
        course.dropStudent();
        student.dropCourse(courseCode);
        System.out.println("Course dropped successfully!");
    }

    static void viewRegisteredCourses() {
        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.next();
        Student student = studentDatabase.get(studentId);
        
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("\n--- Registered Courses ---");
        for (String courseCode : student.registeredCourses) {
            Course course = courseDatabase.get(courseCode);
            System.out.println("Course Code: " + course.code + ", Title: " + course.title);
        }
    }
}
