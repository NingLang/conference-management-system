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
    
    <title>My JSP 'bookmeeting.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var xmlHttp;
		
		function createXMLHttpRequest(){
			if(window.ActiveXObject){
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			else if(window.XMLHttpRequest){
				xmlHttp = new XMLHttpRequest();
			}
		}
		
		function showEmployees(){
			createXMLHttpRequest();
			var deptid = document.getElementById("selDepartments").value;
			
			var url = "SelectEmployeesOfDeptServlet?departmentid=" + escape(deptid);
			
			xmlHttp.open("GET", url, true);
			
			xmlHttp.onreadystatechange = callback;
			xmlHttp.send(null);
		}
		
		function callback(){
			clearEmployees();
			var selEmployees = document.getElementById("selEmployees");
			if (xmlHttp.readyState==4){
				if(xmlHttp.status == 200){
					var elements = xmlHttp.responseXML.getElementsByTagName("option");
					for(var i=0;i<elements.length;i++){
						var value = elements[i].getElementsByTagName("value")[0].firstChild.nodeValue;
						var text = elements[i].getElementsByTagName("text")[0].firstChild.nodeValue;
						selEmployees.options.add(new Option(text,value));
					}
				}
			}
		}
		
		function clearEmployees(list){
			document.getElementById("selEmployees").options.length=0;
		}
		
		function selectEmployees(){
			var selEmployees = document.getElementById("selEmployees");
			var selSelectedEmployees = document.getElementById("selSelectedEmployees");
			for(var i=0;i<selEmployees.options.length;i++){
				if(selEmployees.options[i].selected){
					var opt = new Option(selEmployees.options[i].text,selEmployees.options[i].value);
					opt.selected=true;
					selSelectedEmployees.options.add(opt);
					selEmployees.options.remove(i);
				}
			}
		}
		
		function deSelectEmployees(){
			var selEmployees = document.getElementById("selEmployees");
			var selSelectedEmployees = document.getElementById("selSelectedEmployees");
			for(var i=0;i<selSelectedEmployees.options.length;i++){
				if(selSelectedEmployees.options[i].selected){
					selEmployees.options.add(new 
							Option(selSelectedEmployees.options[i].text,selSelectedEmployees.options[i].value));
					selSelectedEmployees.options.remove(i);
				}
			}
			setSelected();
		}
		
		function setSelected(){
			var selSelectedEmployees = document.getElementById("selSelectedEmployees");
			for(var i=0;i<selSelectedEmployees.options.length;i++){
				selSelectedEmployees.options[i].selected=true;
			}
		}
		
		function refreshRooms(){
			createXMLHttpRequest();
			var starttime = document.getElementById("starttime").value;
			var endtime = document.getElementById("endtime").value;
			
			var url = "RefreshRoomsServlet?starttime=" + escape(starttime)+"&endtime="+escape(endtime);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = refresh;
			xmlHttp.send(null);
		}
		
		function refresh() {
			clearMeetingRooms();
			var roomid = document.getElementById("roomid");
			if(xmlHttp.readyState == 4){
				if(xmlHttp.status == 200){
					var elements = xmlHttp.responseXML.getElementsByTagName("option");
					for(var i=0; i<elements.length;i++){
						var value = elements[i].getElementsByTagName("value")[0].firstChild.nodeValue;
						var text = elements[i].getElementsByTagName("text")[0].firstChild.nodeValue;
						roomid.options.add(new Option(text,value),i+1);
					}
				}
			}
		}
		
		function clearMeetingRooms(){
			document.getElementById("roomid").options.length=1;
		}
	</script>
  </head>
  
  <body>
    <div>
    	<div>会议预定&gt;预定会议</div>
    	<form method="post" action="BookMeetingServlet">
    		<fieldset>
    			<legend>会议信息</legend>
    			<table>
    				<tr>
    					<td>会议名称：</td>
    					<td>
    						<input type="text" maxlength="20" id="meetingname" name="meetingname">
    					</td>
    				</tr>
    				<tr>
    					<td>预计参加人数：</td>
    					<td>
    						<input type="text" id="numofparticipants" name="numofparticipants">
    					</td>
    				</tr>
    				<tr>
    					<td>预计开始时间：</td>
    					<td><input class="wdate" type="text" id="starttime" name="starttime"
    							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
    					</td>
    				</tr>
    				<tr>
    					<td>预计结束时间：</td>
    					<td><input class="wdate" type="text" id="endtime" name="endtime"
    							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
    					</td>
    				</tr>
    				<tr>
    					<td>选择会议室：</td>
    					<td>
    						<select name="roomid" id="roomid" onfocus="refreshRooms()">
    						<option>请选择会议室</option>
    							<c:forEach var="room" items="${requestScope.roomsList }">
    								<option value="${room.roomid }">${room.roomname }</option>
    							</c:forEach>
    						</select>
    					</td>
    				</tr>
    				<tr>
    					<td>会议说明：</td>
    					<td>
    						<textarea id="description" name="description" rows="5"></textarea>
    					</td>
    				</tr>
    				<tr>
    					<td>选择参会人员：</td>
    					<td>
    							<select id="selDepartments" onchange="showEmployees()">
    								<option>请选择部门</option>
										<c:forEach var="dept" items="${requestScope.deptsList }">
											<option value="${dept.departmentid }">${dept.departmentname }
										</c:forEach>  								
    							</select>
    					</td>
    				</tr>
    				<tr>
    					<td></td>
    					<td >
    						<select  id="selEmployees" style="width:90.8px;height:150px" multiple="multiple">
    						</select>
    						<input type="button" value="&gt;"
    								onclick="selectEmployees()">
    						<input type="button" value="&lt;"
    								onclick="deSelectEmployees()">
    					</td>
    					
    					<td>
    						<select id="selSelectedEmployees" name="selSelectedEmployees" style="width:90.8px;height:150px" multiple="multiple"></select>
    					</td>
    				</tr>
    				<tr>
    					<td>
    						<input type="hidden" name="code" value="book">
    						<input type="submit" value="预定会议">
    					</td>
    				</tr>
    			</table>
    			
    		</fieldset>
    	
    	</form>
    </div>
  </body>
</html>
