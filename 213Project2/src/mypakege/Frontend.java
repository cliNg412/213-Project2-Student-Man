package mypakege;

import tuition.International;
import tuition.NonResident;
import tuition.Resident;
import tuition.Student;
import tuition.TriState;
import util.Date;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Frontend {
    private StudentList students = new StudentList();
    private Schedule schedule = new Schedule();

    public void run() {
        System.out.println("Registration System is running.");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            String cmd = st.nextToken();

            if (cmd.equals("Q")) {
                System.out.println("Registration System is terminated.");
                return;
            }

            try {
                switch (cmd) {
                    case "L": handleL(); break;

                    case "R": handleR(st); break;
                    case "AR": handleAR(st); break;
                    case "AN": handleAN(st); break;
                    case "AT": handleAT(st); break;
                    case "AI": handleAI(st); break;

                    case "S": handleS(st); break;

                    case "O": handleO(st); break;
                    case "C": handleC(st); break;
                    case "E": handleE(st); break;
                    case "D": handleD(st); break;

                    case "PS": students.print(); break;
                    case "PC": schedule.printByCourse(); break;
                    case "PL": schedule.printByLocation(); break;
                    case "PT": students.printTuition(schedule); break;
                    case "PG": students.printGraduates(); break;

                    default:
                        System.out.println(cmd + " is an invalid command!");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Missing data tokens.");
            } catch (NumberFormatException e) {
                System.out.println("INVALID: XX is not an integer!");
            }
        }
    }
    // ===================== helpers =====================

    private boolean isAtLeast16(Date dob) {
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH) + 1;
        int d = cal.get(Calendar.DAY_OF_MONTH);

        int age = y - dob.getYear();
        if (m < dob.getMonth() || (m == dob.getMonth() && d < dob.getDay())) age--;
        return age >= 16;
    }

    private Profile readProfile(StringTokenizer st) {
        String fn = st.nextToken();
        String ln = st.nextToken();
        Date dob = new Date(st.nextToken());
        return new Profile(fn, ln, dob);
    }

    private boolean validateDOB(Date dob) {
        if (!dob.isValid()) {
            System.out.println("INVALID: " + dob + " is not a valid calendar date!");
            return false;
        }
        if (dob.isTodayOrFuture()) {
            System.out.println("INVALID: " + dob + " cannot be today or a future day.");
            return false;
        }
        if (!isAtLeast16(dob)) {
            System.out.println("INVALID: " + dob + " younger than 16 years old.");
            return false;
        }
        return true;
    }

    private Major readMajor(StringTokenizer st) {
        String majorToken = st.nextToken();
        Major m = Major.from(majorToken);
        if (m == null) {
            System.out.println("INVALID: " + majorToken.toUpperCase() + " major does not exist.");
        }
        return m;
    }

    private int readCreditsCompleted(StringTokenizer st) {
        String tok = st.nextToken();
        int credits;
        try {
            credits = Integer.parseInt(tok);
        } catch (NumberFormatException e) {
            System.out.println("INVALID: " + tok + " is not an integer!");
            return Integer.MIN_VALUE;
        }
        if (credits < 0) {
            System.out.println("INVALID: " + credits + " credit is negative!");
            return -1;
        }
        return credits;
    }

    private boolean addStudent(Student s) {
        if (students.contains(s)) {
            System.out.println(s.getProfile() + " student is already in the list.");
            return false;
        }
        students.add(s);
        System.out.println(s.getProfile() + addTag(s) + " added to the list.");
        return true;
    }

    private String standingLabel(Standing s) {
        switch (s) {
            case FRESHMAN:
                return "Freshman";
            case SOPHOMORE:
                return "Sophomore";
            case JUNIOR:
                return "Junior";
            case SENIOR:
                return "Senior";
            default:
                return s.name();
        }
    }

    private String addTag(Student s) {
        if (s instanceof tuition.Resident) return "[Resident]";
        if (s instanceof tuition.TriState) {
            String st = ((tuition.TriState) s).getState();
            return "[Tristate: " + st + "]";
        }
        if (s instanceof tuition.International) {
            return ((tuition.International) s).isStudyAbroad()
                    ? "[International study abroad]"
                    : "[International]";
        }
        if (s instanceof tuition.NonResident) return "[Nonresident]";
        return "";
    }

    // ===================== L =====================

    private void handleL() {
        String file = "students.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                StringTokenizer inner = new StringTokenizer(line);
                String cmd = inner.nextToken().toUpperCase();
                try {
                    switch (cmd) {
                        case "R":
                            handleAR(inner);
                            break;
                        case "N":
                            handleAN(inner);
                            break;
                        case "T":
                            handleAT(inner);
                            break;
                        case "I":
                            handleAI(inner);
                            break;
                        default:
                            System.out.println(cmd + " is an invalid command!");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Missing data tokens.");
                } catch (NumberFormatException e) {
                    System.out.println("INVALID: XX is not an integer!");
                }
            }
            System.out.println("student list loaded from the text file.");
        } catch (IOException e) {
            System.out.println(file + " not found!");
        }
    }

    private void handleR(StringTokenizer st) {
        Profile p = readProfile(st);
        if (students.removeByProfile(p)) {
            System.out.println(p + " removed from the list.");
        } else {
            System.out.println(p + " is not in the student list.");
        }
    }

    // ===================== AR / AN / AT / AI =====================

    private void handleAR(StringTokenizer st) {
        String fn = st.nextToken();
        String ln = st.nextToken();
        Date dob = new Date(st.nextToken());
        if (!validateDOB(dob)) return;

        Major major = readMajor(st);
        if (major == null) return;

        int creditsCompleted = readCreditsCompleted(st);
        if (creditsCompleted == Integer.MIN_VALUE || creditsCompleted < 0) return;

        Profile p = new Profile(fn, ln, dob);
        addStudent(new Resident(p, major, creditsCompleted));
    }

    private void handleAN(StringTokenizer st) {
        String fn = st.nextToken();
        String ln = st.nextToken();
        Date dob = new Date(st.nextToken());
        if (!validateDOB(dob)) return;

        Major major = readMajor(st);
        if (major == null) return;

        int creditsCompleted = readCreditsCompleted(st);
        if (creditsCompleted == Integer.MIN_VALUE || creditsCompleted < 0) return;

        Profile p = new Profile(fn, ln, dob);
        addStudent(new NonResident(p, major, creditsCompleted));
    }

    private void handleAT(StringTokenizer st) {
        String fn = st.nextToken();
        String ln = st.nextToken();
        Date dob = new Date(st.nextToken());
        if (!validateDOB(dob)) return;

        Major major = readMajor(st);
        if (major == null) return;

        int creditsCompleted = readCreditsCompleted(st);
        if (creditsCompleted == Integer.MIN_VALUE || creditsCompleted < 0) return;

        String state = st.nextToken().toUpperCase(); // NY or CT
        if (!state.equals("NY") && !state.equals("CT")) {
            System.out.println(state + ": Invalid state code.");
            return;
        }
        Profile p = new Profile(fn, ln, dob);
        addStudent(new TriState(p, major, creditsCompleted, state));
    }

    private void handleAI(StringTokenizer st) {
        String fn = st.nextToken();
        String ln = st.nextToken();
        Date dob = new Date(st.nextToken());
        if (!validateDOB(dob)) return;

        Major major = readMajor(st);
        if (major == null) return;

        int creditsCompleted = readCreditsCompleted(st);
        if (creditsCompleted == Integer.MIN_VALUE || creditsCompleted < 0) return;

        String abroadToken = st.nextToken().trim().toUpperCase();
        boolean abroad = abroadToken.equals("T") || abroadToken.equals("TRUE");

        Profile p = new Profile(fn, ln, dob);
        addStudent(new International(p, major, creditsCompleted, abroad));
    }

    // ===================== S scholarship =====================

    private void handleS(StringTokenizer st) {
        Profile p = readProfile(st);
        String tok = st.nextToken();
        int amount;

        try {
            amount = Integer.parseInt(tok);
        } catch (NumberFormatException e) {
            System.out.println("INVALID: amount is not an integer.");
            return;
        }

        Student s = students.get(p);
        if (s == null) {
            System.out.println("INVALID: " + p + " does not exist.");
            return;
        }

        if (!(s instanceof Resident)) {
            System.out.println(p + " is a non-resident not eligible for the scholarship.");
            return;
        }

        if (amount <= 0 || amount > 10000) {
            System.out.println("INVALID: scholarship amount cannot be 0 or negative or greater than $10,000.");
            return;
        }

        int enrolledCredits = schedule.creditsEnrolled(s);
        if (enrolledCredits < 12) {
            System.out.println(p + " enrolled less than 12 credits, not eligible for the scholarship.");
            return;
        }

        ((Resident) s).setScholarship(amount);
        System.out.printf("Scholarship $%,d updated for %s%n", amount, p);
    }

    // ===================== O/C/E/D (你原来项目里有就行；这里给可跑版本) =====================

    private void handleO(StringTokenizer st) {
        String courseToken = st.nextToken();
        int period = Integer.parseInt(st.nextToken());
        String instToken = st.nextToken();
        String roomToken = st.nextToken();

        Course c = Course.from(courseToken);
        if (c == null) {
            System.out.println("INVALID: course name " + courseToken.toLowerCase() + " does not exist.");
            return;
        }

        if (!Time.isValidPeriod(period)) {
            System.out.println("INVALID: period " + period + " does not exist.");
            return;
        }

        if (schedule.hasCoursePeriod(c, period)) {
            System.out.println("INVALID: " + c.getCode() + " period " + period + " already exists.");
            return;
        }

        Instructor ins = Instructor.from(instToken);
        if (ins == null) {
            System.out.println("INVALID: faculty " + instToken.toLowerCase() + " does not exist.");
            return;
        }

        if (schedule.instructorConflict(ins, period)) {
            System.out.println("INVALID: " + ins + " time conflict.");
            return;
        }

        Classroom room = Classroom.from(roomToken);
        if (room == null) {
            System.out.println("INVALID: location " + roomToken.toLowerCase() + " does not exist.");
            return;
        }

        if (!schedule.classroomAvailable(room, period)) {
            System.out.println("INVALID: " + room + " not available.");
            return;
        }

        Section sec = new Section(c, period, ins, room);
        schedule.add(sec);
        System.out.println(sec + " added to the schedule.");
    }

    private void handleC(StringTokenizer st) {
        String courseToken = st.nextToken();
        int period = Integer.parseInt(st.nextToken());

        Course c = Course.from(courseToken);
        if (c == null) {
            System.out.println("INVALID: course name " + courseToken.toLowerCase() + " does not exist.");
            return;
        }
        if (!Time.isValidPeriod(period)) {
            System.out.println("INVALID: period " + period + " does not exist.");
            return;
        }

        Section sec = schedule.get(c, period);
        if (sec == null) {
            System.out.println(c.getCode() + " " + Time.timeOf(period) + " does not exist.");
            return;
        }

        if (sec.getNumStudents() > 0) {
            System.out.println(c.getCode() + " " + Time.timeOf(period)
                    + " cannot be removed [" + sec.getNumStudents() + " student(s) enrolled]");
            return;
        }

        schedule.remove(sec);
        System.out.println(c.getCode() + " " + Time.timeOf(period) + " removed.");
    }

    private void handleE(StringTokenizer st) {
        String fn = st.nextToken();
        String ln = st.nextToken();
        Date dob = new Date(st.nextToken());

        String courseToken = st.nextToken();
        int period = Integer.parseInt(st.nextToken());

        Course c = Course.from(courseToken);
        if (c == null) {
            System.out.println("INVALID: course name " + courseToken.toLowerCase() + " does not exist.");
            return;
        }
        if (!Time.isValidPeriod(period)) {
            System.out.println("INVALID: period " + period + " does not exist.");
            return;
        }

        Section sec = schedule.get(c, period);
        if (sec == null) {
            System.out.println("INVALID: " + c.getCode() + " " + Time.timeOf(period) + " does not exist.");
            return;
        }

        Profile p = new Profile(fn, ln, dob);
        Student student = students.get(p);
        if (student == null) {
            System.out.println("INVALID: " + p + " does not exist.");
            return;
        }

        if (schedule.studentEnrolledSameCourse(student, c)) {
            System.out.println(p + " already enrolled in " + c.getCode());
            return;
        }

        if (schedule.studentTimeConflict(student, period)) {
            System.out.println("Time conflict: " + p + " enrolled in another class at period " + period);
            return;
        }

        Standing standing = Standing.fromCredits(student.getCreditsCompleted());
        if (!standing.meets(c.getMinStanding())) {
            System.out.println("Prereq: " + c.getMinStanding() + " - " + p + " [" + standing + "]");
            return;
        }

        if (c.getMajorOnly() != null && student.getMajor() != c.getMajorOnly()) {
            System.out.println("Prereq: major only - " + p + " [" + student.getMajor().name() + "]");
            return;
        }

        int currentCredits = schedule.creditsEnrolled(student);
        int newCredits = currentCredits + c.getCredits();
        if (student instanceof International) {
            International intl = (International) student;
            if (intl.isStudyAbroad() && newCredits > 12) {
                System.out.println("International student study abroad cannot enroll more than 12 credits.");
                return;
            }
        }
        if (newCredits > 20) {
            System.out.println("Cannot enroll " + p + "; now has " + currentCredits
                    + " will exceeds credit limit of 20.");
            return;
        }


        if (sec.isFull()) {
            System.out.println("Cannot enroll " + p + ", " + c.getCode() + " " + Time.timeOf(period) + " is full.");
            return;
        }

        sec.enroll(student);
        System.out.println(p + " added to " + c.getCode() + " " + Time.timeOf(period));
    }

    private void handleD(StringTokenizer st) {
        String fn = st.nextToken();
        String ln = st.nextToken();
        Date dob = new Date(st.nextToken());

        String courseToken = st.nextToken();
        int period = Integer.parseInt(st.nextToken());

        Course c = Course.from(courseToken);
        if (c == null) {
            System.out.println("INVALID: course name " + courseToken.toLowerCase() + " does not exist.");
            return;
        }
        if (!Time.isValidPeriod(period)) {
            System.out.println("INVALID: period " + period + " does not exist.");
            return;
        }

        Section sec = schedule.get(c, period);
        if (sec == null) {
            System.out.println("INVALID: " + c.getCode() + " " + Time.timeOf(period) + " does not exist.");
            return;
        }

        Profile p = new Profile(fn, ln, dob);
        Student student = students.get(p);
        if (student == null) {
            System.out.println("INVALID: " + p + " does not exist.");
            return;
        }

        if (!sec.contains(student)) {
            System.out.println(p + " is not enrolled in this section.");
            return;
        }

        sec.drop(student);
        System.out.println(p + " dropped from " + c.getCode() + " " + Time.timeOf(period));
    }
}