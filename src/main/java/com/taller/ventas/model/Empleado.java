package com.taller.ventas.model;

public abstract class Empleado {
    protected int idEmpleado;
    protected String nombreEmpleado;
    protected String correo;
    protected String turno;

    public Empleado(int idEmpleado, String nombreEmpleado, String correo, String turno) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.correo = correo;
        this.turno = turno;
    }

    public int getIdEmpleado() { return idEmpleado; }
    public String getNombreEmpleado() { return nombreEmpleado; }
    public String getCorreo() { return correo; }
    public String getTurno() { return turno; }

    public void cambiarTurno(String nuevoTurno) {
        this.turno = nuevoTurno;
        System.out.println("Turno cambiado a: " + nuevoTurno);
    }

    public boolean validarCredenciales(String email, String password) {
        // Simulación: en un caso real se compararía con base de datos
        return this.correo.equals(email) && password.equals("1234");
    }

    @Override
    public String toString() {
        return "Empleado{id=" + idEmpleado + ", nombre='" + nombreEmpleado + "', turno='" + turno + "'}";
    }
}