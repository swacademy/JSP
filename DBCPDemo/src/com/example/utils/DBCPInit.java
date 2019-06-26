package com.example.utils;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {
	private final String driverClass = "oracle.jdbc.driver.OracleDriver";
	private final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String username = "hr";
	private final String password = "hr";
	
	@Override
	public void init() throws ServletException{
		loadJDBCDriver();
		initconnectionPool();
	}
	
	private void loadJDBCDriver() {
		try {
			//Connection Pool에서 사용할 JDBC Driver Loading
			Class.forName(this.driverClass);
		}catch(ClassNotFoundException ex) {
			throw new RuntimeException("Driver Loading Failure");
		}
	}
	
	private void initconnectionPool() {
		try {
			//ConnectionFactory 생성, ConnectionFactory는 새로운 Connection을 생성할 때 사용.
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(this.url, this.username, this.password);
			
			//DBCP가 Connection Pool에 connection을 보관할 때 사용하는 PoolableConnectionFactory 생성
			//실제로 내부적으로 connection을 담고 있고, connection을 관리하는데 기능을 제공한다.
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			//Connection이 유효한지 확인할 때 사용하는 query를 설정한다.
			poolableConnFactory.setValidationQuery("select 1 from dual");
			
			//Connection Pool의 설정 정보를 생성한다.
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			//유휴 connection 검사 주기
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60 * 1L);
			//Pool에 있는 connection이 유효한지 검사 유무 설정
			poolConfig.setTestWhileIdle(true);
			//Connection 최소 갯수 설정
			poolConfig.setMinIdle(4);
			//Connection 최대 갯수 설정
			poolConfig.setMaxTotal(50);
			
			//Connection Pool 생성, parameter는 위에서 생성한 PoolableConnectionFactory와 GenericObjectPoolConfig를 사용
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			
			//PoolableConnectionFactory에도 Connection Pool 연결
			poolableConnFactory.setPool(connectionPool);
			
			//Connection Pool을 제공하는 JDBC Driver 등록.
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			
			//위에서 Connection Pool Driver에 생성한 Connection Pool을 등록한다. 
			//이름은 cp이다.
			driver.registerPool("cp", connectionPool);
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
