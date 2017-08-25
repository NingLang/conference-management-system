<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String datetime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); //获取系统时间 
request.setAttribute("currentTime",datetime);
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mybookings.jsp' starting page</title>
    
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
    		个人中心&gt;我的预定
    	</div>
    	<table>
    		<caption>我预定的会议：</caption>
    		<tr>
    			<th>会议名称</th>
    			<th>会议室名称</th>
    			<th>会议开始时间</th>
    			<th>会议结束时间</th>
    			<th>会议预定时间</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach var="item" items="${requestScope.map }">
    		<tr>
    			<td>${item.key.meetingname }</td>
    			<td>${item.value }</td>
    			<td>${item.key.starttime }</td>
    			<td>${item.key.endtime }</td>
    			<td>${currentTime}</td>
    			<c:choose>
    						<c:when test="${item.key.canceledtime==null }">
    						<td>
    						<a href="ViewMyBookingDetailServlet?meetingid=${item.key.meetingid }" target=main >查看(可取消)</a>
    						</td>
    						</c:when>
    						<c:otherwise>
    						<c:if test="${currentTime>item.key.canceledtime}">
    						<td>
    						<a href="ViewMyBookingDetailServlet?meetingid=${item.key.meetingid }" target=main >查看(已取消)</a>
    						</td>
    						</c:if>
    						<c:if test="${!(currentTime>item.key.canceledtime)}">
    						<td>
    						<a href="ViewMyBookingDetailServlet?meetingid=${item.key.meetingid }" target=main >查看(可取消)</a>
    						</td>
    						</c:if>
    						</c:otherwise>
    			</c:choose>
    		</tr>
    		</c:forEach>
    	</table>
    </div>
  </body>
</html>
