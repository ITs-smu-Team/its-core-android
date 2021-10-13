<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

    <%
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hometraining?serverTimezone=UTC&useSSL=false");
    PreparedStatement statement = con.prepareStatement("select * from guide where name=?");
    statement.setString(1, request.getParameter("guidename"));
    ResultSet rs = statement.executeQuery();

    if(rs.next()){
                response.getWriter().write(rs.getString(5)+"$"+rs.getString(6)+"$"+rs.getString(3)+"$");
                  } else{
                        response.getWriter().write("error");
                        }
                        con.close();
        %>
