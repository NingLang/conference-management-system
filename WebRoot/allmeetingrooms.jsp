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
    
    <title>My JSP 'allmeetingrooms.jsp' starting page</title>
    
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
    	<div>会议预定 &gt;查看会议室</div>
    	<table>
    		<caption>所有会议室：</caption>
    		<tr>
    			<th>门牌编号</th>
    			<th>会议室名称</th>
    			<th>容纳人数</th>
    			<th>当前状态</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach var="room" items='${requestScope.allmeetingrooms }'>
    		<tr>
    			<td>${room.roomnum }</td>
    			<td>${room.roomname }</td>
    			<td>${room.capacity }</td>
    			<c:if test="${room.status eq 0 }">
    			<td>可用</td>
    			</c:if>
    			<c:if test="${room.status eq 1 }">
    			<td>不可用</td>
    			</c:if>
    			<td>
    				<a href="ViewOneMeetingRoomServlet?roomid=${room.roomid }">查看详情</a>
    			</td>
    		</tr>
    		</c:forEach>
    	</table>
    </div>
  </body>
</html>
