package com.mallbit.cliente;

import java.util.*;
import java.util.Date;

import com.mallbit.Conexion.ConexionDB;

import java.sql.*;
import java.text.SimpleDateFormat;

public class ModeloCliente {
		
		public List<Cliente> obtenerClientesDB() throws Exception {

	        List<Cliente> clientes = new ArrayList<>();

	        Connection connection;
	        Statement statement;
	        ResultSet resultSet;

	        //Establecer la conexión
	        connection = ConexionDB.conectar();

	        //Crear sentencia SQL y statement
	        String sentenciaSQL = "SELECT * FROM cliente";
	        statement = connection.createStatement();

	        //Ejecutar SQL y guardar valores de consulta en resultSet
	        resultSet = statement.executeQuery(sentenciaSQL);

	        //Recorrer resultador de la sentencia
	        while (resultSet.next()) {
	            int id = resultSet.getInt("IDCliente"); 
	            String nombre = resultSet.getString("Nombre");
	            String apellido = resultSet.getString("Apellido");
	            String correo = resultSet.getString("Correo");
	            String usuario = resultSet.getString("Usuario");
	            String contraseña = resultSet.getString("Password");
	            Date fechaNacimiento = resultSet.getDate("FechaNacimiento");
	            int idGenero = resultSet.getInt("IDGenero");

	            clientes.add(new Cliente(id, nombre, apellido, correo, usuario, contraseña, fechaNacimiento, idGenero));

	        }
	        return clientes;
	    }

	    public void agregarClienteDB(Cliente cliente) throws SQLException {
	        
	        Connection connection;
	        PreparedStatement preparedStatement;

	        //Establecer la conexión
	        connection = ConexionDB.conectar();

	        //Crear sentencia SQL y statement
	        String sentenciaSQL = "INSERT INTO cliente "
	                + "(Nombre,Apellido,Correo,Usuario,Password,FechaNacimiento,IDGenero) VALUES (?,?,?,?,?,?,?)";
	        preparedStatement = connection.prepareStatement(sentenciaSQL);
	        
	        //Pasar valores del objeto cliente a la sentenciaSQL
	        preparedStatement.setString(1, cliente.getNombre());
	        preparedStatement.setString(2, cliente.getApellido());
	        preparedStatement.setString(3, cliente.getCorreo());
	        preparedStatement.setString(4, cliente.getUsuario());
	        preparedStatement.setString(5, cliente.getContraseña());
	        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	        preparedStatement.setString(6, formatDate.format(cliente.getFechaNacimiento()));
	        preparedStatement.setInt(7, cliente.getIdGenero());
	        
	        preparedStatement.execute();
	    }
	    
	  
	
}

