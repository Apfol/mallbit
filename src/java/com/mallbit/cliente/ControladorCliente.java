
package com.mallbit.cliente;


import com.mallbit.cliente.Cliente;
import com.mallbit.cliente.ModeloCliente;
import com.mallbit.cliente.ModeloCliente;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andres Ramos
 */
@WebServlet(name = "ControladorCliente", urlPatterns = {"/ControladorCliente"})
public class ControladorCliente extends HttpServlet {

    //Crea objeto del modelo
    ModeloCliente modeloCliente = new ModeloCliente();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Leer el parámetro del formulario
        String parametro = request.getParameter("instruccion");
        switch (parametro) {
            case "listarDDBB":
                obtenerClientes(request, response);
                break;
            case "insertarDDBB":
                insertarCliente(request, response);
                break;
            case "actualizarDDBB":
                break;
            case "buscarClienteDDBB":
                buscarCliente(request, response);
                break;
        }
    }

    private void obtenerClientes(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Cliente> clientes = modeloCliente.obtenerClientesDDBB();

            //Agregar clientes al request
            request.setAttribute("LISTACLIENTES", clientes);

            //Enviar request a la página que se desea
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request, response);

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) {

        try {

            // <editor-fold defaultstate="collapsed" desc="Pasos para cambiar al formato fecha de MySQL">
            //Formato de fecha que aparece en la pagina
            SimpleDateFormat formatPage = new SimpleDateFormat("dd MMM, yyyy");

            //Formato de fecha que acepta MySQL
            SimpleDateFormat formatSQL = new SimpleDateFormat("yyyy-MM-dd");

            Date fechaNacimiento = null;

            try {
                //Se pasa la fecha de la pagina a un objeto Date
                Date date = formatPage.parse(request.getParameter("fechaNacimiento"));
                //Se pasa el objeto Date al formato que admite MySQL
                fechaNacimiento = formatSQL.parse(formatSQL.format(date));
            } catch (ParseException ex) {
            }
            // </editor-fold>

            //Crear objeto con los datos recibidos del formulario
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String usuario = request.getParameter("usuario");
            String correo = request.getParameter("correo");
            String contraseña = request.getParameter("password");
            int idGenero = Integer.parseInt(request.getParameter("genero"));

            Cliente cliente = new Cliente(nombre, apellido, correo, usuario, contraseña, fechaNacimiento, idGenero);
            //enviar objeto al modelo para guardar en DDBB
            modeloCliente.insertarClienteDDBB(cliente);

            HttpSession session = request.getSession();

            session.setAttribute("CLIENTE_SESSION", cliente);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request, response);

        } catch (IOException | NumberFormatException | SQLException | ServletException ex) {

        }
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response) {
        try {
            String usuario = request.getParameter("usuario");
            String contraseña = request.getParameter("password");
            String encontroUsuario = "no";

            List<Cliente> clientes = modeloCliente.obtenerClientesDDBB();
            for (Cliente cliente : clientes) {
                if (cliente.getUsuario().equals(usuario) && cliente.getContraseña().equals(contraseña)) {
                    encontroUsuario = "si";
                    HttpSession session = request.getSession();
                    session.setAttribute("CLIENTE_SESSION", cliente);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                    requestDispatcher.forward(request, response);
                }
            }

            if (encontroUsuario.equals("no")) {
                request.setAttribute("ENCONTRO", "no");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/iniciar-sesion.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
