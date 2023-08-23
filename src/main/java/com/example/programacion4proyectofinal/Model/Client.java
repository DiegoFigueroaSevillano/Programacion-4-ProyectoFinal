package com.example.programacion4proyectofinal.Model;

public class Client {
    String nombre;
    String apellido;
    String CI;
    Integer tiempoReserva;
    Integer prioridad; // VIP=3 , FRECUENTE=2, REGULAR=1

    public Client(String nombre, String apellido, String CI, Integer tiempoReserva, Integer prioridad) {
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
