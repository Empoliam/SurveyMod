package dev.patchi.surveymod;

import java.util.ArrayList;
import java.util.List;

public class SurveyTrip {

    String tripName = "trip1";
    List<SurveyLeg> legList = new ArrayList<SurveyLeg>();
    public boolean saved = false;

    public SurveyTrip(String trip) {
        this.tripName = trip;
    }

    public void addLeg(SurveyLeg leg) {
        legList.add(leg);
    }

    public List<SurveyLeg> getLegList() {
        return legList;
    }

    public void clearList() {
        legList.clear();
    }

}
