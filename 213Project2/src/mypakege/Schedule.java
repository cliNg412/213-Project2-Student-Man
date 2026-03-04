package mypakege;

import util.List;

/**
 * Schedule list.
 */
public class Schedule extends List<Section> {

    public Section get(Course course, int period) {
        for (Section section : this) {
            if (section.getCourse() == course && section.getPeriod() == period) {
                return section;
            }
        }
        return null;
    }

    public boolean hasCoursePeriod(Course course, int period) {
        return get(course, period) != null;
    }

    public boolean instructorConflict(Instructor instructor, int period) {
        for (Section section : this) {
            if (section.getInstructor() == instructor && section.getPeriod() == period) {
                return true;
            }
        }
        return false;
    }

    public boolean classroomAvailable(Classroom classroom, int period) {
        for (Section section : this) {
            if (section.getClassroom() == classroom && section.getPeriod() == period) {
                return false;
            }
        }
        return true;
    }

    public boolean studentEnrolledSameCourse(tuition.Student student, Course course) {
        for (Section section : this) {
            if (section.getCourse() == course && section.contains(student)) {
                return true;
            }
        }
        return false;
    }

    public boolean studentTimeConflict(tuition.Student student, int period) {
        for (Section section : this) {
            if (section.getPeriod() == period && section.contains(student)) {
                return true;
            }
        }
        return false;
    }

    public int creditsEnrolled(tuition.Student student) {
        int total = 0;
        for (Section section : this) {
            if (section.contains(student)) {
                total += section.getCourse().getCredits();
            }
        }
        return total;
    }

    public void printByCourse() {
        if (isEmpty()) {
            System.out.println("Schedule is empty!");
            return;
        }
        System.out.println("* List of sections ordered by course name, section time *");
        for (int i = 0; i < size() - 1; i++) {
            for (int j = 0; j < size() - i - 1; j++) {
                Section a = get(j);
                Section b = get(j + 1);
                int cmpCourse = a.getCourse().getCode().compareToIgnoreCase(b.getCourse().getCode());
                if (cmpCourse > 0 || (cmpCourse == 0 && a.getPeriod() > b.getPeriod())) {
                    set(j, b);
                    set(j + 1, a);
                }
            }
        }
        for (Section section : this) {
            System.out.println(section.print());
        }
        System.out.println("* end of list *");
    }

    public void printByLocation() {
        if (isEmpty()) {
            System.out.println("Schedule is empty!");
            return;
        }
        System.out.println("* List of sections ordered by campus, building *");
        for (int i = 0; i < size() - 1; i++) {
            for (int j = 0; j < size() - i - 1; j++) {
                Section a = get(j);
                Section b = get(j + 1);
                int cmpCampus = a.getClassroom().getCampus().compareToIgnoreCase(b.getClassroom().getCampus());
                int cmpBuilding = a.getClassroom().getBuilding().compareToIgnoreCase(b.getClassroom().getBuilding());
                if (cmpCampus > 0 || (cmpCampus == 0 && cmpBuilding > 0)) {
                    set(j, b);
                    set(j + 1, a);
                }
            }
        }
        for (Section section : this) {
            System.out.println(section.print());
        }
        System.out.println("* end of list *");
    }
}