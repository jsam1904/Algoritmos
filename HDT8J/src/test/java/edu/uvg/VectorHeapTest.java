package edu.uvg;

import org.junit.Test;
import static org.junit.Assert.*;

public class VectorHeapTest {
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

    @Test
    public void testEmptyHeap() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        assertNull(heap.remove());
        assertTrue(heap.isEmpty());
    }
}