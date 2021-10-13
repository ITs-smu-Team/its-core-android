<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

<%
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hometraining?serverTimezone=UTC&useSSL=false");
       PreparedStatement statement = con.prepareStatement("select rank() over(partition by r.team order by point desc),r.id,r.point,m.name from members as m join ranking as r on m.id=r.id where r.team=?");
        statement.setString(1, request.getParameter("userTeam"));
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
                response.getWriter().write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+",");
        }
        con.close();

%>
