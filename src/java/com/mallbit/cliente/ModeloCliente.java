/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mallbit.cliente;

import com.mallbit.conexiondb.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Andres Ramos
 */
public class ModeloCliente {
    
    public List<Cliente> obtenerClientesDDBB() throws Exception {

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
            int id = resultSet.getInt("id_cliente"); //parte entre comillas va exactamente el nombre del columna SQL
            String nombre = resultSet.getString("nombre");
            String apellido = resultSet.getString("apellido");
            String correo = resultSet.getString("correo");
            String usuario = resultSet.getString("usuario");
            String contraseña = resultSet.getString("contraseña");
            Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
            int idGenero = resultSet.getInt("id_genero");

            clientes.add(new Cliente(id, nombre, apellido, correo, usuario, contraseña, fechaNacimiento, idGenero));

        }
        return clientes;
    }

    void insertarClienteDDBB(Cliente cliente) throws SQLException {
        
        Connection connection;
        PreparedStatement preparedStatement;

        //Establecer la conexión
        connection = ConexionDB.conectar();

        //Crear sentencia SQL y statement
        String sentenciaSQL = "INSERT INTO cliente "
                + "(nombre,apellido,correo,usuario,contraseña,fecha_nacimiento,id_genero) VALUES (?,?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sentenciaSQL);
        
        // pasar valores del objeto cliente a la sentenciaSQL
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
