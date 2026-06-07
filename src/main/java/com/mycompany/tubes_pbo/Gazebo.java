package com.mycompany.tubes_pbo;

public class Gazebo extends FasilitasPantai {

    public int capacity;
    public Double hourlyRate;
    public Boolean isRented;

    public Gazebo(String facilityID, String lokasi, String status, int capacity, double hourlyRate, boolean isRented) {
        super(facilityID, lokasi, status);
        this.capacity = capacity;
        this.hourlyRate = hourlyRate;
        this.isRented = isRented;
    }

    public String bookGazebo() {
        if (isRented) {
            return "Gazebo " + facilityID + " sudah disewa.";
        }
        isRented = true;
        Status = "Tidak Tersedia";
        return "Gazebo " + facilityID + " berhasil dipesan. Tarif: Rp" + String.format("%,.0f", hourlyRate) + "/jam";
    }

    @Override
    public double calculateMaintenanceCost() {
        return capacity * 50_000.0;
    }

    @Override
    public String getReportData() {
        return super.getReportData()
             + " | Kapasitas: " + capacity
             + " | Tarif/jam: Rp" + String.format("%,.0f", hourlyRate)
             + " | Disewa: " + (isRented ? "Ya" : "Tidak");
    }

    public int getCapacity() { return capacity; }
    public Double getHourlyRate() { return hourlyRate; }
    public Boolean getIsRented() { return isRented; }

    public void setCapacity(int capacity) { this.capacity   = capacity; }
    public void setHourlyRate(double rate) { this.hourlyRate = rate; }
    public void setIsRented(boolean isRented) { this.isRented   = isRented; }
}
