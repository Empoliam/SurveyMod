package dev.patchi.surveymod;

public record SurveySplay(String station, double left, double right, double up, double down) {

    @Override
    public String toString() {
        return station + " " + left + " " + right + " " + up + " " + down;
    }
}
