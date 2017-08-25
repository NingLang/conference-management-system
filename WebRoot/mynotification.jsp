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
    
    <title>My JSP 'mynotification.jsp' starting page</title>
    
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
    	<div>
    	个人中心&gt;最新通知
    	</div>
    	<table>
    		<caption>
    		未来7天我要参加的会议：
    		</caption>
    		<tr>
    			<th>会议名称</th>
    			<th>会议室</th>
    			<th>起始时间</th>
    			<th>结束时间</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach var="item" items="${requestScope.meetingsMap }">
    		<tr>
    			<td>${item.key.meetingname }</td>
    			<td>${item.value }</td>
    			<td>${item.key.starttime }</td>
    			<td>${item.key.endtime }</td>
    			<td>
    				<a href="ViewMyMeetingDetail?meetingid=${item.key.meetingid }">查看详情</a>
    			</td>
    		</tr>
    		</c:forEach>
    	</table>
    	<table>
    		<caption>已取消的会议</caption>
    		<tr>
    			<th>会议名称</th>
    			<th>会议室</th>
    			<th>起始时间</th>
    			<th>结束时间</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach var="item" items="${requestScope.cancelMeetingsMap }">
    		<tr>
    			<td>${item.key.meetingname }</td>
    			<td>${item.value }</td>
    			<td>${item.key.starttime }</td>
    			<td>${item.key.endtime }</td>
    			<td>
    				<a href="ViewMyMeetingDetail?meetingid=${item.key.meetingid }">查看详情</a>
    			</td>
    		</tr>
    		</c:forEach>
    	</table>
    </div>
  </body>
</html>
