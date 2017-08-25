<%@page import="com.lang.meeting.vo.Department"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'departments.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div>
    人员管理&gt;部门管理
    </div>
    <form action="AddDeleteDepartmentServlet" method="post">
    	<fieldset>
    		<legend>所有部门</legend>
    		部门名称：
    		<input type="text" name="departmentname" maxlength="20">
    		<input type="hidden" name="code" value="add">
    		<input type="submit" value="添加">
    	</fieldset>
    </form>
    <c:if test="${requestScope.departmentslist!=null }">
    <table>
    	<caption>所有部门:</caption>
    	<tr>
    		<th>部门编号</th>
    		<th>部门名称</th>
    		<th>操作</th>
    	</tr>
    	<c:forEach var="dept" items="${requestScope.departmentslist}">
    	<tr>
    		<td>${dept.departmentid}</td>
    		<td>${dept.departmentname}</td>
    		<td><a class="clickbutton" href="AddDeleteDepartmentServlet?code=delete&departmentid=${dept.departmentid}">删除</a></td>
    	</tr>
    	</c:forEach>
    </table>
    </c:if>
  </body>
</html>
