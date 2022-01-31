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

@WebServlet(name = "editservletplg", urlPatterns = {"/editservletplg"})
public class editservletplg extends HttpServlet {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int row;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String eid = request.getParameter("id_pelanggan");

        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/user1", "azis", "azis");
            String kd_plg="", nama="";
            kd_plg = request.getParameter("idplg");
            nama = request.getParameter("namaplg");
            
            pst = con.prepareStatement("UPDATE tb_pelanggan set nama = ? where id_pelanggan = ?");
            pst.setString(1, nama); 
            pst.setString(2, kd_plg);
            row = pst.executeUpdate();

            out.println("<h1> Berhasil Diedit </h1>");
            out.println("<button><a href=\"viewPelanggan\">View Data</a></button>");
        }catch(Exception e){
            out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
