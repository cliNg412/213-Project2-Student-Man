package mypakege;

import tuition.International;
import tuition.NonResident;
import tuition.Resident;
import tuition.Student;
import tuition.TriState;
import util.List;
import util.Sort;

/**
 * Student list.
 */
public class StudentList extends List<Student> {

    public Student get(Profile profile) {
        for (Student student : this) {
            if (student.getProfile().equals(profile)) {
                return student;
            }
        }
        return null;
    }

    public boolean containsProfile(Profile profile) {
        return get(profile) != null;
    }

    public boolean removeByProfile(Profile profile) {
        Student student = get(profile);
        if (student == null) return false;
        remove(student);
        return true;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Student list is empty!");
            return;
        }
        Sort.sort(this);
        System.out.println("* Student list ordered by last, first name, DOB *");
        for (Student student : this) {
            System.out.println(student);
        }
        System.out.println("* end of list **");
    }

    public void printTuition(Schedule schedule) {
        if (schedule == null || schedule.isEmpty()) {
            System.out.println("Schedule is empty!");
            return;
        }

        Sort.sort(this);
        System.out.println("* Tuition dues ordered by student. *");

        for (Student student : this) {
            System.out.println(student.getProfile() + billTag(student));

            int credits = 0;

            for (Section sec : schedule) {
                if (sec.contains(student)) {
                    System.out.println("               " + sec.getCourse().getCode()
                            + "[" + Time.timeOf(sec.getPeriod()) + "] [credit: " + sec.getCourse().getCredits() + "]");
                    credits += sec.getCourse().getCredits();
                }
            }

            if (student instanceof International) {
                International intl = (International) student;

                if (intl.isStudyAbroad()) {
                    if (credits == 0) {
                        System.out.println("               **not enrolled.");
                    } else {
                        System.out.printf("               **Total credits enrolled: %d [tuition due: $%,.2f]%n",
                                credits, student.tuition(credits));
                    }
                } else {
                    if (credits < 12) {
                        System.out.println("               **International student must enroll at least 12 credits.");
                    } else {
                        System.out.printf("               **Total credits enrolled: %d [tuition due: $%,.2f]%n",
                                credits, student.tuition(credits));
                    }
                }
            } else {
                if (credits == 0) {
                    System.out.println("               **not enrolled.");
                } else {
                    System.out.printf("               **Total credits enrolled: %d [tuition due: $%,.2f]%n",
                            credits, student.tuition(credits));
                }
            }
        }

        System.out.println("* end of list *");
    }


    public void printGraduates() {
        if (isEmpty()) {
            System.out.println("Schedule is empty!");
            return;
        }

        Sort.sort(this);
        System.out.println("* List of students eligible for graduation, ordered by major *");
        boolean found = false;

        for (Student student : this) {
            if (student.getCreditsCompleted() >= 120) {
                System.out.println(student.getProfile() + "[" + student.getMajor().name() + "," + student.getMajor().getSchool() + "]");
                found = true;
            }
        }

        if (!found) {
            System.out.println("**No students eligible to graduate**");
        }

        System.out.println("* end of list *");
    }

    public String billTag(Student student) {
        if (student instanceof Resident) return "[Resident]";
        if (student instanceof TriState) return "[Tristate: " + ((TriState) student).getState() + "]";
        if (student instanceof International) {
            return ((International) student).isStudyAbroad() ? "[International study abroad]" : "[International]";
        }
        if (student instanceof NonResident) return "[Noresident]";
        return "";
    }
}