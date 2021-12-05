package com.jnit1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoctorRegister extends HttpServlet {

	Connection con = null;
	PreparedStatement ps = null;

	public void init(ServletConfig config) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/appointmentapplication";
		String username = "root";
		String password = "root";
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String specialist = request.getParameter("specialist");

		try {

			ps = con.prepareStatement("insert into doctorregister(name,email,password,phone,specialist) value(?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setInt(4, phone);
			ps.setString(5, specialist);
			int x = ps.executeUpdate();
			PrintWriter pw = response.getWriter();
			pw.println("<html> <body bgcolor='wheat'>");

			if (x != 0)
				response.sendRedirect("doctor_login.html");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
