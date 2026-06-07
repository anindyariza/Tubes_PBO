package com.mycompany.tubes_pbo;

import java.util.ArrayList;
import java.util.List;

public class BeachSection {

    public String sectionName;
    public int    maxCapacity;
    public int    currentVisitorCount;

    private List<VisitorTicket> tickets = new ArrayList<>();

    public BeachSection(String sectionName, int maxCapacity) {
        this.sectionName         = sectionName;
        this.maxCapacity         = maxCapacity;
        this.currentVisitorCount = 0;
    }

    public boolean isZoneFull() {
        return currentVisitorCount >= maxCapacity;
    }

    public void updateVisitorCount(int count) {
        if (count < 0) {
            this.currentVisitorCount = 0;
        } else if (count > maxCapacity) {
            this.currentVisitorCount = maxCapacity;
        } else {
            this.currentVisitorCount = count;
        }
    }

    public boolean tambahTiket(VisitorTicket tiket) {
        if (isZoneFull())           return false;
        if (!tiket.validateEntry()) return false;
        tickets.add(tiket);
        currentVisitorCount = tickets.size();
        return true;
    }

    public void hapusSemua() {
        tickets.clear();
        currentVisitorCount = 0;
    }

    public List<VisitorTicket> getTikets() {
        return new ArrayList<>(tickets);
    }

    public String getSectionName()         { return sectionName; }
    public int    getMaxCapacity()         { return maxCapacity; }
    public int    getCurrentVisitorCount() { return currentVisitorCount; }

    public void setSectionName(String sectionName) { this.sectionName = sectionName; }
    public void setMaxCapacity(int maxCapacity)     { this.maxCapacity = maxCapacity; }
}
