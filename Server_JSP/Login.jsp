<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

<%
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hometraining?serverTimezone=UTC&useSSL=false");
        PreparedStatement statement = con.prepareStatement("select m.num, m.id, m.pwd, m.name, m.team, m.age, m.gender, m.height, m.weight, r.point, m.total_exc, m.last_exc from members as m join ranking as r on m.id=r.id where m.id=? and m.pwd=?");
        statement.setString(1, request.getParameter("userId"));
        statement.setString(2, request.getParameter("userPwd"));
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
                response.getWriter().write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12));
        } else{
                response.getWriter().write("error");
        }
        con.close();


%>

