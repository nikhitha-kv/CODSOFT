import java.util.ArrayList;
import java.util.List;

// Course class to represent course information
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<Student> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(student);
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        return registeredStudents.remove(student);
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nSchedule: " + schedule;
    }
}

// Student class to represent student information
class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        return registeredCourses.add(course);
    }

    public boolean dropCourse(Course course) {
        return registeredCourses.remove(course);
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name;
    }
}

// CourseDatabase class to manage courses
class CourseDatabase {
    private List<Course> courses;

    public CourseDatabase() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

// StudentDatabase class to manage students
class StudentDatabase {
    private List<Student> students;

    public StudentDatabase() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student findStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }
}

public class task_5 {
    public static void main(String[] args) {
        // Create course database
        CourseDatabase courseDatabase = new CourseDatabase();
        Course javaCourse = new Course("CS101", "Introduction to Java Programming", "Basic Java programming concepts", 20, "Monday, Wednesday, Friday");
        courseDatabase.addCourse(javaCourse);

        // Create student database
        StudentDatabase studentDatabase = new StudentDatabase();
        Student student1 = new Student(1001, "John Doe");
        studentDatabase.addStudent(student1);

        // Register student for a course
        javaCourse.registerStudent(student1);
        System.out.println("Student " + student1.getName() + " registered for course " + javaCourse.getTitle());

        // Drop course for a student
        javaCourse.removeStudent(student1);
        System.out.println("Student " + student1.getName() + " dropped course " + javaCourse.getTitle());
    }
}
