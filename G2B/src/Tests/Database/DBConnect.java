package Database;

import java.io.File;
import java.net.URL;
import java.sql.*;

/**
 * @author Christoph Reichl
 * @since 28.04.2017
 */

public class DBConnect {

    public static void DBConnect() {
        //constructor

        Connection conn = null;

        try {

            //String url = "jdbc:sqlite:C:/Users/Christoph/Schule/POS_3CHIF/POS-Project/G2B-Database/G2B.db";
            String url = "jdbc:sqlite:DB/G2B.db";
            conn = DriverManager.getConnection(String.valueOf(url));
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            try {

                if (conn != null) {

                    conn.close();

                }

            } catch (SQLException ex) {

                System.out.println(ex.getMessage());

            }
        }
    }

    public static void DBcreate(String fileName) {
        //creates a new database which has the name of the parameter

        //String url = "jdbc:sqlite:C:/Users/Christoph/Schule/POS_3CHIF/POS-Project/G2B-Database" + fileName;
        String url = "jdbc:sqlite:DB/" + fileName;
        //File file = new File("C:\\Users\\Christoph\\Schule\\POS_3CHIF\\POS-Project\\G2B-Database\\" + fileName);
        File file = new File("jdbc:sqlite:DB\\" + fileName);
        if (!file.exists()) {

            try (Connection conn = DriverManager.getConnection(url)) {

                if (conn != null) {

                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");

                }

            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }

        }

    }

    public static void TableCreate() {
        //creates the tables of the database

        // SQLite connection string
        //String url = "jdbc:sqlite:C:/Users/Christoph/Schule/POS_3CHIF/POS-Project/G2B-Database/G2B.db";
        String url = "jdbc:sqlite:DB/G2B.db";
        // SQL statement for creating a new table
        String t_kellner = "CREATE TABLE IF NOT EXISTS Kellner (\n"
                + "	SVNr varchar PRIMARY KEY,\n"
                + "	vname varchar NOT NULL,\n"
                + "	fname varchar NOT NULL,\n"
                + "	pw varchar NOT NULL,\n"
                + "	PLZ integer NOT NULL,\n"
                + "	Stadt varchar NOT NULL\n"
                + ");";

        String t_bestellung = " CREATE TABLE IF NOT EXISTS Bestellung (\n"
                + "	TischNr integer PRIMARY KEY,\n"
                + "	SVNr varchar REFERENCES Kellner,\n"
                + "	Zeit varchar NOT NULL,\n"
                + "	Essen varchar NOT NULL,\n"
                + "	Getraenk varchar NOT NULL,\n"
                + "	Preis double(5, 2) NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(String.valueOf(url));
             Statement stmt = conn.createStatement()) {

            // create a new table
            stmt.execute(t_kellner);
            stmt.execute(t_bestellung);
            System.out.println("A new table has been created.");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }

     public boolean insert_k(String SVNr, String vname, String fname, String pw, int PLZ, String Stadt) {
        //inserts into the database the parameter values in table 'Kellner'

         //String url = "jdbc:sqlite:C:/Users/Christoph/Schule/POS_3CHIF/POS-Project/G2B-Database/G2B.db";
         String url = "jdbc:sqlite:DB/G2B.db";
         String sql = "INSERT INTO Kellner(SVNr, vname, fname, pw, PLZ, Stadt) VALUES(?, ?, ?, ?, ?, ?)";

         try (Connection conn = DriverManager.getConnection(url);
              PreparedStatement pstmt = conn.prepareStatement(sql)) {

             pstmt.setString(1, SVNr);
             pstmt.setString(2, vname);
             pstmt.setString(3, fname);
             pstmt.setString(4, pw);
             pstmt.setInt(5, PLZ);
             pstmt.setString(6, Stadt);
             pstmt.executeUpdate();

         } catch (SQLException e) {

             return false;

         }

         return true;

     }

    public void insert_b(int TischNr, String SVNr, String Zeit, String Essen, String Getraenk, double Preis) {
        //inserts into the database the parameter values in table 'Bestellung'

        //String url = "jdbc:sqlite:C:/Users/Christoph/Schule/POS_3CHIF/POS-Project/G2B-Database/G2B.db";
        String url = "jdbc:sqlite:DB/G2B.db";
        String sql = "INSERT INTO Bestellung(TischNr, SVNr, Zeit, Essen, Getraenk, Preis) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, TischNr);
            pstmt.setString(2, SVNr);
            pstmt.setString(3, Zeit);
            pstmt.setString(4, Essen);
            pstmt.setString(5, Getraenk);
            pstmt.setDouble(6, Preis);
            pstmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    public void delete_k(String SVNr) {
        //deletes the whole row of the parameter value from 'Kellner'

        //String url = "jdbc:sqlite:C:/Users/Christoph/Schule/POS_3CHIF/POS-Project/G2B-Database/G2B.db";
        String url = "jdbc:sqlite:DB/G2B.db";
        String sql = "DELETE FROM Kellner WHERE SVNr = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, SVNr);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }

    public void delete_b(int TischNr) {
        //deletes the whole row of the parameter value from 'Bestellung'

        //String url = "jdbc:sqlite:C:/Users/Christoph/Schule/POS_3CHIF/POS-Project/G2B-Database/G2B.db";
        String url = "jdbc:sqlite:DB/G2B.db";
        String sql = "DELETE FROM Bestellung WHERE TischNr = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, TischNr);
            // execute the delete statement
            pstmt.executeUpdate();
            System.out.println("deleted");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }

}
