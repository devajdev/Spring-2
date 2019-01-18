<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <h1 style="color:red;text-align:center">Employee Registration Example </h1>
   
    <form:form  method="POST" commandName="empCmd">
     <p style="color:red">
       <form:errors path="*"/>
       </p>
       Employee number :: <form:input path="eno"/> <br>
         Employee name :: <form:input path="ename"/> <br>
       Employee job :: <form:input path="job"/> <br>
      Employee salary :: <form:input path="salary"/> <br>
      <input type="submit"  value="register">
    </form:form>
   


</body>
</html>