package dao;

import db.DBConnection;
import java.sql.*;

public class BookingDAO {

    public boolean bookTicket(String username,String scheduleId,
                              String name,String age,String gender,
                              String proofT,String proofNo,String seat){

        try{
            Connection con=DBConnection.getConnection();
            Statement st=con.createStatement();

            // ---------- GET USER ID ----------
            ResultSet rs=st.executeQuery(
            "SELECT user_id FROM users WHERE username='"+username+"'");

            rs.next();
            int userId=rs.getInt(1);

            // ---------- CREATE BOOKING ----------
            int bookingId=(int)(Math.random()*100000);

            st.executeUpdate(
            "INSERT INTO booking VALUES("+bookingId+","+userId+","+scheduleId+",SYSDATE,'BOOKED')");

            // ---------- GET SEAT ID ----------
            ResultSet rsSeat=st.executeQuery(
            "SELECT seat_id FROM seat WHERE seat_number='"+seat+"'");

            rsSeat.next();
            int seatId=rsSeat.getInt(1);

            // ---------- INSERT PASSENGER ----------
            int passengerId=(int)(Math.random()*100000);

            st.executeUpdate(
            "INSERT INTO passenger VALUES("+passengerId+","+bookingId+","+seatId+",'"+
            name+"',"+age+",'"+gender+"','"+proofT+"','"+proofNo+"')");

            // ---------- UPDATE SEAT STATUS ----------
            st.executeUpdate(
            "UPDATE seat SET seat_status='BOOKED' WHERE seat_id="+seatId);

            con.close();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
}