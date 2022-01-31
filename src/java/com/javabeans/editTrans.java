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

@WebServlet(name = "editTrans", urlPatterns = {"/editTrans"})
public class editTrans extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        int row;
        String eid = request.getParameter("id_transaksi");

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/user1", "azis", "azis");

            pst = con.prepareStatement("SELECT * FROM tb_transaksi WHERE id_transaksi = ?");
            pst.setString(1, eid);
            rs = pst.executeQuery();

            while (rs.next()) {
                out.println("<h1 align='center'>Data Barang</h1>");
                out.println("<form action='editservlettrans' method='POST'>");
                out.println("<table  align='center'>");
                out.println("<tr><td>Kode Transaksi</td> <td> <input type='text' name='idbrg' id='idbrg' value='" + rs.getString("id_transaksi") + "'/> </td></tr>");
                out.println("<tr><td>Nama Barang</td> <td> <input type='text' name='namabrg' id='namabrg' value='" + rs.getString("nama") + "'/> </td></tr>");
                out.println("<tr><td>Harga Barang</td> <td> <input type='text' name='hrg' id='hrg' value='" + rs.getString("harga") + "'/></td></tr>");
                out.println("<tr><td>Jumlah</td> <td> <input type='text' name='jml' id='jml' value='" + rs.getString("jumlah") + "'/></td></tr>");
                out.println("<tr><td colspan='2'> <input type='submit' value='Edit'/></td></tr>");
                out.println("</table>");
                out.println("</form>");
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
