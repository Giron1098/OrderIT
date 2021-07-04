package com.moviles.orderit;

public class Pedido {

    int idPedidos;
    String fecha;
    double cantidad;
    double total;
    String nombrePlatillo;
    double precio;
    String nombreRest;
    int costoEntrega;

    public Pedido(int idPedidos, String fecha, double cantidad, double total, String nombrePlatillo, double precio, String nombreRest, int costoEntrega) {
        this.idPedidos = idPedidos;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
        this.nombrePlatillo = nombrePlatillo;
        this.precio = precio;
        this.nombreRest = nombreRest;
        this.costoEntrega = costoEntrega;
    }

    public int getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(int idPedidos) {
        this.idPedidos = idPedidos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombreRest() {
        return nombreRest;
    }

    public void setNombreRest(String nombreRest) {
        this.nombreRest = nombreRest;
    }

    public int getCostoEntrega() {
        return costoEntrega;
    }

    public void setCostoEntrega(int costoEntrega) {
        this.costoEntrega = costoEntrega;
    }
}
