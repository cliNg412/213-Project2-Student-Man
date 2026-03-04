package tuition;

import mypakege.Major;
import mypakege.Profile;

public class International extends NonResident {
    private boolean isStudyAbroad;

    private static final double HEALTH_INSURANCE = 2650;
    private static final double ADMIN_FEE = 500;

    public International(Profile profile, Major major, int creditsCompleted, boolean isStudyAbroad) {
        super(profile, major, creditsCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    public boolean isStudyAbroad() {
        return isStudyAbroad;
    }

    @Override
    public double tuition(int creditsEnrolled) {
        boolean fullTime = creditsEnrolled >= 12;
        double universityFee = fullTime ? UNIVERSITY_FEE_FULL : UNIVERSITY_FEE_FULL * 0.5;
        if (isStudyAbroad) {
            return universityFee + HEALTH_INSURANCE + ADMIN_FEE;
        }
        if (fullTime && creditsCompleted >= 120) {
            return universityFee + HEALTH_INSURANCE + ADMIN_FEE;
        }
        double tuition;
        if (fullTime) {
            tuition = FULL_TIME_TUITION;
            if (creditsEnrolled > 16) tuition += (creditsEnrolled - 16) * PART_TIME_PER_CREDIT;
        } else {
            tuition = creditsEnrolled * PART_TIME_PER_CREDIT;
        }
        return tuition + universityFee + HEALTH_INSURANCE + ADMIN_FEE;
    }

    @Override
    public String toString() {
        return getProfile() + " [" + getMajor().name() + "," + getMajor().getSchool() + "] credits earned: "
                + getCreditsCompleted() + " [" + getStanding() + "]"
                + (isStudyAbroad ? "[international:study abroad]" : "[international]");
    }
}