<?php
include 'conexion.php';
$email_log=$_POST['email'];
$password_log=$_POST['password'];

//$email_log="omar91916@gmail.com";
//$password_log="elg4lo";

$sentencia=$conexion->prepare("SELECT * FROM usuarios WHERE email=? and password=?");
$sentencia->bind_param('ss',$email_log,$password_log);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()){
    echo json_encode($fila,JSON_UNESCAPED_UNICODE);
}
$sentencia->close();
$conexion->close();
?>