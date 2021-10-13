<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

    <%
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hometraining?serverTimezone=UTC&useSSL=false");
     PreparedStatement statement = con.prepareStatement("select distinct name from routine where id=?");
    statement.setString(1, request.getParameter("UserId"));
    ResultSet rs = statement.executeQuery();
    while(rs.next()){
        response.getWriter().write(rs.getString(1)+",");
        }
    con.close();

    %>
