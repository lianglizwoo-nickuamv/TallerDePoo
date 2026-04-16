package com.taller.ventas.model;

public class Proveedor {
    private int idProveedor;
    private String nombreProveedor;
    private String telefono;

    public Proveedor(int idProveedor, String nombreProveedor, String telefono) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.telefono = telefono;
    }

    public int getIdProveedor() { return idProveedor; }
    public String getNombreProveedor() { return nombreProveedor; }
    public String getTelefono() { return telefono; }

    public void obtenerCatalogo() {
        System.out.println("Obteniendo catálogo del proveedor: " + nombreProveedor);
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + idProveedor + ", nombre='" + nombreProveedor + "'}";
    }
}