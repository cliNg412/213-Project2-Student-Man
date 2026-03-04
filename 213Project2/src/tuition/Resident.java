package tuition;

import mypakege.Major;
import mypakege.Profile;

public class Resident extends Student {
    private int scholarship; // dollars

    private static final double FULL_TIME_TUITION = 14933;
    private static final double PART_TIME_PER_CREDIT = 482;
    private static final double UNIVERSITY_FEE_FULL = 3891;

    public Resident(Profile profile, Major major, int creditsCompleted) {
        super(profile, major, creditsCompleted);
        this.scholarship = 0;
    }

    public void setScholarship(int amount) {
        this.scholarship = amount;
    }

    public int getScholarship() {
        return scholarship;
    }

    @Override
    public double tuition(int creditsEnrolled) {
        boolean fullTime = creditsEnrolled >= 12;
        double universityFee = fullTime ? UNIVERSITY_FEE_FULL : UNIVERSITY_FEE_FULL * 0.5;

        if (fullTime && creditsCompleted >= 120) {
            return universityFee;
        }

        double tuition;
        if (fullTime) {
            tuition = FULL_TIME_TUITION;
            if (creditsEnrolled > 16) {
                tuition += (creditsEnrolled - 16) * PART_TIME_PER_CREDIT;
            }
        } else {
            tuition = creditsEnrolled * PART_TIME_PER_CREDIT;
        }

        if (fullTime) {
            tuition -= scholarship;
            if (tuition < 0) tuition = 0;
        }

        return tuition + universityFee;
    }

    @Override
    protected String residencyTag() {
        return "[resident]";
    }
}