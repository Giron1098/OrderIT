package com.moviles.orderit;

public class Restaurante {

    int idRestaurante;
    String nombreRest;
    String direccion;
    String horario;
    String tiempoEstimado;
    int costoEntrega;
    int idPlatillos;
    String nombrePlatillo;
    double precio;

    public Restaurante(int idRestaurante, String nombreRest, String direccion, String horario, String tiempoEstimado, int costoEntrega, int idPlatillos, String nombrePlatillo, double precio) {
        this.idRestaurante = idRestaurante;
        this.nombreRest = nombreRest;
        this.direccion = direccion;
        this.horario = horario;
        this.tiempoEstimado = tiempoEstimado;
        this.costoEntrega = costoEntrega;
        this.idPlatillos = idPlatillos;
        this.nombrePlatillo = nombrePlatillo;
        this.precio = precio;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getNombreRest() {
        return nombreRest;
    }

    public void setNombreRest(String nombreRest) {
        this.nombreRest = nombreRest;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(String tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public int getCostoEntrega() {
        return costoEntrega;
    }

    public void setCostoEntrega(int costoEntrega) {
        this.costoEntrega = costoEntrega;
    }

    public int getIdPlatillos() {
        return idPlatillos;
    }

    public void setIdPlatillos(int idPlatillos) {
        this.idPlatillos = idPlatillos;
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
}
