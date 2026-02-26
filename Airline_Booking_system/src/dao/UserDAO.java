package dao;

import db.DBConnection;
import java.sql.*;

public class UserDAO {

    public boolean login(String username, String password){

        try{
            Connection con = DBConnection.getConnection();

            if(con == null)
                return false;

            Statement st = con.createStatement();

            String sql =
            "SELECT * FROM users WHERE username='"
            + username.trim()
            + "' AND password='"
            + password.trim()
            + "'";

            ResultSet rs = st.executeQuery(sql);

            if(rs.next()){
                con.close();
                return true;
            }

            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
}