<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

<%
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hometraining?serverTimezone=UTC&useSSL=false");
            PreparedStatement statement = con.prepareStatement("update members set height=?,weight=?,team=? where id=?");
            statement.setString(1, request.getParameter("height"));
            statement.setString(2, request.getParameter("weight"));
            statement.setString(3, request.getParameter("team"));
            statement.setString(4, request.getParameter("userId"));
            int ret=statement.executeUpdate();
            if(ret == 0){
                    response.getWriter().write("error");
            }else{
                    response.getWriter().write("success");}
            con.close();
%>
