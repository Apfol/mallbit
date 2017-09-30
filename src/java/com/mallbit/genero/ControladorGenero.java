/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mallbit.genero;

import com.mallbit.cliente.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andres Ramos
 */
@WebServlet(name = "ControladorGenero", urlPatterns = {"/ControladorGenero"})
public class ControladorGenero extends HttpServlet {

    ModeloGenero modeloGenero = new ModeloGenero();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Genero> generos = modeloGenero.getGeneros();
            
            //Agregar clientes al request
            request.setAttribute("LISTAGENEROS", generos);
            
            
            //Enviar request a la página que se desea
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/registro-cliente.jsp");
            requestDispatcher.forward(request, response);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
