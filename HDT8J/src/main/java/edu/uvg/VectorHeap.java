package main.java.edu.uvg;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class VectorHeap<E extends Comparable<E>> implements java.util.PriorityQueue<E> {
    private ArrayList<E> heap;

    public VectorHeap() {
        heap = new ArrayList<>();
    }

    // Agregar un elemento
    @Override
    public boolean add(E e) {
        heap.add(e);
        siftUp(heap.size() - 1);
        return true;
    }

    // Retirar el elemento de mayor prioridad (mínimo)
    @Override
    public E remove() {
        if (heap.isEmpty()) return null;
        E result = heap.get(0);
        int lastIdx = heap.size() - 1;
        heap.set(0, heap.get(lastIdx));
        heap.remove(lastIdx);
        if (!heap.isEmpty()) siftDown(0);
        return result;
    }

    // Mirar el elemento de mayor prioridad sin retirarlo
    @Override
    public E peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    // Métodos auxiliares para mantener la propiedad del heap
    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(parent).compareTo(heap.get(index)) <= 0) break;
            swap(parent, index);
            index = parent;
        }
    }

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

    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    @Override
    public int size() { return heap.size(); }
    @Override
    public boolean isEmpty() { return heap.isEmpty(); }
    @Override
    public void clear() { heap.clear(); }
    // Otros métodos de PriorityQueue pueden implementarse según necesidad
}