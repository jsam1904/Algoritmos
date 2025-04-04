package edu.uvg;

/**
 * Clase que representa un paciente en el sistema de emergencias.
 * 
 * @autor Javier Alvarado - 24546
 */
public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private char codigoEmergencia;

    /**
     * Constructor para crear un nuevo paciente.
     * 
     * @param nombre El nombre del paciente.
     * @param sintoma El síntoma o condición del paciente.
     * @param codigoEmergencia El código de emergencia del paciente (A, B, C, etc.).
     */
    public Paciente(String nombre, String sintoma, char codigoEmergencia) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.codigoEmergencia = Character.toUpperCase(codigoEmergencia); // Asegurar mayúsculas
    }

    /**
     * Obtiene el nombre del paciente.
     * 
     * @return El nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el síntoma o condición del paciente.
     * 
     * @return El síntoma del paciente.
     */
    public String getSintoma() {
        return sintoma;
    }

    /**
     * Obtiene el código de emergencia del paciente.
     * 
     * @return El código de emergencia del paciente.
     */
    public char getCodigoEmergencia() {
        return codigoEmergencia;
    }

    /**
     * Compara este paciente con otro paciente para determinar su prioridad.
     * 
     * @param otro El otro paciente a comparar.
     * @return Un valor negativo, cero o positivo si este paciente tiene mayor, igual o menor prioridad que el otro.
     */
    @Override
    public int compareTo(Paciente otro) {
        // Invertir el orden para que 'A' tenga mayor prioridad
        return Character.compare(otro.codigoEmergencia, this.codigoEmergencia);
    }

    /**
     * Devuelve una representación en texto del paciente.
     * 
     * @return Una cadena de texto que representa al paciente.
     */
    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + codigoEmergencia;
    }
}