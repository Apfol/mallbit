﻿package com.mallbit.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.*;

@WebServlet("/ControladorCliente")
public class ControladorCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ModeloCliente modeloCliente = new ModeloCliente();
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Leer parametro (value) del input hidden del formulario
		String parametro = request.getParameter("instruccion");
		
		//Ejecutar m�todo seg�n valor del parametro
		switch(parametro){
                case "listarClientes":
	            listarClientesDB(request, response);
	            break;
	        case "insertarCliente":
	            insertarClienteDB(request, response);
	            break;
	        case "validarCliente":
                    validarClienteDB(request, response);
	            break;
	        case "actualizarCliente":
	            break;
                case "borrarCliente":
	            break;
	        default:
	        	break;
		}	

	}

	private void insertarClienteDB(HttpServletRequest request, HttpServletResponse response) {

        try {
            
            //Crear objeto Cliente con los datos recibidos del formulario
            //<editor-fold defaultstate="collapsed" desc="Pasos para cambiar al formato fecha de MySQL">
            //Formato de fecha que aparece en la pagina
        	
        	//Definiendo el idioma de la fecha
            Locale idioma = new Locale("en"); 
            SimpleDateFormat formatPage = new SimpleDateFormat("dd MMM, yyyy",idioma);

            //Formato de fecha que acepta MySQL
            SimpleDateFormat formatSQL = new SimpleDateFormat("yyyy-MM-dd",idioma);

            Date fechaNacimiento = null;

            //Se pasa la fecha de la pagina a un objeto Date
            Date date = formatPage.parse(request.getParameter("fechaNacimiento"));
            //Se pasa el objeto Date al formato que admite MySQL
            fechaNacimiento = formatSQL.parse(formatSQL.format(date));
            System.out.println(fechaNacimiento);
                
            // </editor-fold>      
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String usuario = request.getParameter("usuario");
            String correo = request.getParameter("correo");
            String contraseña = request.getParameter("password");
            int idGenero = Integer.parseInt(request.getParameter("genero"));
            
            String existe = "";
            
            //Se verifica si existe otro cliente con ese Usuario
            List<Cliente> clientes = modeloCliente.obtenerClientesDB();
            for (Cliente cliente : clientes) {
                if (cliente.getUsuario().equals(usuario)) {
                    existe = "existe";
                    break;
                }
            }  
            
            //Se envia un request al jsp correspondiente segun el caso
            if(existe.equals("existe")){
                String[] parametros= {existe,nombre,apellido,correo,contraseña,request.getParameter("fechaNacimiento"),request.getParameter("genero")};
                request.setAttribute("ESTADO", parametros);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/registro-cliente.jsp");
                requestDispatcher.forward(request, response);
            }else{
                Cliente cliente = new Cliente(nombre, apellido, correo, usuario, contraseña, fechaNacimiento, idGenero);
                
                //Enviar objeto al modelo para guardar en la Base de Datos
                modeloCliente.agregarClienteDB(cliente);
                HttpSession session = request.getSession();
                session.setAttribute("CLIENTE_SESSION", cliente);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (Exception ex) {    
            
        }
        
	}

	private void listarClientesDB(HttpServletRequest request, HttpServletResponse response) {
		try{
			//Obtener lista de Clientes
			List<Cliente> clientes;
				
				clientes = modeloCliente.obtenerClientesDB();
				
			//Agregar lista de clientes al Request
				request.setAttribute("LISTACLIENTES", clientes);
				
			//Enviar request al JSP correspondiente
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			
		}catch(Exception e){}
	}
        
        private void validarClienteDB(HttpServletRequest request, HttpServletResponse response) {
        try {
            String usuario = request.getParameter("usuario");
            String contraseña = request.getParameter("password");
            String estado = "indefinido";
            Cliente c = null;

            List<Cliente> clientes = modeloCliente.obtenerClientesDB();
            for (Cliente cliente : clientes) {
                if (cliente.getUsuario().equals(usuario) && cliente.getContraseña().equals(contraseña)) {
                    estado = "correcto";
                    c = cliente;
                    break;
                }
            }

            if (estado.equals("indefinido")) {
                for (Cliente cliente : clientes) {
                    if (cliente.getUsuario().equals(usuario) && !cliente.getContraseña().equals(contraseña)) {
                        estado = "incorrecto";
                        break;
                    }
                }
            }
            
            switch (estado){
                case "correcto":
                    HttpSession session = request.getSession();
                    session.setAttribute("CLIENTE_SESSION", c);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                case "incorrecto":
                    request.setAttribute("RESULTADO", estado);
                    RequestDispatcher requestDispatcherI = request.getRequestDispatcher("/iniciar-sesion.jsp");
                    requestDispatcherI.forward(request, response);
                    break;
                case "indefinido":
                    request.setAttribute("RESULTADO", estado);
                    RequestDispatcher requestDispatcherC = request.getRequestDispatcher("/iniciar-sesion.jsp");
                    requestDispatcherC.forward(request, response);
                    break;
            }

        } catch (Exception ex) {
            
        }

    }

}

