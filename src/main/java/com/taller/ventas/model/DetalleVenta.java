package com.taller.ventas.model;

public class DetalleVenta {
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private ProductoFisico producto;

    public DetalleVenta(int cantidad, ProductoFisico producto) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.precioUnitario = producto.getPrecio();
        calcularSubtotal();
    }

    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getSubtotal() { return subtotal; }
    public ProductoFisico getProducto() { return producto; }

    public final void calcularSubtotal() {
        this.subtotal = cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return String.format("%d x %s = $%.2f", cantidad, producto.getNombreProducto(), subtotal);
    }
}