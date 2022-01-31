
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


@WebServlet(name = "editPlg", urlPatterns = {"/editPlg"})
public class editPlg extends HttpServlet {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int row;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String eid = request.getParameter("id_pelanggan");
        
        try{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/user1", "azis", "azis");

        pst = con.prepareStatement("SELECT * FROM tb_pelanggan WHERE id_pelanggan = ?");
        pst.setString(1, eid);
        rs = pst.executeQuery();

        while(rs.next()){
            out.println("<h1 align='center'>Edit Data Pelanggan</h1>");
            out.println("<form action='editservletplg' method='POST'>");
            out.println("<table  align='center'>");
            out.println("<tr><td>Kode Pelanggan</td> <td> <input type='text' name='idplg' id='idplg' value='"+rs.getString("id_pelanggan")+"'/> </td></tr>");
            out.println("<tr><td>Nama Pelanggan</td> <td> <input type='text' name='namaplg' id='namaplg' value='"+rs.getString("nama")+"'/></td></tr>");
            out.println("<tr><td colspan='2'> <input type='submit' value='Edit'/></td></tr>");
            out.println("</table>");
            out.println("</form>");
            }
        }
        catch(Exception e){
        out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
