<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <h1 style="color:red;text-align:center"> Our Login page </h1>
 <body color="cyan">
 <form action="j_spring_security_check" method="POST">
   enter username :: <input type="text" name="j_username"> <br>
   enter password :: <input type="password" name="j_password"> <br> 
   <input type="submit"  value="Login"/>
 </form>
 </body>

