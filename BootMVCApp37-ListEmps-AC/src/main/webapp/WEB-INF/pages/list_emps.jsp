<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1 style="color:red;text-align:center"> Employee Report </h1>
<c:choose>
    <c:when test="${!empty empList}">
       <table border="1"  bgcolor="cyan">
         <tr> <th>EmpNo</th><th>EMPNAME</th><th>JOB</th><th>SALARY</th><th> DEPTNO</th> </tr>
          <c:forEach var="dto"  items="${empList}">
              <tr>
                 <td>${dto.eno}</td>
                 <td>${dto.ename}</td>
                 <td>${dto.job}</td>
                 <td>${dto.salary}</td>
                 <td>${dto.deptNo}</td>
              </tr>
          </c:forEach>
       </table>
    </c:when>
    <c:otherwise>
        <h1 style="color:red;text-align:center"> No Records found </h1>
    </c:otherwise>
</c:choose>
<br><br>
 <a href="home.htm">home</a>
