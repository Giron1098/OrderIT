<?php
$hostname='localhost';
$database='db_orderit';
$username='root';
$password='';

$conexion=new mysqli($hostname,$username,$password,$database);
if($conexion->connect_errno){
    echo "El sitio esta en mantenimiento";
}
?>