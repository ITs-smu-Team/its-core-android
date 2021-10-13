<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"
    import="java.util.Date" 
    import="java.text.SimpleDateFormat"%>

<%
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hometraining?serverTimezone=UTC&useSSL=false");
        PreparedStatement statement = con.prepareStatement("update ranking set point=? where id=?");
                statement.setString(1, request.getParameter("userScore"));
        statement.setString(2, request.getParameter("userId"));
        int ret=statement.executeUpdate();

        int total_exc =Integer.parseInt(request.getParameter("total_exc"));
        String last_exc=request.getParameter("last_exc");
        Date now = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        String today = date.format(now);

        if(last_exc.equals(today)){}
        else{
                total_exc++;
                PreparedStatement statement1 = con.prepareStatement("update members set  total_exc=?,last_exc=? where id=?");
                statement1.setInt(1, total_exc);
                statement1.setString(2, today);
                statement1.setString(3, request.getParameter("userId"));
                int ret1=statement1.executeUpdate();
        }
        response.getWriter().write(total_exc+","+today);
        con.close();
%>


%>
