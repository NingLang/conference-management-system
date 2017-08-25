<%@page import="com.lang.meeting.vo.Department"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
	<script type="text/javascript">
					var xmlHttp;
					function createXMLHttpRequest(){
						//alert("调用createXMLHttpRequest");
						if(window.ActiveXObject){
							xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
						}
						else if(window.XMLHttpRequest){
							xmlHttp = new XMLHttpRequest();
						}
					}
					
					function validate(){
						//alert("调用validate");
						createXMLHttpRequest();
						var username = document.getElementById("username");
						var url = "ValidateUsernameServlet?username=" + escape(username.value);
						xmlHttp.open("GET",url,true);
						xmlHttp.onreadystatechange = callback;
						xmlHttp.send(null);
					}
					
					function callback(){
						if(xmlHttp.readyState==4){
							if(xmlHttp.status==200){
								//alert(xmlHttp.status);
								//alert(xmlHttp.responseXML.getElementsByTagName("message")[0].firstChild.data);
								var message = xmlHttp.responseXML.getElementsByTagName("message")[0].firstChild.data;
								var passed = xmlHttp.responseXML.getElementsByTagName("passed")[0].firstChild.data;
								setMessage(message,passed);
							}
						}
					}
					
					function setMessage(message,passed){
						var validateMessage = document.getElementById("validateMessage");
						var fontColor = "red";
						if(passed == "true"){
							fontColor = "green";
						}
						validateMessage.innerHTML = "<font color="+ fontColor +">" + message +"</font>";
					}
					function check(){
								if(form1.password1.value!=form1.password2.value){
									confirminfo.innerHTML="<font color=red>两次输入的密码不一致</font>";
								}else{
									confirminfo.innerHTML="<font color=green>两次输入的密码一致</font>";
								}
					}
	</script>

  </head>
  
  <body>
    <div>
   		<form id="form1" action="RegistServlet" method="post">
   			<fieldset>
   				<legend>员工信息</legend>
   				<table>
   					<tr>
   						<td>提示信息：</td>
   						<td>
   							<font color = 'red'>${requestScope.msg }</font>
   						</td>
   					</tr>
   					<tr>
   						<td>姓名：</td>
   						<td><input type="text" name="employeename" maxlength="20" value=${parem.employeename }></td>
   					</tr>
   					<tr>
   						<td>账户名：</td>
   						<td><input type="text" id="username"  name="username" maxlength="20" onchange="validate()" value=${parem.username }  >
   						</td>
   						<td id="validateMessage">
   						</td>
   					</tr>
   					<tr>
   						<td>密码：</td>
   						<td><input type="password" id="password1" name="password" maxlength="20" placeholder ="请输入6位以上密码"></td>
   						<td id="confirminfo1"></td>
   					</tr>
   					<tr>
   						<td>确认密码：</td>
   						<td><input type="password" id="password2" name="confirm" maxlength="20" onblur="check()"></td>
   						<td id="confirminfo"></td>
   					</tr>
   					<tr>
   						<td>联系电话：</td>
   						<td><input type="text" name="phone" maxlength="20" value=${parem.phone }></td>
   					</tr>
   					<tr>
   						<td>电子邮件</td>
   						<td><input type="text" name="email" maxlength="20" value=${parem.email }></td>
   					</tr>
   					<tr>
   						<td>所在部门：</td>
   						<td>
   							<select name="deptid">
   								<c:forEach var="department" items="${requestScope.departmentslist }">
   									<c:if test="${department.departmentid==param.deptid }">
   										<option value="${department.departmentid }" selected>${department.departmentname }</option>
   									</c:if>
   									<c:if test="${department.departmentid!=param.deptid }">
   										<option value="${department.departmentid }" >${department.departmentname }</option>
   									</c:if>
   							</c:forEach>
   							</select>
   						</td>
   					</tr>
   					<tr>
   						<td colspan="">
   						<input type="submit"class="clickbutton" value="注册">
   						<input type="reset" class="clickbutton" value="重置">
   						</td>
   					</tr>
   				</table>
   			</fieldset>	
   		</form>
    </div>
  </body>
</html>
