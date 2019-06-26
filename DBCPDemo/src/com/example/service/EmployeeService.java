package com.example.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.example.dao.EmployeeDao;
import com.example.vo.EmployeeVO;

public class EmployeeService {
	private int deptno;
	private ArrayList<EmployeeVO> list;
	
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public ArrayList<EmployeeVO> getList() {
		ArrayList<EmployeeVO> list = null;
		try {
			list = EmployeeDao.selectAll(this.deptno);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
		return list;
	}
}
