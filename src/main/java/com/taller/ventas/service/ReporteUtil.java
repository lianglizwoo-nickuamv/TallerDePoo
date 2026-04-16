package com.taller.ventas.service;

import com.taller.ventas.model.Factura;
import com.taller.ventas.model.ProductoFisico;
import java.util.*;

public final class ReporteUtil {
    private ReporteUtil() {}

    public static double calcularTotalVentas(List<Factura> facturas) {
        return facturas.stream().mapToDouble(Factura::getTotal).sum();
    }

    public static long contarFacturas(List<Factura> facturas) {
        return facturas.size();
    }

    public static Map<String, Integer> obtenerProductosMasVendidos(List<Factura> facturas) {
        Map<String, Integer> conteo = new HashMap<>();
        for (Factura f : facturas) {
            f.getDetalles().forEach(d -> {
                String nombre = d.getProducto().getNombreProducto();
                conteo.put(nombre, conteo.getOrDefault(nombre, 0) + d.getCantidad());
            });
        }
        return conteo;
    }

    public static void imprimirReporte(List<Factura> facturas) {
        System.out.println("\n=== REPORTE DE VENTAS ===");
        System.out.printf("Total de ventas: $%.2f%n", calcularTotalVentas(facturas));
        System.out.printf("Facturas emitidas: %d%n", contarFacturas(facturas));
        System.out.println("\nProductos más vendidos:");
        Map<String, Integer> top = obtenerProductosMasVendidos(facturas);
        top.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.printf("   - %s: %d unidad(es)%n", e.getKey(), e.getValue()));
    }
}