<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.example.vo.EmployeeVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="service" class="com.example.service.EmployeeService" />
<c:set target="${service}" property="deptno" value="${empty param.deptno ? 10 : param.deptno}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원명단</title>
</head>
<body>
	<h1>사원 명단(부서번호 : <c:out value="${empty param.deptno ? 10 : param.deptno}" />)</h1>
	<table border="1">
		<thead>
			<tr>
				<th>사원번호</th><th>사원이름</th><th>봉급</th><th>입사일자</th><th>부서이름</th><th>부서위치</th><th>부서번호</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${service.list}" var="emp">
			<tr>
				<td>${emp['employee_id']}</td><td>${emp['first_name']}</td><td>${emp.salary}</td>
				<td>${emp.hiredate}</td><td>${emp['department_name']}</td><td>${emp.city}</td>
				<td>${emp.departno}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>