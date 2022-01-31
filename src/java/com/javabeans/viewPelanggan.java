package com.javabeans;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "viewPelanggan", urlPatterns = {"/viewPelanggan"})
public class viewPelanggan extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        int row;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/user1", "azis", "azis");

            String sql;
            sql = "SELECT * FROM tb_pelanggan";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            out.println("<style>\n"
                    + "table {\n"
                    + "  border-collapse: collapse; }\n"
                    + "th, td {\n"
                    + "  text-align: left;\n"
                    + "  padding: 8px;\n"
                    + "}\n"
                    + "\n"
                    + "tr:nth-child(even) {background-color: #f2f2f2;}\n"
                    + "</style>");
            out.println("<h1 align='center'>Data Master Pelanggan</h1>");
            out.println("<center><button><a href=\"pelanggan.html\">Tambah Data</a></button> "
                    + " <button><a href=\"navbar.html\">Menu Utama</a></button> </center><br>");
            out.println("<table border='1' align='center'");
            out.println("<tr>");
            out.println("<td>Kode Pelanggan</td>");
            out.println("<td>Nama Pelanggan</td>");
            out.println("<td align='center'>Action</td>");
            out.println("</tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("id_pelanggan") + "</td>");
                out.println("<td>" + rs.getString("nama") + "</td>");

                out.println("<td>" + "<a href='editPlg?id_pelanggan=" + rs.getString("id_pelanggan") + "'>Edit | <a href='deletePlg?id_pelanggan=" + rs.getString("id_pelanggan") + "'>Delete</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
