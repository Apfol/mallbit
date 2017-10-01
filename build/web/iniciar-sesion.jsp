<%-- 
    Document   : iniciar-sesion
    Created on : Sep 28, 2017, 11:27:09 PM
    Author     : Andres Ramos
--%>

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
        <title>Document</title>
    </head>

    <body class="lime lighten-2">
        <nav class="pushpin-nav z-depth-0" data-target="primera">
            <div class="nav-wrapper lime lighten-2">
                <div class="col s12">
                    <div id="principal-nav">
                        <a href="index.jsp" class="brand-logo"><i class="material-icons">shopping_basket</i>MallBIT</a>
                        <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">casa</i></a>
                    </div>
                </div>
            </div>
        </nav>

        <div class="center-align">
            <h3>¡Hola! ingresa tu correo y contraseña</h3>
        </div>

        <br>

        <div class="block">
            <div class="card-panel">
                <form action="ControladorCliente" method="post">
<<<<<<< HEAD
                    <input type="hidden" name="instruccion" value="validarCliente">
                    <div class="row">
                        <div class="col s10">
                            <p style="font-size: 30px" id="titleis">Inicio de sesión</p>
                            <p style="font-size: 15px" id="subtitleis">Acceder a MallBIT</p><br>
                        </div>
                        <div class="col s2">
                            <i class="material-icons left" id="personas">people</i>
                        </div>
                    </div>
=======
                    <input type="hidden" name="instruccion" value="buscarClienteDDBB"/>
>>>>>>> parent of 1b1c791... Cambios mayores a la Interfaz de Inicio de Sesion
                    <div class="input-field">
                        <input id="usuario" name="usuario" type="text" class="validate">
                        <label for="usuario">Usuario</label>
                    </div>

                    <div class="input-field">
                        <input id="contraseña" name="password" type="password" class="validate">
                        <label for="contraseña">Contraseña</label>
                    </div>
                    <div class="center-align">
                        <button class="btn waves-effect waves-light lime lighten-2 black-text" type="submit" name="action">
                            Iniciar Sesión
                        </button>
                    </div>
                </form>
                <% String encontro =  (String) request.getAttribute("ENCONTRO"); %>
                <% if (encontro != null) {%>
                <div class="center-align">
                    <p>Usuario no encontrado</p>
                </div>
                <% }%>
                <br>
                <div class="center-align">
                    <a href="ControladorGenero">Crear cuenta</a>
                </div>

            </div>
        </div>



        <!--Import jQuery before materialize.js-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script src="js/script.js"></script>
    </body>
</html>
