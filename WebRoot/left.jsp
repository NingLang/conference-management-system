<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
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
  		<h3>个人中心</h3>
  		<dl>
			<dd><a href="MyNotificationServlet" target=main>最新通知</a></dd>
			<dd><a href="ViewMyBookingServlet" target=main>我的预定</a></dd>
			<dd><a href="ViewMyMeetingsServlet" target=main>我的会议</a></dd>
		</dl>
		<h3>人员管理</h3>	 
		<dl>
			<dd><a href="ViewAllDepartmentsServlet?code=Viewalldepartments" target=main>部门管理</a></dd>
			<dd><a href="ViewAllEmployeesServlet?code=approve" target=main>注册审批</a></dd>
			<dd><a href="searchemployees.jsp" target=main>搜索员工</a></dd>	
  		</dl>
  		<h3>会议预定</h3>	 
		<dl>
			<dd><a href="addmeetingroom.jsp" target=main>添加会议室</a></dd>
			<dd><a href="ViewAllMeetingRoomsServlet" target=main>查看会议室</a></dd>
			<dd><a href="BookMeetingServlet?code=prepare" target=main>预定会议</a></dd>	
  		</dl>
  	</div>
  </body>
</html>
