package dev.patchi.surveymod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SurveyTrip {

    String tripName = "trip1";
    List<SurveyLeg> legList = new ArrayList<SurveyLeg>();

    HashSet<String> stationList = new HashSet<>();

    public boolean saved = false;

    public SurveyTrip(String trip) {
        this.tripName = trip;
    }

    public void addLeg(SurveyLeg leg) {
        legList.add(leg);
        addSurveyStation(leg.getFrom());
        addSurveyStation(leg.getTo());
    }

    public void addSurveyStation(String station) {
        stationList.add(station);
    }

    public List<SurveyLeg> getLegList() {
        return legList;
    }

    public void clearList() {
        legList.clear();
        stationList.clear();
    }

}
