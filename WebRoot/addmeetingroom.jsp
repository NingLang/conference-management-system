<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addmeetingroom.jsp' starting page</title>
    
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
    	会议预定&gt;添加会议室
    	</div>
    	<form	method="post" action="AddMeetingRoomServlet">
    		<fieldset>
    			<legend>会议室信息</legend>
    			<table>
    				<tr>
    					<td>门牌号：</td>
    					<td>
    						<input type="text" name="roomnum" placeholder="例如：201" maxlength="10">
    					</td>
    				</tr>
    				<tr>
    					<td>会议室名称：</td>
    					<td>
    						<input type="text" name="roomname" placeholder="例如：第一会议室" maxlength="10">
    					</td>
    				</tr>
    				<tr>
    					<td>容纳人数：</td>
    					<td>
    						<input type="text" name="capacity" maxlength="10">
    					</td>
    				</tr>
    				<tr>
    					<td>当前状态：</td>
    					<td>
    					 <input type="radio" id="status" name="status" checked="checked" value="0"><label for="status">可用</label>
    					 <input type="radio" id="status" name="status"  value="1"><label for="status">不可用</label>
    					</td>
    				</tr>
    				<tr>
    					<td>备注：</td>
    					<td>
    						<textarea maxlength="200" name="description" rows="5" cols="60" ></textarea>
    					</td>
    				</tr>
    				<tr>
    					<td colspan="2">
								<input type="submit" value="添加">    					
								<input type="reset" value="重置">
    					</td>
    				</tr>
    			</table>
    		</fieldset>
    		
    	</form>
    </div>
  </body>
</html>
