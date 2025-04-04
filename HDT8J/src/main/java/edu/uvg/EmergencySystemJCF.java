// Removed package declaration

import java.util.PriorityQueue;

public class EmergencySystemJCF {
    public static void main(String[] args) {
        PriorityQueue<Paciente> queue = new PriorityQueue<>();
        queue.add(new Paciente("Juan Perez", "fractura de pierna", 'C'));
        queue.add(new Paciente("Maria Ramirez", "apendicitis", 'A'));
        queue.add(new Paciente("Lorenzo Toledo", "chikungunya", 'E'));
        queue.add(new Paciente("Carmen Sarmientos", "dolores de parto", 'B'));

        while (!queue.isEmpty()) {
            System.out.println("Atendiendo a: " + queue.remove());
        }
    }
}