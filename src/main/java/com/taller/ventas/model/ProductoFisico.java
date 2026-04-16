package com.taller.ventas.model;

public class ProductoFisico extends Producto {
    private int stock;
    private double peso;
    private Proveedor proveedor;

    public ProductoFisico(int idProducto, String nombreProducto, double precio, String categoria,
                          int stock, double peso, Proveedor proveedor) {
        super(idProducto, nombreProducto, precio, categoria);
        this.stock = stock;
        this.peso = peso;
        this.proveedor = proveedor;
    }

    public int getStock() { return stock; }
    public double getPeso() { return peso; }
    public Proveedor getProveedor() { return proveedor; }

    public void reducirStock(int cantidad) {
        if (cantidad > 0 && cantidad <= stock) {
            stock -= cantidad;
            System.out.println("Stock actualizado. Nuevo stock: " + stock);
        } else {
            throw new IllegalArgumentException("Cantidad no válida o stock insuficiente");
        }
    }

    public boolean verificarDisponibilidad(int cantidad) {
        return stock >= cantidad;
    }

    @Override
    public boolean esBajoStock() {
        return stock < 5; // umbral de bajo stock
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", stock=%d, peso=%.2fkg, proveedor=%s",
                stock, peso, proveedor.getNombreProveedor());
    }
}