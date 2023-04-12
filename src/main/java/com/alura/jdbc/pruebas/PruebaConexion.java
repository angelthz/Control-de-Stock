package com.alura.jdbc.pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.connection.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

public class PruebaConexion {

    public static void main(String[] args) throws SQLException  {
    	
    	
		List<Map<String, String>> resultados = new ArrayList<>();
		
		//Connection con = new FactoryConection().getConection();
		
		//try catch with resources java 7
		/*
		try(Connection con = new FactoryConection().getConection()){
			try(PreparedStatement query = con.prepareStatement("SELECT *FROM producto")){
				query.execute();
				
				ResultSet res = query.getResultSet();
				System.out.println("Pruebas");
				while(res.next()) {
					
					System.out.println(res.getString("id")+ " "+ res.getString("nombre") +" "+ res.getString("descripcion"));
				}
			}
			catch(Exception e) {
				
			}

		
		}
		catch (Exception e) {
			
		}*/
		
		/*
		System.out.println("Ejecutando con try/catch with resources");
		//try/cathc with resources java 9+
		final Connection con = new FactoryConection().getConection();
			
		try(con){
			final PreparedStatement query = con.prepareStatement("SELECT *FROM producto");
			try(query){
				query.execute();
				ResultSet res = query.getResultSet();
				while(res.next()) {
					System.out.println(res.getString("id")+ " "+ res.getString("nombre") +" "+ res.getString("descripcion"));
				}
			}
			catch(Exception e) {
				System.out.println("Error: " + e);
			}
					
		}catch(Exception e) {
			System.out.println("Error: " + e);
		}*/
		/*int max = 50;
		int cant = 120;
		
		
		do {
			int actual = Math.min(max, cant);
			System.out.println(actual);
			cant -= max;
		}while(cant>0);*/
		
		/*ConnectionFactory conFactory = new ConnectionFactory();
		for(int i=0; i<20; i++) {
			Connection con = conFactory.getConection();
			System.out.println("Abriendo la conexion: "+(i+1));
		}*/
		
		Producto p = new Producto(1,"Telefono","Huawei",27);
		System.out.println(p);
    }

}
