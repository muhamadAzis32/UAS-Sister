package com.javabeans;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "editservletbrg", urlPatterns = {"/editservletbrg"})
public class editservletbrg extends HttpServlet {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int row;
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String eid = request.getParameter("id_barang");

        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/user1","azis", "azis");
            String kd_brg="", ktg="", nama="", harga="";
            kd_brg = request.getParameter("idbrg");
            ktg = request.getParameter("ktg");
            nama = request.getParameter("namabrg");
            harga = request.getParameter("hrg");
            
            pst = con.prepareStatement("UPDATE tb_barang SET kategori = ?, nama = ?, harga = ? where id_barang = ?");
            pst.setString(1, ktg); 
            pst.setString(2, nama);
            pst.setString(3, harga);
            pst.setString(4, kd_brg);
            row = pst.executeUpdate();

            out.println("<h1> Berhasil Diedit! </h1>");
            out.println("<button><a href=\"viewBarang\">View Data</a></button>");
        }catch(Exception e){
            out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
