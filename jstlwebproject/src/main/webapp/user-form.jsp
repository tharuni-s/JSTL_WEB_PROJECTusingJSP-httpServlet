<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul><li><a href="<%=request.getContextPath()%>/list"></a></li></ul>
<c:if test="${user!=null}">
<form action="update" method="post"></form>
</c:if>
<c:if test="${user==null}">
<form action="insert" method="post"></form>
</c:if>
<h2>
<c:if test="${user!=null}">Edit User</c:if>
<c:if test="${user==null}">Add new User</c:if>
</h2>
<c:if test="${user!=null}">
<form action="<%=request.getContextPath() %>/insert" method="post">
<input type="hidden" name="id" value="<c:out value='${user.id}'/>"></</c:if>
<label>User Name</label><input type="text" value="<c:out value='${user.name}'/>">
<label>User Email</label><input type="text" value="<c:out value='${user.email}'/>">
<label>User Country</label><input type="text" value="<c:out value='${user.country}'/>">
<input type="submit">
</body>
</html>