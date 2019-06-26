package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.utils.DBConnection;
import com.example.vo.EmployeeVO;

public class EmployeeDao {
	public static ArrayList<EmployeeVO> selectAll(int deptno) throws SQLException{
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();
		Connection conn = DBConnection.getConnection();
		CallableStatement cstmt = conn.prepareCall("{ call sp_select(?, ?) }");
		cstmt.setInt(1, deptno);
		cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
		cstmt.executeUpdate();
		ResultSet rs = (ResultSet)cstmt.getObject(2);
		while(rs.next()) {
			EmployeeVO emp = new EmployeeVO();
			emp.setEmployee_id(rs.getInt("employee_id"));
			emp.setFirst_name(rs.getString("first_name"));
			emp.setSalary(rs.getDouble("salary"));
			emp.setHiredate(rs.getString("hiredate"));
			emp.setDepartment_name(rs.getString("department_name"));
			emp.setCity(rs.getString("city"));
			emp.setDepartno(rs.getInt("deptno"));
			list.add(emp);
		}
		if(rs != null) rs.close();
		if(cstmt != null) cstmt.close();
		return list;
	}
}
