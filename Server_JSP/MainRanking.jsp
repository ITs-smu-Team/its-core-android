<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

<%
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hometraining?serverTimezone=UTC&useSSL=false");
        PreparedStatement statement = con.prepareStatement("select * from(select rank () over(order by point desc) as ranking,id,point from ranking) as ranking where id=?");
        statement.setString(1, request.getParameter("userId"));
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
                response.getWriter().write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3));
        } else{
                response.getWriter().write("error");
        }
        con.close();

%>
