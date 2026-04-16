package com.taller.ventas.model;

public class Administrador extends Empleado {
    public Administrador(int idEmpleado, String nombreEmpleado, String correo, String turno) {
        super(idEmpleado, nombreEmpleado, correo, turno);
    }

    public void gestionarInventario() {
        System.out.println("Administrador " + nombreEmpleado + " está gestionando el inventario.");
    }
}