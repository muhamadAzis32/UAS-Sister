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

@WebServlet(name = "editBrg", urlPatterns = {"/editBrg"})
public class editBrg extends HttpServlet {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int row;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String eid = request.getParameter("id_barang");
        
        try{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/user1","azis", "azis");

        pst = con.prepareStatement("SELECT * FROM tb_barang WHERE id_barang = ?");
        pst.setString(1, eid);
        rs = pst.executeQuery();

        while(rs.next()){
            out.println("<h1 align='center'>Edit Data Barang</h1>");
            out.println("<form action='editservletbrg' method='POST'>");
            out.println("<table  align='center'>");
            out.println("<tr><td>Kode Barang</td> <td> <input type='text' name='idbrg' id='idbrg' value='"+rs.getString("id_barang")+"'/> </td></tr>");
            out.println("<tr><td>Kategori</td> <td> <input type='text' name='ktg' id='ktg' value='"+rs.getString("kategori")+"'/> </td></tr>");
            out.println("<tr><td>Nama Barang</td> <td> <input type='text' name='namabrg' id='namabrg' value='"+rs.getString("nama")+"'/></td></tr>");
            out.println("<tr><td>Harga</td> <td> <input type='text' name='hrg' id='hrg' value='"+rs.getString("harga")+"'/></td></tr>");
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
