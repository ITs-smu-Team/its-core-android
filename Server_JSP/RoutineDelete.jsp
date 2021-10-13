<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

<%
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hometraining?serverTimezone=UTC&useSSL=false");
         PreparedStatement statement = con.prepareStatement("delete from routine where id=? and name=?");

        statement.setString(1, request.getParameter("userId"));
        statement.setString(2, request.getParameter("routine_name"));

        int rs = statement.executeUpdate();
        if(rs==0){
                response.getWriter().write("error");
        } else{
                response.getWriter().write("success");
        }
        con.close();

%>
