package dao;

import db.DBConnection;
import java.sql.*;
import java.util.*;

public class SeatDAO {

    // -------- GET BOOKED SEATS --------
    public Set<String> getBookedSeats(String scheduleId){

        Set<String> booked=new HashSet<>();

        try{
            Connection con=DBConnection.getConnection();
            Statement st=con.createStatement();

            String sql =
            "SELECT s.seat_number "+
            "FROM passenger p "+
            "JOIN booking b ON p.booking_id=b.booking_id "+
            "JOIN seat s ON p.seat_id=s.seat_id "+
            "WHERE b.schedule_id="+scheduleId;

            ResultSet rs=st.executeQuery(sql);

            while(rs.next())
                booked.add(rs.getString(1));

            con.close();
        }
        catch(Exception e){ e.printStackTrace(); }

        return booked;
    }


    // -------- GET SEAT ID --------
    public int getSeatId(String seatNo,String scheduleId){

        try{
            Connection con=DBConnection.getConnection();
            Statement st=con.createStatement();

            String sql =
            "SELECT s.seat_id "+
            "FROM seat s "+
            "JOIN aircraft a ON s.aircraft_id=a.aircraft_id "+
            "WHERE s.seat_number='"+seatNo+"' "+
            "AND a.schedule_id="+scheduleId;

            ResultSet rs=st.executeQuery(sql);

            if(rs.next())
                return rs.getInt(1);

            con.close();
        }
        catch(Exception e){ e.printStackTrace(); }

        return -1;
    }
}