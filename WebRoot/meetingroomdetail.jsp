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
    
    <title>My JSP 'meetingroomdetail.jsp' starting page</title>
    
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
    	会议预定 &gt;修改会议室信息
    	</div>
    	<form action="UpdateMeetingRoomServlet" method="post">
    		<fieldset>
    			<legend>会议室信息</legend>
    			<table>
    				<tr>
    					<td>门牌号:</td>
    					<td>
    						<input type="hidden" name="roomid" value='${requestScope.room.roomid }'>
    						<input type="text" name="roomnum" maxlength="10" value='${requestScope.room.roomnum }'>
    					</td>
    				</tr>
    				<tr>
    					<td>会议室名称：</td>
    					<td>
    						<input name="roomname" type="text" maxlength="20" value='${requestScope.room.roomname }'>
    					</td>
    				</tr>
    				<tr>
    					<td>最多容纳人数：</td>
    					<td>
    						<input name="capacity" type="text" value='${requestScope.room.capacity }'>
    					</td>
    				</tr>
    				<tr>
    					<td>当前状态：</td>
    					<td>
    					<c:if test="${requestScope.room.status eq 0 }">
    					<input type="radio" id="status" name="status" checked="checked" value="0"><label for="status">可用</label>
    					<input type="radio" id="status" name="status"  value="1"><label for="status">不可用</label>
    					</c:if>
    					<c:if test="${requestScope.room.status eq 1 }">
    					<input type="radio" id="status" name="status" value="0"><label for="status">可用</label>
    					<input type="radio" id="status" name="status" checked="checked" value="1"><label for="status">不可用</label>
    					</c:if>
    					</td>
    				</tr>
    				<tr>
    					<td>备注：</td>
    					<td>
    						<textarea maxlength="200" name="description" rows="5" cols="60" >${requestScope.room.description }</textarea>
    					</td>
    				</tr>
    				<tr>
    					<td colspan="2">
								<input type="submit" value="确认修改">    					
								<input type="button" value="返回" onclick="window.history.back()">
    					</td>
    				</tr>
    			</table>
    		</fieldset>
    	</form>
    </div>
  </body>
</html>
