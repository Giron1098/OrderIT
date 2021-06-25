<?PHP
$hostname_localhost="localhost";
$database_localhost="orderit";
$username_localhost="root";
$password_localhost="";

$json=array();

if(isset($_GET["idUsuarios"]) && isset($_GET["nombre"]) && isset($_GET["apellidoPaterno"]) && isset($_GET["apellidoMaterno"]) && isset($_GET["email"]) && isset($_GET["password"])){
    $idUsuarios=$_GET['idUsuarios'];
    $nombre=$_GET['nombre'];
    $apellidoPaterno=$_GET['apellidoPaterno'];
    $apellidoMaterno=$_GET['apellidoMaterno'];
    $email=$_GET['email'];
    $password=$_GET['password'];

    $conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

    $insert="INSERT INTO usuarios(idUsuarios, nombre, apellidoPaterno, apellidoMaterno, email, password) VALUES ('$idUsuarios','$nombre','$apellidoPaterno','$apellidoMaterno','$email','$password')";
    $resultado_insert=mysqli_query($conexion,$insert);

    if($resultado_insert){
        $consulta="SELECT * FROM usuarios WHERE idUsuarios = '{$idUsuarios}'";
        $resultado=mysqli_query($conexion,$consulta);

        if($registro=mysqli_fetch_array($resultado)){
            $json['usuarios'][]=$registro;
        }
        mysqli_close($conexion);
        echo json_encode($json);
    }
    else{
        $resulta["idUsuarios"]=0;
        $resulta["nombre"]='No registra';
        $resulta["apellidoPaterno"]='No registra';
        $resulta["apellidoMaterno"]='No registra';
        $resulta["email"]='No registra';
        $resulta["password"]='No registra';
        $json['usuarios'][]=$resulta;
        echo json_encode($json);
    }

}
else{
    $resulta["idUsuarios"]=0;
        $resulta["nombre"]='WS No registra';
        $resulta["apellidoPaterno"]='WS No registra';
        $resulta["apellidoMaterno"]='WS No registra';
        $resulta["email"]='WS No registra';
        $resulta["password"]='WS No registra';
        $json['usuarios'][]=$resulta;
        echo json_encode($json);
}
?>