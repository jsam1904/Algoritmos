package edu.uvg;

import java.util.PriorityQueue;

/**
 * Clase principal para simular un sistema de emergencias utilizando una cola de prioridad.
 * 
 * @autor Javier Alvarado - 24546
 */
public class EmergencySystemJCF {

    /**
     * Método principal que ejecuta el sistema de emergencias.
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        PriorityQueue<Paciente> queue = new PriorityQueue<>();

        // Agregar pacientes a la cola de prioridad
        queue.add(new Paciente("Juan Perez", "fractura de pierna", 'C'));
        queue.add(new Paciente("Maria Ramirez", "apendicitis", 'A'));
        queue.add(new Paciente("Lorenzo Toledo", "chikungunya", 'E'));
        queue.add(new Paciente("Carmen Sarmientos", "dolores de parto", 'B'));

        // Atender a los pacientes en orden de prioridad
        while (!queue.isEmpty()) {
            System.out.println("Atendiendo a: " + queue.remove());
        }
    }
}