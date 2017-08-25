<%@page import="com.lang.meeting.vo.Employee"%>
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
    
    <title>Lang会议管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
     td {text-align:center}
  </style>

  </head>
  
  <body>
    <div>
    人员管理&gt;注册审批
    </div>
    <table class="listtable">
    	<caption>所有待审批注册信息</caption>
    	<tr class="listheader">
				<th>姓名</th>    	
				<th>账号名</th>    	
				<th>联系电话</th>    	
				<th>电子邮件</th>    	
				<th>操作</th>    	
    	</tr>
    	<c:forEach var="emp" items="${requestScope.employeeslist }">
    	<tr >
    		<td>${emp.employeename}</td>
    		<td>${emp.username}</td>
    		<td>${emp.phone }</td>
    		<td>${emp.email }</td>
    		<td>
    			<input type="button" class="clickbuttn" value="通过" onclick="window.location.href='ApproveServlet?employeeid=${emp.employeeid}&oper=yes'">
    			<input type="button" class="clickbuttn" value="不通过" onclick="window.location.href='ApproveServlet?employeeid=${emp.employeeid}&oper=no'">
    		</td>
    	</tr>
    	</c:forEach>
    </table>
  </body>
</html>
