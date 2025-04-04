package edu.uvg;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase de prueba para la implementación de la clase VectorHeap.
 * 
 * @autor Javier Alvarado - 24546
 */
public class VectorHeapTest {

    /**
     * Prueba el método add y remove de la clase VectorHeap.
     * Verifica que los elementos se agreguen y se eliminen en el orden correcto según la prioridad.
     */
    @Test
    public void testAddAndRemove() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        heap.add(new Paciente("Juan Perez", "fractura de pierna", 'C'));
        heap.add(new Paciente("Maria Ramirez", "apendicitis", 'A'));
        heap.add(new Paciente("Carmen Sarmientos", "dolores de parto", 'B'));

        assertEquals("Maria Ramirez, apendicitis, A", heap.remove().toString());
        assertEquals("Carmen Sarmientos, dolores de parto, B", heap.remove().toString());
        assertEquals("Juan Perez, fractura de pierna, C", heap.remove().toString());
    }

    /**
     * Prueba el comportamiento del heap cuando está vacío.
     * Verifica que remove devuelva null y que isEmpty sea true.
     */
    @Test
    public void testEmptyHeap() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        assertNull(heap.remove());
        assertTrue(heap.isEmpty());
    }
}