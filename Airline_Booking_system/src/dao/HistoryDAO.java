package dao;

import db.DBConnection;
import java.sql.*;
import java.util.*;

public class HistoryDAO {

    public List<String[]> getHistory(String username){

        List<String[]> list = new ArrayList<>();

        try{
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            String sql =
            "SELECT b.booking_id, f.flight_number, "+
            "f.source_airport, f.destination_airport, "+
            "p.passenger_name, "+
            "TO_CHAR(fs.journey_date,'DD-MM-YYYY'), "+
            "s.seat_number, b.booking_status "+
            "FROM booking b "+
            "JOIN users u ON b.user_id=u.user_id "+
            "JOIN flight_schedule fs ON b.schedule_id=fs.schedule_id "+
            "JOIN flight f ON fs.flight_id=f.flight_id "+
            "JOIN passenger p ON b.booking_id=p.booking_id "+
            "JOIN seat s ON p.seat_id=s.seat_id "+
            "WHERE u.username='"+username+"' "+
            "ORDER BY b.booking_id DESC";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                String row[] = new String[8];

                for(int i=0;i<8;i++)
                    row[i] = rs.getString(i+1);

                list.add(row);
            }

            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}