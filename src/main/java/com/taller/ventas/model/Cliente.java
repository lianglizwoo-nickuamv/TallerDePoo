package com.taller.ventas.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String email;
    private List<Factura> historialCompras;

    public Cliente(int idCliente, String nombre, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.historialCompras = new ArrayList<>();
    }

    public int getIdCliente() { return idCliente; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

    public List<Factura> obtenerHistorialCompras() {
        return new ArrayList<>(historialCompras);
    }

    public void agregarFactura(Factura factura) {
        historialCompras.add(factura);
    }

    @Override
    public String toString() {
        return "Cliente{id=" + idCliente + ", nombre='" + nombre + "', email='" + email + "'}";
    }
}