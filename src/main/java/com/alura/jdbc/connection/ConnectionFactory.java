package com.alura.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	//POOL de conexiones
	
	
	
	public Connection getConection() throws SQLException {
			return DriverManager.getConnection(
			        "jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC",
			        "root",
			        "2011.1994");
	}
}
