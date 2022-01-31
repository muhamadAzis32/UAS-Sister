package com.javabeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class validate {
    public static boolean checkUser(String nama,String pass) 
    {
        boolean st =false;
        try {

            //loading drivers for mysql
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/user1","azis", "azis");
            PreparedStatement ps = con.prepareStatement("select * from tuser where nama=? and password=?");
            ps.setString(1, nama);
            ps.setString(2, pass);
            ResultSet rs =ps.executeQuery();
            st = rs.next();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;                 
    }   
}
