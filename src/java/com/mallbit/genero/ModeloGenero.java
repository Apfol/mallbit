/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mallbit.genero;

import com.mallbit.cliente.Cliente;
import com.mallbit.conexiondb.ConexionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Andres Ramos
 */
public class ModeloGenero {
    public List<Genero> getGeneros() throws Exception {

        List<Genero> generos = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet resultSet;

        //Establecer la conexión
        connection = ConexionDB.conectar();

        //Crear sentencia SQL y statement
        String sentenciaSQL = "SELECT * FROM genero";
        statement = connection.createStatement();

        //Ejecutar SQL
        resultSet = statement.executeQuery(sentenciaSQL);

        //Recorrer resultador de la sentencia
        while (resultSet.next()) {
            int id = resultSet.getInt("IDGenero"); //parte entre comillas va exactamente el nombre del columna SQL
            String tipo = resultSet.getString("tipo");

            generos.add(new Genero(id, tipo));

        }
        return generos;
    }
}
