package edu.tcu.cs.superfrogscheduler.domain;

/**
 * There are three types of events a SuperFrog can appear:
 * 1. Texas Christian University (TCU) On Campus event: $100/hr
 * 2.Public Schools or Non-Profit event: $100/hr
 * 3. Private or Residential event: $175/hr
 */
public enum EventType {

    TCU(100), PUBLIC(100), PRIVATE(175);

    private int hourlyRate;


    EventType(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

}
