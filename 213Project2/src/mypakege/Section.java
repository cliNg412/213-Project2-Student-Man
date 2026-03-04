package mypakege;

import tuition.Student;

/**
 * Course Section.
 */
public class Section {
    private static final int CAPACITY = 4;

    private Course course;
    private int period;
    private Instructor instructor;
    private Classroom classroom;
    private StudentList roster;
    private int numStudents;

    public Section(Course course, int period, Instructor instructor, Classroom classroom) {
        this.course = course;
        this.period = period;
        this.instructor = instructor;
        this.classroom = classroom;
        this.roster = new StudentList();
        this.numStudents = 0;
    }

    public Course getCourse() { return course; }
    public int getPeriod() { return period; }
    public Instructor getInstructor() { return instructor; }
    public Classroom getClassroom() { return classroom; }
    public int getNumStudents() { return numStudents; }

    public boolean isFull() {
        return numStudents >= CAPACITY;
    }

    public boolean contains(Student student) {
        return roster.contains(student);
    }

    public void enroll(Student student) {
        if (isFull()) return;
        if (contains(student)) return;
        roster.add(student);
        numStudents++;
    }

    public void drop(Student student) {
        if (!contains(student)) return;
        roster.remove(student);
        numStudents--;
    }

    @Override
    public String toString() {
        return "[" + course.getCode() + " " + Time.timeOf(period) + "] [" + instructor + "] " + classroom;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(toString()).append("\n");
        if (numStudents == 0) {
            sb.append("        **No students enrolled**");
        } else {
            sb.append("        **Roster**\n");
            for (Student student : roster) {
                sb.append("        ").append(student.getProfile()).append("\n");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}