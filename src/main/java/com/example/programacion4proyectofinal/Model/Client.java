package com.example.programacion4proyectofinal.Model;

/**
 * This class is used for Client's information.
 */
public class Client {
    String nombre;
    String apellido;
    String CI;
    Integer reservationTime;
    Enum priority;

    public Client(String name, String lastName, String CI, Integer reservationTime,Enum priority) {
        this.nombre = name;
        this.apellido = lastName;
        this.CI = CI;
        this.reservationTime = reservationTime;
        this.priority = priority;
    }

    /**
     * This method is used to get the client's information.
     * @return
     */
    @Override
    public String toString() {
        return "Client{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", CI='" + CI + '\'' +
                ", tiempoReserva=" + reservationTime +
                ", prioridad=" + priority +
                '}';
    }

    /**
     * This methos is used to get the client's name.
     * @return client's name.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * This methos is used to get the client's last name.
     * @return client's last name.
     */

    public String getApellido() {
        return apellido;
    }
    /**
     * This methos is used to get the client's ci.
     * @return client's ci.
     */

    public String getCI() {
        return CI;
    }
}
