/**@author Christoph \n @since (datum) */

package database;

import java.sql.*;

public class DBConnect {

    //create coonstructor for the database
    public static void DBConnect() {

        Connection conn = null;

        try {

            String url = "jdbc:sqlite:../databasetest.db";
            conn = DriverManager.getConnection(url);
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

    //create database in sqlite
    public static void DBcreate(String fileName) {

        String url = "jdbc:sqlite:database/" + fileName;
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

    //create database in sqlite
    public static void TableCreate() {

        // SQLite connection string
        String url = "jdbc:sqlite:../databasetest.db";
        // SQL statement for creating a new table
        String t_kellner = "CREATE TABLE IF NOT EXISTS Kellner (\n"
                + "	SVNr varchar PRIMARY KEY,\n"
                + "	vname varchar NOT NULL,\n"
                + "	fname varchar NOT NULL,\n"
                + "	pw varchar NOT NULL,\n"
                + "	PLZ integer NOT NULL,\n"
                + "	Stadt varchar NOT NULL\n"
                + ");";

        String t_bestellung = "CREATE TABLE IF NOT EXISTS Bestellung (\n"
                + "	Zeit varchar PRIMARY KEY,\n"
                + "	TischNr integer,\n"
                + "	SVNr varchar REFERENCES Kellner,\n"
                + "	PersonNr varchar NOT NULL,\n"
                + "	Reservierung varchar NOT NULL,\n"
                + "	Essen varchar NOT NULL,\n"
                + "	Getraenk varchar NOT NULL,\n"
                + "	Preis decimal(5, 2) NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
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
        //inserting into the servant table
        String url = "jdbc:sqlite:../databasetest.db";
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

    public void insert_b(String Zeit, int TischNr, String SVNr, String PersonNr, String Reservierung, String Essen, String Getraenk, double Preis) {
        //inserting into the orders table
        String url = "jdbc:sqlite:../databasetest.db";
        String sql = "INSERT INTO Bestellung(Zeit, TischNr, SVNr, PersonNr, Reservierung, Essen, Getraenk, Preis) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, Zeit);
            pstmt.setInt(2, TischNr);
            pstmt.setString(3, SVNr);
            pstmt.setString(4, PersonNr);
            pstmt.setString(5, Reservierung);
            pstmt.setString(6, Essen);
            pstmt.setString(7, Getraenk);
            pstmt.setDouble(8, Preis);
            pstmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    public void delete_k(String SVNr) {
        //deleting a row in the servant table
        String url = "jdbc:sqlite:../databasetest.db";
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

    public void delete_b(String Zeit) {
        //deleting a row in the order table
        String url = "jdbc:sqlite:../databasetest.db";
        String sql = "DELETE FROM Bestellung WHERE Zeit = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, Zeit);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }

}