package com.taller.ventas;

import com.taller.ventas.model.*;
import com.taller.ventas.service.ReporteUtil;
import com.taller.ventas.util.ConsoleHelper;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<Cliente> clientes;
    private List<ProductoFisico> inventario;
    private List<Factura> facturas;
    private Administrador admin;
    private Vendedor vendedorActual;

    public Main() {
        clientes = new ArrayList<>();
        inventario = new ArrayList<>();
        facturas = new ArrayList<>();
        inicializarDatos();
    }

    private void inicializarDatos() {
        // Proveedores
        Proveedor proveedorTech = new Proveedor(1, "TechSupplies S.A.", "555-0192");

        // Productos
        inventario.add(new ProductoFisico(101, "Laptop DevPro 15\"", 1250.00, "Computación", 5, 2.5, proveedorTech));
        inventario.add(new ProductoFisico(102, "Ratón Inalámbrico", 35.00, "Accesorios", 15, 0.2, proveedorTech));
        inventario.add(new ProductoFisico(103, "Teclado Mecánico", 85.00, "Accesorios", 8, 0.8, proveedorTech));
        inventario.add(new ProductoFisico(104, "Monitor 24\"", 320.00, "Computación", 0, 4.0, proveedorTech)); // sin stock
        inventario.add(new ProductoFisico(105, "Audífonos Bluetooth", 65.00, "Audio", 10, 0.3, proveedorTech));

        // Clientes (precargados para demo)
        clientes.add(new Cliente(5001, "Andrés Gómez", "andres@correo.com"));
        clientes.add(new Cliente(5002, "María López", "maria@correo.com"));

        // Empleados
        admin = new Administrador(1, "Laura Martínez", "admin@tienda.com", "Mañana");
        vendedorActual = new Vendedor(2, "Carlos Pérez", "carlos@tienda.com", "Tarde");
    }

    private boolean login() {
        System.out.println("\n=== SISTEMA DE VENTAS === ");
        System.out.println("Inicie sesión como administrador\n");
        String email = ConsoleHelper.leerString("Email: ");
        String password = ConsoleHelper.leerString("Contraseña: ");

        if (admin.validarCredenciales(email, password)) {
            System.out.println("\n¡Bienvenido " + admin.getNombreEmpleado() + "!\n");
            return true;
        } else {
            System.out.println("\nCredenciales incorrectas. Acceso denegado.\n");
            return false;
        }
    }

    // --- Menú ---
    private void registrarCliente() {
        System.out.println("\nREGISTRAR NUEVO CLIENTE");
        int id = clientes.size() + 1;
        String nombre = ConsoleHelper.leerString("Nombre: ");
        String email = ConsoleHelper.leerString("Email: ");
        Cliente c = new Cliente(id, nombre, email);
        clientes.add(c);
        System.out.println("Cliente registrado con ID " + id);
    }

    private void consultarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes.");
            return;
        }
        System.out.println("\nLISTA DE CLIENTES");
        for (Cliente c : clientes) System.out.println(c);
    }

    private void mostrarInventario() {
        System.out.println("\n🛒 INVENTARIO DE PRODUCTOS");
        System.out.printf("%-5s %-25s %-10s %-8s %-15s%n", "ID", "Producto", "Precio", "Stock", "Proveedor");
        System.out.println("--------------------------------------------------------------------------------");
        for (ProductoFisico p : inventario) {
            System.out.printf("%-5d %-25s $%-9.2f %-8d %-15s%n",
                    p.getIdProducto(), p.getNombreProducto(), p.getPrecio(), p.getStock(), p.getProveedor().getNombreProveedor());
        }
    }

    private ProductoFisico buscarProductoPorId(int id) {
        return inventario.stream().filter(p -> p.getIdProducto() == id && p.getStock() > 0).findFirst().orElse(null);
    }

    private Cliente buscarClientePorId(int id) {
        return clientes.stream().filter(c -> c.getIdCliente() == id).findFirst().orElse(null);
    }

    private void realizarVenta() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes. Registre uno primero.");
            return;
        }
        System.out.println("\nREALIZAR VENTA");
        int idCliente = ConsoleHelper.leerInt("ID del cliente: ");
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        int idFactura = facturas.size() + 1;
        String metodoPago = ConsoleHelper.leerString("Método de pago (Efectivo/Tarjeta): ");
        Factura factura = new Factura(idFactura, metodoPago, cliente, vendedorActual);

        boolean agregando = true;
        while (agregando) {
            mostrarInventario();
            int idProducto = ConsoleHelper.leerInt("\nID del producto a vender (0 para terminar): ");
            if (idProducto == 0) break;
            ProductoFisico producto = buscarProductoPorId(idProducto);
            if (producto == null) {
                System.out.println("Producto no disponible o sin stock.");
            } else {
                int cantidad = ConsoleHelper.leerInt("Cantidad: ");
                try {
                    factura.agregarDetalle(cantidad, producto);
                    System.out.println("Producto agregado. Subtotal actual: $" + factura.getTotal());
                } catch (IllegalArgumentException e) {
                    System.out.println("NO" + e.getMessage());
                }
            }
            agregando = ConsoleHelper.leerBoolean("¿Agregar otro producto?");
        }

        if (factura.getDetalles().isEmpty()) {
            System.out.println("Venta cancelada (sin productos).");
            return;
        }

        factura.imprimirFactura();
        vendedorActual.registrarVenta(factura);
        cliente.agregarFactura(factura);
        facturas.add(factura);
        System.out.println("Venta registrada exitosamente.");
    }

    private void generarReportes() {
        if (facturas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        ReporteUtil.imprimirReporte(facturas);
    }

    private void gestionarInventario() {
        System.out.println("\nGESTIÓN DE INVENTARIO");
        admin.gestionarInventario();
        // Aquí podrías agregar opciones para añadir stock, etc.
        System.out.println("Funcionalidad en desarrollo. Por ahora, solo se muestra el inventario.");
        mostrarInventario();
    }

    private void mostrarMenu() {
        System.out.println("\n" + "=".repeat(55));
        System.out.println("          MENÚ PRINCIPAL - SISTEMA DE VENTAS");
        System.out.println("=".repeat(55));
        System.out.println("1. Registrar nuevo cliente");
        System.out.println("2. Consultar clientes");
        System.out.println("3. Ver inventario");
        System.out.println("4. Realizar venta");
        System.out.println("5. Reportes de ventas");
        System.out.println("6. Gestionar inventario (Admin)");
        System.out.println("7. Salir");
        System.out.println("=".repeat(55));
    }

    public void ejecutar() {
        if (!login()) return;
        int opcion;
        do {
            mostrarMenu();
            opcion = ConsoleHelper.leerInt("Opción: ");
            switch (opcion) {
                case 1: registrarCliente(); break;
                case 2: consultarClientes(); break;
                case 3: mostrarInventario(); break;
                case 4: realizarVenta(); break;
                case 5: generarReportes(); break;
                case 6: gestionarInventario(); break;
                case 7: System.out.println("\n¡Hasta luego!"); break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

    public static void main(String[] args) {
        new Main().ejecutar();
    }
}