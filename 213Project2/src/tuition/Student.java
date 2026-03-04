package tuition;

import mypakege.Major;
import mypakege.Profile;
import mypakege.Standing;

/**
 * Abstract Student.
 */
public abstract class Student implements Comparable<Student> {
    protected Profile profile;
    protected Major major;
    protected int creditsCompleted;

    public Student(Profile profile, Major major, int creditsCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditsCompleted = creditsCompleted;
    }

    public Profile getProfile() { return profile; }
    public Major getMajor() { return major; }
    public int getCreditsCompleted() { return creditsCompleted; }

    public Standing getStanding() {
        return Standing.fromCredits(creditsCompleted);
    }

    /**
     * Compute tuition due given credits enrolled this semester.
     */
    public abstract double tuition(int creditsEnrolled);

    @Override
    public int compareTo(Student o) {
        return this.profile.compareTo(o.profile);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return this.profile.equals(other.profile);
    }

    @Override
    public String toString() {
        String result = profile + " [" + major + "] credits earned: " + creditsCompleted
                + " [" + Standing.fromCredits(creditsCompleted) + "]" + residencyTag();

        if (this instanceof Resident) {
            int scholarship = ((Resident) this).getScholarship();
            if (scholarship > 0) {
                result += String.format(" [scholarship: $%,d]", scholarship);
            }
        }

        return result;
    }

    /**
     * Residency tag in the list printing, e.g., "[resident]" or "[international]".
     */
    protected abstract String residencyTag();
}