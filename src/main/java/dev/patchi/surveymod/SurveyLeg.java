package dev.patchi.surveymod;

public class SurveyLeg {

    private final String from;
    private final String to;
    private final double clino;
    private final double compass;
    private final double distance;

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

    public String getFrom(){
        return from;
    }

    public String getTo() {
        return to;
    }

}
