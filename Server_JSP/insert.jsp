
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

<%
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hometraining?serverTimezone=UTC&useSSL=false");
        String id=request.getParameter("userId");
        String rtname = request.getParameter("routine_name");
        String ename= request.getParameter("exc_name");
        String enname= request.getParameter("engexc_name");
        if(ename!=null){
                String exc_name[]=ename.split(",");
                String exc_engname[] = enname.split(",");
                for(int i =0;exc_name.length >i;i++)
                {
                PreparedStatement statement = con.prepareStatement("insert into routine(id,name,exc_name,exc_engname) values (?,?,?,?)");
                statement.setString(1, id);
                statement.setString(2, rtname);
                statement.setString(3, exc_name[i]);
                statement.setString(4, exc_engname[i]);
                int rs = statement.executeUpdate();
                }
        }
        con.close();

%>
