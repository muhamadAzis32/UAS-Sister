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

@WebServlet(name = "viewTransaksi", urlPatterns = {"/viewTransaksi"})
public class viewTransaksi extends HttpServlet {

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
            sql = "SELECT * FROM tb_transaksi";
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
            out.println("<h1 align='center'>Data Master Transaksi</h1>");
            out.println("<center><button><a href=\"transaksi.html\">Tambah Data</a></button> "
                    + "<button><a href=\"navbar.html\">Menu Utama</a></button> </center><br>");
            out.println("<table border='1' align='center'");
            out.println("<tr>");
            out.println("<td>Kode Transaksi</td>");
            out.println("<td>Nama Barang</td>");
            out.println("<td>Harga Barang</td>");
            out.println("<td>Jumlah Barang</td>");
            out.println("<td>Total Bayar</td>");
            out.println("<td align='center'>Action</td>");
            out.println("</tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("id_transaksi") + "</td>");
                out.println("<td>" + rs.getString("nama") + "</td>");
                out.println("<td>" + rs.getString("harga") + "</td>");
                out.println("<td>" + rs.getString("jumlah") + "</td>");
                out.println("<td>" + rs.getString("total") + "</td>");

                out.println("<td>" + "<a href='editTrans?id_transaksi=" + rs.getString("id_transaksi") + "'>Edit | <a href='deleteTrans?id_transaksi=" + rs.getString("id_transaksi") + "'>Delete</td>");
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
