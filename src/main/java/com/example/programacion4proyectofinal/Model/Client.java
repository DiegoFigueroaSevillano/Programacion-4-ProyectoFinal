package com.example.programacion4proyectofinal.Model;

public class Client {
    String nombre;
    String apellido;
    String CI;
    Integer tiempoReserva;
    Enum prioridad;

    public Client(String nombre, String apellido, String CI, Integer tiempoReserva,Enum prioridad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.CI = CI;
        this.tiempoReserva = tiempoReserva;
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", CI='" + CI + '\'' +
                ", tiempoReserva=" + tiempoReserva +
                ", prioridad=" + prioridad +
                '}';
    }
}
