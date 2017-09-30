<!DOCTYPE html>
<html lang="en">
<head>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link href="css/style.css" rel="stylesheet" />
    <link type="text/css" rel="stylesheet" href="css/materialize.css"/>

    <meta charset="UTF-8">
    <title>Inicio Sesión</title>
</head>

<body class="lime lighten-2">
    <div class="nav-wrapper lime darken-1" id="isbl">
        <center><a href="index.jsp" class="brand-logo"><i class="material-icons">shopping_basket</i> MallBIT</a></center>
    </div>
    <div class="block">
        <div class="card-panel" id="cardp">
            <div class="row">
                <div class="col s10">
                    <p style="font-size: 30px" id="titleis">Inicio de sesión</p>
                    <p style="font-size: 15px" id="subtitleis">Acceder a MallBIT</p><br>
                </div>
                <div class="col s1">
                    <i class="material-icons left" id="personas">people</i>
                </div>
            </div>
            <form action="PruebaDB2.jsp" method="post">
            <div class="input-field">
                <input id="correo" type="text" name="email" class="validate">
                <label for="correo">Correo</label>
            </div>

            <div class="input-field">
                <input id="contraseña" type="text" name="password" class="validate">
                <label for="contraseña">Contraseña</label>
            </div>
            <div class="center-align">
                <button class="waves-effect waves-light btn lime lighten-2 black-text" type="submit">Iniciar sesión</button>
            </div>
            </form>
            <br>
            <div class="center-align">
                ¿No tienes una cuenta?<a href="ControladorGenero"> Regístrate</a>
            </div>
        </div>
    </div>
    <!--Import jQuery before materialize.js-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
    <script src="js/script.js"></script>
</body>
</html>