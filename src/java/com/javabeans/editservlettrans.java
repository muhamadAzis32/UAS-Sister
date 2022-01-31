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

@WebServlet(name = "editservlettrans", urlPatterns = {"/editservlettrans"})
public class editservlettrans extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
            String kd_trans = "", nama = "";
            int harga, jumlah, total;
            kd_trans = request.getParameter("idbrg");
            nama = request.getParameter("namabrg");
            harga = Integer.parseInt(request.getParameter("hrg"));
            jumlah = Integer.parseInt(request.getParameter("jml"));
            total = harga * jumlah;

            pst = con.prepareStatement("UPDATE tb_transaksi SET  nama = ?, harga = ? , jumlah = ?, total = ? where id_transaksi = ?");
            pst.setString(1, nama);
            pst.setInt(2, harga);
            pst.setInt(3, jumlah);
            pst.setInt(4, total);
            pst.setString(5, kd_trans);
            row = pst.executeUpdate();

            out.println("<h1>Berhasil Diedit</h1>");
            out.println("<button><a href=\"viewTransaksi\">View Data</a></button>");
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
