package com.taller.ventas.model;

public abstract class Producto {
    protected int idProducto;
    protected String nombreProducto;
    protected double precio;
    protected String categoria;

    public Producto(int idProducto, String nombreProducto, double precio, String categoria) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getIdProducto() { return idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }

    public void aplicarDescuento(double porcentaje) {
        if (porcentaje > 0 && porcentaje <= 100) {
            double descuento = precio * (porcentaje / 100);
            precio -= descuento;
            System.out.printf("Descuento aplicado. Nuevo precio: $%.2f%n", precio);
        } else {
            System.out.println("Porcentaje inválido.");
        }
    }

    public abstract boolean esBajoStock();

    @Override
    public String toString() {
        return String.format("Producto{id=%d, nombre='%s', precio=%.2f, categoría='%s'}",
                idProducto, nombreProducto, precio, categoria);
    }
}