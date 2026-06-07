package com.mycompany.tubes_pbo;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class FasilitasPantai implements Laporan {

    public  String facilityID;
    public  String Lokasi;
    protected String Status;

    private static final String DB_URL  = "jdbc:mysql://localhost:3306/manajemenpantai";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private static Connection   dbConn;

    public static Connection getConnection() {
        try {
            if (dbConn == null || dbConn.isClosed()) {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                dbConn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            }
        } catch (Exception e) {
            System.err.println("Koneksi gagal: " + e.getMessage());
        }
        return dbConn;
    }

    public FasilitasPantai(String facilityID, String lokasi, String status) {
        this.facilityID = facilityID;
        this.Lokasi     = lokasi;
        this.Status     = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public abstract double calculateMaintenanceCost();
    
    @Override
    public String getReportData() {
        return "ID: " + facilityID
             + " | Lokasi: " + Lokasi
             + " | Status: " + Status
             + " | Biaya Perawatan: Rp" + String.format("%,.0f", calculateMaintenanceCost());
    }

    public boolean simpan() {
        String jenis = (this instanceof Gazebo) ? "Gazebo" : "Toilet";
        String sql = "INSERT INTO fasilitaspantai VALUES ('"
                   + facilityID + "','" + jenis + "','" + Status + "','" + Lokasi + "')";
        try {
            getConnection().createStatement().execute(sql);
            return true;
        } catch (Exception e) {
            System.err.println("simpan() gagal: " + e.getMessage());
            return false;
        }
    }

    public boolean ubah() {
        String jenis = (this instanceof Gazebo) ? "Gazebo" : "Toilet";
        String sql = "UPDATE fasilitaspantai SET "
                   + "Fasilitas='"   + jenis      + "',"
                   + "Status='"      + Status      + "',"
                   + "Area_Pantai='" + Lokasi      + "' "
                   + "WHERE ID_Fasilitas='" + facilityID + "'";
        try {
            getConnection().createStatement().execute(sql);
            return true;
        } catch (Exception e) {
            System.err.println("ubah() gagal: " + e.getMessage());
            return false;
        }
    }

    public static boolean hapus(String facilityID) {
        String sql = "DELETE FROM fasilitaspantai WHERE ID_Fasilitas='" + facilityID + "'";
        try {
            getConnection().createStatement().execute(sql);
            return true;
        } catch (Exception e) {
            System.err.println("hapus() gagal: " + e.getMessage());
            return false;
        }
    }

    public static boolean hapusSemua() {
        try {
            getConnection().createStatement().execute("TRUNCATE TABLE fasilitaspantai");
            return true;
        } catch (Exception e) {
            System.err.println("hapusSemua() gagal: " + e.getMessage());
            return false;
        }
    }

    public static java.util.List<Object[]> ambilSemua() {
        java.util.List<Object[]> list = new java.util.ArrayList<>();
        try {
            java.sql.ResultSet rs = getConnection().createStatement()
                .executeQuery("SELECT * FROM fasilitaspantai");
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString("ID_Fasilitas"),
                    rs.getString("Fasilitas"),
                    rs.getString("Status"),
                    rs.getString("Area_Pantai")
                });
            }
        } catch (Exception e) {
            System.err.println("ambilSemua() gagal: " + e.getMessage());
        }
        return list;
    }
}
