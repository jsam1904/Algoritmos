package edu.uvg;

public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private char codigoEmergencia;

    // Constructor
    public Paciente(String nombre, String sintoma, char codigoEmergencia) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.codigoEmergencia = Character.toUpperCase(codigoEmergencia); // Asegurar mayúsculas
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getSintoma() { return sintoma; }
    public char getCodigoEmergencia() { return codigoEmergencia; }

    // Implementación de compareTo
    @Override
    public int compareTo(Paciente otro) {
        // Invertir el orden para que 'A' tenga mayor prioridad
        return Character.compare(otro.codigoEmergencia, this.codigoEmergencia);
    }

    // Para mostrar la información del paciente
    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + codigoEmergencia;
    }
}