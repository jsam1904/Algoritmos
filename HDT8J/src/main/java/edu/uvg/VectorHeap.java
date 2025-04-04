package edu.uvg;

import java.util.ArrayList;

/**
 * Clase que implementa un heap basado en un vector (ArrayList).
 * 
 * @param <E> El tipo de elementos almacenados en el heap, que deben ser comparables.
 */
public class VectorHeap<E extends Comparable<E>> {
    private ArrayList<E> heap;

    /**
     * Constructor para inicializar el heap.
     */
    public VectorHeap() {
        heap = new ArrayList<>();
    }

    /**
     * Agrega un elemento al heap.
     * 
     * @param e El elemento a agregar.
     * @return true si el elemento fue agregado exitosamente.
     */
    public boolean add(E e) {
        heap.add(e);
        siftUp(heap.size() - 1);
        return true;
    }

    /**
     * Retira el elemento de mayor prioridad (mínimo) del heap.
     * 
     * @return El elemento de mayor prioridad, o null si el heap está vacío.
     */
    public E remove() {
        if (heap.isEmpty()) return null;
        E result = heap.get(0);
        int lastIdx = heap.size() - 1;
        heap.set(0, heap.get(lastIdx));
        heap.remove(lastIdx);
        if (!heap.isEmpty()) siftDown(0);
        return result;
    }

    /**
     * Mira el elemento de mayor prioridad sin retirarlo.
     * 
     * @return El elemento de mayor prioridad, o null si el heap está vacío.
     */
    public E peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    /**
     * Obtiene el tamaño del heap.
     * 
     * @return El número de elementos en el heap.
     */
    public int size() {
        return heap.size();
    }

    /**
     * Verifica si el heap está vacío.
     * 
     * @return true si el heap está vacío, false en caso contrario.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Limpia todos los elementos del heap.
     */
    public void clear() {
        heap.clear();
    }

    // Métodos auxiliares para mantener la propiedad del heap

    /**
     * Realiza el sift-up para restaurar la propiedad del heap después de agregar un elemento.
     * 
     * @param index El índice del elemento agregado.
     */
    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(parent).compareTo(heap.get(index)) <= 0) break;
            swap(parent, index);
            index = parent;
        }
    }

    /**
     * Realiza el sift-down para restaurar la propiedad del heap después de eliminar el elemento raíz.
     * 
     * @param index El índice del elemento a ajustar.
     */
    private void siftDown(int index) {
        int size = heap.size();
        while (true) {
            int minIdx = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < size && heap.get(left).compareTo(heap.get(minIdx)) < 0)
                minIdx = left;
            if (right < size && heap.get(right).compareTo(heap.get(minIdx)) < 0)
                minIdx = right;

            if (minIdx == index) break;
            swap(index, minIdx);
            index = minIdx;
        }
    }

    /**
     * Intercambia dos elementos en el heap.
     * 
     * @param i El índice del primer elemento.
     * @param j El índice del segundo elemento.
     */
    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}