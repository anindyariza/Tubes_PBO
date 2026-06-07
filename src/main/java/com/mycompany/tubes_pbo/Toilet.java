package com.mycompany.tubes_pbo;

public class Toilet extends FasilitasPantai {

    public double entryfee;
    public int waterStockLevel; 

    public Toilet(String facilityID, String lokasi, String status, double entryfee, int waterStockLevel) {
        super(facilityID, lokasi, status);
        this.entryfee = entryfee;
        this.waterStockLevel = waterStockLevel;
    }

    public String checkWaterCondition() {
        if (waterStockLevel < 20) {
            Status = "Dalam Perbaikan";
            return "Toilet " + facilityID + ": stok air sangat rendah ("
                 + waterStockLevel + "%). Status → 'Dalam Perbaikan'.";
        } else if (waterStockLevel < 50) {
            return "Toilet " + facilityID + ": stok air rendah ("
                 + waterStockLevel + "%). Perlu perhatian.";
        }
        return "Toilet " + facilityID + ": kondisi air normal ("
             + waterStockLevel + "%).";
    }

    @Override
    public double calculateMaintenanceCost() {
        return entryfee * 30;
    }

    @Override
    public String getReportData() {
        return super.getReportData()
             + " | Tarif masuk: Rp" + String.format("%,.0f", entryfee)
             + " | Stok air: " + waterStockLevel + "%";
    }

    public double getEntryfee() { return entryfee; }
    public int getWaterStockLevel() { return waterStockLevel; }

    public void setEntryfee(double entryfee) { this.entryfee = entryfee; }
    public void setWaterStockLevel(int waterStockLevel){ this.waterStockLevel = waterStockLevel; }
}
