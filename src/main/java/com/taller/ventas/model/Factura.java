package com.taller.ventas.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura {
    private int idVenta;
    private Date fecha;
    private double total;
    private String metodoPago;
    private Cliente cliente;
    private Vendedor vendedor;
    private List<DetalleVenta> detalles;

    public Factura(int idVenta, String metodoPago, Cliente cliente, Vendedor vendedor) {
        this.idVenta = idVenta;
        this.fecha = new Date();
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.detalles = new ArrayList<>();
        this.total = 0;
    }

    public int getIdVenta() { return idVenta; }
    public Date getFecha() { return fecha; }
    public double getTotal() { return total; }
    public String getMetodoPago() { return metodoPago; }
    public Cliente getCliente() { return cliente; }
    public Vendedor getVendedor() { return vendedor; }
    public List<DetalleVenta> getDetalles() { return new ArrayList<>(detalles); }

    public void agregarDetalle(int cantidad, ProductoFisico producto) {
        if (!producto.verificarDisponibilidad(cantidad)) {
            throw new IllegalArgumentException("Stock insuficiente para " + producto.getNombreProducto());
        }
        DetalleVenta detalle = new DetalleVenta(cantidad, producto);
        detalles.add(detalle);
        producto.reducirStock(cantidad);
        calcularTotal();
    }

    public final void calcularTotal() {
        this.total = 0;
        for (DetalleVenta d : detalles) {
            this.total += d.getSubtotal();
        }
    }

    public void imprimirFactura() {
        System.out.println("\n=== FACTURA #" + idVenta + " ===");
        System.out.println("Fecha: " + fecha);
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Vendedor: " + vendedor.getNombreEmpleado());
        System.out.println("Método de pago: " + metodoPago);
        System.out.println("Detalles:");
        for (DetalleVenta d : detalles) {
            System.out.println("  " + d);
        }
        System.out.printf("TOTAL: $%.2f%n", total);
    }

    @Override
    public String toString() {
        return String.format("Factura #%d - Cliente: %s - Total: $%.2f", idVenta, cliente.getNombre(), total);
    }
}