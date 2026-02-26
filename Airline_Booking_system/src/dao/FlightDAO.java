package dao;

import db.DBConnection;
import java.sql.*;
import java.util.*;

public class FlightDAO {

    // ---------- SEARCH FLIGHTS ----------
    public List<String[]> searchFlights(String src,String dest,String date){

        List<String[]> list = new ArrayList<>();

        try{
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            String sql =
            "SELECT f.flight_number,f.airline_name,fs.departure_time,"+
            "fs.arrival_time,fs.available_seats,fs.schedule_id "+
            "FROM flight f JOIN flight_schedule fs "+
            "ON f.flight_id=fs.flight_id "+
            "WHERE LOWER(f.source_airport)=LOWER('"+src+"') "+
            "AND LOWER(f.destination_airport)=LOWER('"+dest+"') "+
            "AND TRUNC(fs.journey_date)=TO_DATE('"+date+"','DD-MM-YYYY')";

            System.out.println(sql);

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                String row[] = new String[6];
                row[0]=rs.getString(1);
                row[1]=rs.getString(2);
                row[2]=rs.getString(3);
                row[3]=rs.getString(4);
                row[4]=rs.getString(5);
                row[5]=rs.getString(6);
                list.add(row);
            }

            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }


    // ---------- LOAD AIRPORT LIST ----------
    public Set<String> getAirports(){

        Set<String> set=new TreeSet<>();

        try{
            Connection con=DBConnection.getConnection();
            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery(
            "SELECT source_airport FROM flight UNION SELECT destination_airport FROM flight");

            while(rs.next())
                set.add(rs.getString(1));

            con.close();
        }
        catch(Exception e){ e.printStackTrace(); }

        return set;
    }
}