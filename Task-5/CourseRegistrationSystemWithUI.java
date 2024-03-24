import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
            student.registerCourse(this);
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        if (registeredStudents.remove(student)) {
            student.dropCourse(this);
            return true;
        }
        return false;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nSchedule: " + schedule + "\nAvailable Slots: " + getAvailableSlots();
    }
}

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

public class CourseRegistrationSystemWithUI {
    CourseDatabase courseDatabase;
    StudentDatabase studentDatabase;

    public CourseRegistrationSystemWithUI() {
        this.courseDatabase = new CourseDatabase();
        this.studentDatabase = new StudentDatabase();
    }

    public void addCourse(Course course) {
        courseDatabase.addCourse(course);
    }

    public void addStudent(Student student) {
        studentDatabase.addStudent(student);
    }

    public static void main(String[] args) {
        CourseRegistrationSystemWithUI system = new CourseRegistrationSystemWithUI();

        SwingUtilities.invokeLater(() -> {
            new CourseRegistrationUI(system);
        });
    }
}

class CourseRegistrationUI extends JFrame {
    public CourseRegistrationUI(CourseRegistrationSystemWithUI system) {
        setTitle("Course Registration System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel coursePanel = new JPanel(new GridLayout(0, 1));
        for (Course course : system.courseDatabase.getCourses()) {
            JButton courseButton = new JButton(course.getTitle());
            courseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int option = JOptionPane.showConfirmDialog(null, course.toString() + "\n\nDo you want to register for this course?");
                    if (option == JOptionPane.YES_OPTION) {
                        String studentName = JOptionPane.showInputDialog(null, "Enter your name:");
                        if (studentName != null && !studentName.isEmpty()) {
                            Student student = new Student(system.studentDatabase.getStudents().size() + 1, studentName);
                            if (course.registerStudent(student)) {
                                system.addStudent(student);
                                JOptionPane.showMessageDialog(null, "Registration successful!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Registration failed. Course is full.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter your name.");
                        }
                    }
                }
            });
            coursePanel.add(courseButton);
        }

        add(coursePanel, BorderLayout.CENTER);

        setVisible(true);
    }
}

class CourseDatabase {
    private List<Course> courses;

    public CourseDatabase() {
        this.courses = new ArrayList<>();
        courses.add(new Course("CS101", "Introduction to Java Programming", "Basic Java programming concepts", 20, "Monday, Wednesday, Friday"));
        courses.add(new Course("CS102", "Introduction to Python Programming", "Basic Python programming concepts", 25, "Tuesday, Thursday"));
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}

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
}
