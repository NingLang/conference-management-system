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
    
    <title>My JSP 'searchemployees.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
			function goToOnePage(employeename,username,status){
				var pageNum = document.getElementById("pageNum").value;
				if(pageNum==""){
					window.location.href="#";
				}else{
					window.location.href="SearchEmployeesServlet?employeename="+employeename+"&username="+username+"&status="+ststus+"&pageNum="+pageNum;
				}
			}
	</script>

  </head>
  
  <body>
    <div>
    人员管理&gt;搜索员工
    </div>
    <form action="SearchEmployeesServlet" method="post">
    	<fieldset>
    		<legend>搜索员工</legend>
    		<table>
    			<tr>
    				<td>姓名：</td>
    				<td><input type="text" size="10px" name="employeename" value=${param.employeename }></td>
    				<td>账号名：</td>
    				<td><input type="text" size="10px" name="username" value=${param.username }></td>
    				<td>状态：</td>
    				<td>
    					<c:if test="${param.status eq null or param.status eq ''  }">
    						<input type="radio" name="status" value="1"><label>已批准</label>
    						<input type="radio" name="status" value="0"><label>待审批</label>
    						<input type="radio" name="status" value="2"><label>已关闭</label>
    						<input type="radio" name="status" value="" checked><label>所有</label>
    					</c:if>
    					<c:if test="${param.status eq '1' }">
    						<input type="radio" name="status" value="1" checked><label>已批准</label>
    						<input type="radio" name="status" value="0"><label>待审批</label>
    						<input type="radio" name="status" value="2"><label>已关闭</label>
    						<input type="radio" name="status" value=""><label>所有</label>
    					</c:if>
    					<c:if test="${param.status eq '0' }">
    						<input type="radio" name="status" value="1"><label>已批准</label>
    						<input type="radio" name="status" value="0" checked><label>待审批</label>
    						<input type="radio" name="status" value="2"><label>已关闭</label>
    						<input type="radio" name="status" value=""><label>所有</label>
    					</c:if>
    					<c:if test="${param.status eq '2' }">
    						<input type="radio" name="status" value="1"><label>已批准</label>
    						<input type="radio" name="status" value="0"><label>待审批</label>
    						<input type="radio" name="status" value="2" checked><label>已关闭</label>
    						<input type="radio" name="status" value=""><label>所有</label>
    					</c:if>
    				</td>
    			</tr>
    			<tr>
    				<td colspan="6">
    					<input type="submit" value="查询">
    					<input type="reset" value="重置">
    				</td>
    			</tr>
    		</table>	
    	</fieldset>
    </form>
    
    <c:if test="${requestScope.search eq 1 }">
    	<div>
    		<h3 style="text-align:center;color:black">查询结果</h3>
    		<div  style="widht:100%">
    			<div style="float:left;text-align:right;width:50%">
    				共<span>${requestScope.countOfEmployees }</span>条结果，
    				分成<span>${requestScope.countOfPages }</span>页显示，
    				当前第<span>${requestScope.pageNum }</span>页
    			</div>
    			<div style="float:left;width:50%">
    				<input type="button" value="首页"
    				onclick="window.location.href='SearchEmployeesServlet?employeename=${param.employeename}&username=${param.username }&status=${param.status }&pageNum=1'">
    				<c:if test="${requestScope.pageNum ne'1' }">
    				<input type="button" value="上页"
    				onclick="window.location.href='SearchEmployeesServlet?employeename=${param.employeename}&username=${param.username }&status=${param.status }&pageNum=${requestScope.pageNum-1 }'">
    				</c:if>
    				<c:if test="${requestScope.pageNum ne requestScope.countOfPages }">
    				<input type="button" value="下页"
    				onclick="window.location.href='SearchEmployeesServlet?employeename=${param.employeename}&username=${param.username }&status=${param.status }&pageNum=${requestScope.pageNum+1 }'">
    				</c:if>
    				<input type="button" value="末页"
    				onclick="window.location.href='SearchEmployeesServlet?employeename=${param.employeename}&username=${param.username }&status=${param.status }&pageNum=${requestScope.countOfPages }'">
    				跳到第<input type="text" id="pagenum" size="5px" value=${param.pageNum }>页
    				<input type="button" value="跳转" id="pageNum"
    				onclick="goToOnePage('${param.employeename}','${param.username }','${param.status }')">
    			</div>
    		</div>
    	</div>
    	<table align="center" class="gridtable" >
    		<tr>
    			<th>姓名</th>
    			<th>账号名</th>
    			<th>联系电话</th>
    			<th>电子邮件</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach var="emp" items="${requestScope.employeeslist }">
    		<tr>
    			<td>${emp.employeename }</td>
    			<td>${emp.username }</td>
    			<td>${emp.phone }</td>
    			<td>${emp.email }</td>
    			<c:if test="${emp.status eq '2' }">
    			<td>账号已关闭</td>
    			</c:if>
    			<c:if test="${emp.status ne '2' }">
    			<td>
    				<a href="ApproveServlet?employeeid=${emp.employeeid }&employeename=${emp.employeename}&username=${emp.username}&status=${emp.status}&oper=close">关闭账号</a>
    			</td>
    			</c:if>
    		</tr>
    		</c:forEach>
    	</table>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    </c:if>
  </body>
</html>
