package com.example.vo;

public class EmployeeVO {
	private int employee_id;
	private String first_name;
	private double salary;
	private String hiredate;
	private String department_name;
	private String city;
	private int departno;
	
	public EmployeeVO() {}

	public EmployeeVO(int employee_id, String first_name, double salary, String hiredate, String department_name,
			String city, int departno) {
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.salary = salary;
		this.hiredate = hiredate;
		this.department_name = department_name;
		this.city = city;
		this.departno = departno;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getDepartno() {
		return departno;
	}

	public void setDepartno(int departno) {
		this.departno = departno;
	}

	@Override
	public String toString() {
		return "EmployeeVO [employee_id=" + employee_id + ", first_name=" + first_name + ", salary=" + salary
				+ ", hiredate=" + hiredate + ", department_name=" + department_name + ", city=" + city
				+ ", department_id=" + departno + "]";
	}
	
}
