package edu.uvg;

import java.util.ArrayList;
import java.util.List;

class Nodo<E> {
    E data;
    Nodo<E> izquierda, derecha;

    public Nodo(E data) {
        this.data = data;
        this.izquierda = this.derecha = null;
    }
}

public class BST<E extends Comparable<E>> {
    private Nodo<E> raiz;

    public BST() {
        this.raiz = null;
    }

    // Insertar un elemento
    public void insertar(E data) {
        raiz = insertarRec(raiz, data);
    }

    private Nodo<E> insertarRec(Nodo<E> raiz, E data) {
        if (raiz == null) {
            return new Nodo<>(data);
        }
        if (data.compareTo(raiz.data) < 0) {
            raiz.izquierda = insertarRec(raiz.izquierda, data);
        } else if (data.compareTo(raiz.data) > 0) {
            raiz.derecha = insertarRec(raiz.derecha, data);
        }
        return raiz;
    }

    // Buscar un elemento
    public E buscar(E data) {
        return buscarRec(raiz, data);
    }

    private E buscarRec(Nodo<E> raiz, E data) {
        if (raiz == null || raiz.data.equals(data)) {
            return raiz != null ? raiz.data : null;
        }
        return data.compareTo(raiz.data) < 0 ? buscarRec(raiz.izquierda, data) : buscarRec(raiz.derecha, data);
    }

    // Listar en orden ascendente por SKU
    public void inOrdenAscendente() {
        inOrdenAscendenteRec(raiz);
    }

    private void inOrdenAscendenteRec(Nodo<E> raiz) {
        if (raiz != null) {
            inOrdenAscendenteRec(raiz.izquierda);
            System.out.println(raiz.data);
            inOrdenAscendenteRec(raiz.derecha);
        }
    }

    // Obtener lista de elementos en orden in-order
    public List<E> toListInOrder() {
        List<E> lista = new ArrayList<>();
        toListInOrderRec(raiz, lista);
        return lista;
    }

    private void toListInOrderRec(Nodo<E> nodo, List<E> lista) {
        if (nodo != null) {
            toListInOrderRec(nodo.izquierda, lista);
            lista.add(nodo.data);
            toListInOrderRec(nodo.derecha, lista);
        }
    }
}