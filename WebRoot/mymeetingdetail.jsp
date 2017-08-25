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
    
    <title>My JSP 'mymeetingdetail.jsp' starting page</title>
    
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
    	会议预定&gt;修改会议预定
    	</div>
    	<form method="post" action="CancelMeetingServlet">
    		<fieldset>
    			<legend>会议信息</legend>
    			<table>
    				<tr>
    					<td>会议名称：</td>
    					<td>${requestScope.meeting.meetingname }</td>
    				</tr>
    				<tr>
    					<td>预计参会人数：</td>
    					<td>${requestScope.meeting.numberofparticipants }</td>
    				</tr>
    				<tr>
    					<td>预计开始时间：</td>
    					<td>${requestScope.meeting.starttime }</td>
    				</tr>
    				<tr>
    					<td>预计结束时间：</td>
    					<td>${requestScope.meeting.endtime }</td>
    				</tr>
    				<tr>
    					<td>会议说明：</td>
    					<td>${requestScope.meeting.description }</td>
    				</tr>
    				<tr>
    					<td>参会人员：</td>
    					<td>
    						<table>
    							<tr>
    								<th>姓名</th>
    								<th>联系电话</th>
    								<th>电子邮件</th>
    							</tr>
    							<c:forEach var="emp" items="${requestScope.employeesList }">
    							<tr>
    								<td>${emp.employeename }</td>
    								<td>${emp.phone }</td>
    								<td>${emp.email }</td>
    							</tr>
    							</c:forEach>
    						</table>
    					</td>
    				</tr>
    				<tr>
    					<td><input type="hidden" name="meetingid" value=${ requestScope.meeting.meetingid}></td>
    					
    						<c:if test="${currentTime>requestScope.meeting.canceledtime }">
    						<td><input type="button" value="会议已取消"></td>
    						</c:if>
    					<td><input type="button" value="返回" onclick="window.history.back();"></td>
    					
    				</tr>
    			</table>
    		</fieldset>
    	
    	</form>
    </div>
  </body>
</html>
