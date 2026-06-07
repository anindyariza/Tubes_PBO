package com.mycompany.tubes_pbo;

public class VisitorTicket {

    public String ticketID;
    public String visitorName;
    public String category;
    public String entryTime;

    public VisitorTicket(String ticketID, String visitorName,
                         String category, String entryTime) {
        this.ticketID    = ticketID;
        this.visitorName = visitorName;
        this.category    = category;
        this.entryTime   = entryTime;
    }

    public String printTicket() {
        return "===== TIKET MASUK PANTAI =====\n"
             + "No Tiket  : " + ticketID    + "\n"
             + "Nama      : " + visitorName + "\n"
             + "Kategori  : " + category    + "\n"
             + "Jam Masuk : " + entryTime   + "\n";
    }

    public boolean validateEntry() {
        return ticketID    != null && !ticketID.isBlank()
            && visitorName != null && !visitorName.isBlank()
            && category    != null && !category.isBlank()
            && entryTime   != null && !entryTime.isBlank();
    }

    public boolean simpan(String area) {
        String sql = "INSERT INTO tiketpengunjung "
                   + "(No_Tiket, Nama, Kategori, Jam_Masuk, Area_Pantai) VALUES ('"
                   + ticketID    + "','"
                   + visitorName + "','"
                   + category    + "','"
                   + entryTime   + "','"
                   + area        + "')";
        try {
            FasilitasPantai.getConnection().createStatement().execute(sql);
            return true;
        } catch (Exception e) {
            System.err.println("simpan tiket gagal: " + e.getMessage());
            return false;
        }
    }

    public static boolean hapusSemua() {
        try {
            FasilitasPantai.getConnection().createStatement()
                .execute("TRUNCATE TABLE tiketpengunjung");
            return true;
        } catch (Exception e) {
            System.err.println("hapusSemua tiket gagal: " + e.getMessage());
            return false;
        }
    }

    public static int hitungPerArea(String area) {
        try {
            java.sql.ResultSet rs = FasilitasPantai.getConnection().createStatement()
                .executeQuery("SELECT COUNT(*) FROM tiketpengunjung "
                            + "WHERE Area_Pantai='" + area + "'");
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            System.err.println("hitungPerArea gagal: " + e.getMessage());
        }
        return 0;
    }

    public static java.util.List<Object[]> ambilSemua() {
        java.util.List<Object[]> list = new java.util.ArrayList<>();
        try {
            java.sql.ResultSet rs = FasilitasPantai.getConnection().createStatement()
                .executeQuery("SELECT * FROM tiketpengunjung");
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString("No_Tiket"),
                    rs.getString("Nama"),
                    rs.getString("Kategori"),
                    rs.getString("Jam_Masuk"),
                    rs.getString("Area_Pantai")
                });
            }
        } catch (Exception e) {
            System.err.println("ambilSemua tiket gagal: " + e.getMessage());
        }
        return list;
    }

    public String getTicketID() { return ticketID; }
    public String getVisitorName() { return visitorName; }
    public String getCategory() { return category; }
    public String getEntryTime() { return entryTime; }

    public void setTicketID(String ticketID) { this.ticketID    = ticketID; }
    public void setVisitorName(String visitorName) { this.visitorName = visitorName; }
    public void setCategory(String category) { this.category    = category; }
    public void setEntryTime(String entryTime) { this.entryTime   = entryTime; }
}
