
package com.javabeans;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "pelanggan", urlPatterns = {"/pelanggan"})
public class pelanggan extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
            Connection con = null;
            Statement stmt = null;
            String kd_brg="", nama="";
            kd_brg = request.getParameter("idplg");
            nama = request.getParameter("namaplg");
            
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/user1", "azis", "azis");
                stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO tb_pelanggan values('"+kd_brg+"','"+nama+"')");
                out.println("<h1>Berhasil Disimpan!<h1>");
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
