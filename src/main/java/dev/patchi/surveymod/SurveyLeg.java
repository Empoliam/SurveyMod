package dev.patchi.surveymod;

public class SurveyLeg {

    private final String from;
    private final double clino;
    private final double compass;
    private final double distance;
    private final String to;

    public SurveyLeg(String from, String to, double distance, double clino, double compass) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.clino = clino;
        this.compass = compass;
    }

    @Override
    public String toString() {
        return from + " " + to + " " + distance + " " + compass + " " + clino;
    }

}
