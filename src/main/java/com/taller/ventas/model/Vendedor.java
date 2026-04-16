package com.taller.ventas.model;

public class Vendedor extends Empleado {
    public Vendedor(int idEmpleado, String nombreEmpleado, String correo, String turno) {
        super(idEmpleado, nombreEmpleado, correo, turno);
    }

    public void registrarVenta(Factura factura) {
        System.out.println("Vendedor " + nombreEmpleado + " registró la factura #" + factura.getIdVenta());
    }
}