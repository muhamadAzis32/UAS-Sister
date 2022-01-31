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

@WebServlet(name = "deleteTrans", urlPatterns = {"/deleteTrans"})
public class deleteTrans extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        int row;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/user1", "azis", "azis");
            String kd = request.getParameter("id_transaksi");

            pst = con.prepareStatement("DELETE FROM tb_transaksi where id_transaksi = ?");
            pst.setString(1, kd);
            row = pst.executeUpdate();

            out.println("<h1> Berhasil Dihapus </h1>");
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
