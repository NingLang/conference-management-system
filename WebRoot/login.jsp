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

  </head>
  
  <body>
  	<p><strong>欢迎使用lang会议管理系统！！</strong></p>
    <div>
    
    <form action="LoginServlet" method ="post">
    	<fieldset>
    	<legend>登录信息</legend>
    	
    	<table align="center">
    	<c:if test="${requestScope.msg!=null }">
    		<tr>
    			<td>提示信息：</td>
    			<td>
    				<font color ='red'>${requestScope.msg}</font>
    			</td>
    		</tr>
    		</c:if>
    		<tr>
    			<td>用户名:</td>
    			<td><input type="text" name="username"></td>
    		</tr>
    		<tr>
    			<td>密码:</td>
    			<td><input type="password" name="pwd"></td>
    		</tr>
    		<tr>
    			<td>
    			是否要简化登录：
    			</td>
    			<td>
    				<select name="timelength">
    					<option value="0" selected>每次都需要登录</option>
    					<option value="10">10天内</option>
    					<option value="30">30天内</option>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<td><input type="submit" value="登录"></td>
    			<td><input type="button" value="注册" class="clickbutton" onclick="window.location.href='ViewAllDepartmentsServlet?code=regist'"></td>
    		</tr>
    	</table>
    	
    	</fieldset>
    </form>
    </div>
  </body>
</html>
