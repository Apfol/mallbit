<%@page import="com.mallbit.genero.Genero"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link href="css/style.css" rel="stylesheet" />
        <link type="text/css" rel="stylesheet" href="css/materialize.css"/>

        <meta charset="UTF-8">
        <title>Registro Cliente</title>
    </head>

    <body class="lime lighten-2">
        <div class="nav-wrapper lime darken-1" id="isbl">
        <center><a href="index.jsp" class="brand-logo"><i class="material-icons">shopping_basket</i> MallBIT</a></center>
    </div>
    
    <%%>
    
    <div class="block">
        <div class="card-panel" id="cardp">
            <div class="row">
                <div class="col s10">
                    <p style="font-size: 30px" id="titleis">Registro</p>
                    <p style="font-size: 15px" id="subtitleis">Acceder a MallBIT</p><br>
                </div>
                <div class="col s1">
                    <i class="material-icons left" id="personas">people</i>
                </div>
            </div>
            <form action="ControladorCliente" method="post" class="col s12">
                    <input type="hidden" name="instruccion" value="insertarCliente"/>
                    <div class="row">
                        <div class="col s6 input-field">
                            <input id="nombre" name="nombre" type="text" class="validate">
                            <label for="nombre">Nombre</label>
                        </div>

                        <div class="col s6 input-field">
                            <input id="apellido" name="apellido" type="text" class="validate">
                            <label for="apellido">Apellido</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col s6 input-field">
                            <input id="correo" name="correo" type="text" class="validate">
                            <label for="correo">Correo</label>
                        </div>
                        <div class="col s6 input-field">
                            <input id="fecha" name="fechaNacimiento" type="text" class="datepicker">
                            <label for="fecha">Fecha de nacimiento</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s4 input-field">
                            <input id="usuario" name="usuario" type="text" class="validate">
                            <label for="usuario">Usuario</label>
                        </div>

                        <div class="col s4 input-field">
                            <input id="contraseña" name="password" type="password" class="validate">
                            <label for="contraseña">Contraseña</label>
                        </div>
                        <div class="input-field col s4">
                            <select name="genero">
                                <option value="" disabled selected>Género</option>
                                <% List<Genero> generos = (List<Genero>) request.getAttribute("ListaGeneros");%>
                               	<% for(Genero g : generos) { %>
                                <option value="<%=g.getIdGenero()%>"><%=g.getGenero()%></option>
                               	<%}%>
                            </select>
                        </div>
                    </div>
                    <div class="center-align">
                        <button class="btn waves-effect waves-light lime lighten-2 black-text" type="submit" name="action">
                            Registrar
                        </button>
                    </div>
                </form>            
        </div>
    </div>
        <!--Import jQuery before materialize.js-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script src="js/script.js"></script>
    </body>

</html>
