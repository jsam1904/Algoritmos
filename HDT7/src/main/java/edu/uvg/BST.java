package edu.uvg;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un nodo en el árbol binario de búsqueda.
 * 
 * @param <E> El tipo de dato almacenado en el nodo.
 * @autor Javier Alvarado - 24546
 */
class Nodo<E> {
    E data;
    Nodo<E> izquierda, derecha;

    /**
     * Constructor para crear un nuevo nodo.
     * 
     * @param data El dato a almacenar en el nodo.
     */
    public Nodo(E data) {
        this.data = data;
        this.izquierda = this.derecha = null;
    }
}

/**
 * Clase que representa un árbol binario de búsqueda (BST).
 * 
 * @param <E> El tipo de dato almacenado en el árbol, que debe ser comparable.
 * @autor TuNombre
 */
class BST<E extends Comparable<E>> {
    private Nodo<E> raiz;

    /**
     * Constructor para crear un nuevo árbol binario de búsqueda.
     */
    public BST() {
        this.raiz = null;
    }

    /**
     * Inserta un nuevo dato en el árbol.
     * 
     * @param data El dato a insertar.
     */
    public void insertar(E data) {
        raiz = insertarRec(raiz, data);
    }

    /**
     * Método recursivo para insertar un nuevo dato en el árbol.
     * 
     * @param raiz El nodo raíz del subárbol.
     * @param data El dato a insertar.
     * @return El nodo raíz del subárbol modificado.
     */
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

    /**
     * Busca un dato en el árbol.
     * 
     * @param data El dato a buscar.
     * @return El dato encontrado, o null si no se encuentra.
     */
    public E buscar(E data) {
        return buscarRec(raiz, data);
    }

    /**
     * Método recursivo para buscar un dato en el árbol.
     * 
     * @param raiz El nodo raíz del subárbol.
     * @param data El dato a buscar.
     * @return El dato encontrado, o null si no se encuentra.
     */
    private E buscarRec(Nodo<E> raiz, E data) {
        if (raiz == null || raiz.data.equals(data)) {
            return raiz != null ? raiz.data : null;
        }
        return data.compareTo(raiz.data) < 0 ? buscarRec(raiz.izquierda, data) : buscarRec(raiz.derecha, data);
    }

    /**
     * Imprime los datos del árbol en orden ascendente.
     */
    public void inOrdenAscendente() {
        inOrdenAscendenteRec(raiz);
    }

    /**
     * Método recursivo para imprimir los datos del árbol en orden ascendente.
     * 
     * @param raiz El nodo raíz del subárbol.
     */
    private void inOrdenAscendenteRec(Nodo<E> raiz) {
        if (raiz != null) {
            inOrdenAscendenteRec(raiz.izquierda);
            System.out.println(raiz.data);
            inOrdenAscendenteRec(raiz.derecha);
        }
    }

    /**
     * Convierte el árbol a una lista en orden ascendente.
     * 
     * @return Una lista con los datos del árbol en orden ascendente.
     */
    public List<E> toListInOrder() {
        List<E> lista = new ArrayList<>();
        toListInOrderRec(raiz, lista);
        return lista;
    }

    /**
     * Método recursivo para convertir el árbol a una lista en orden ascendente.
     * 
     * @param nodo El nodo raíz del subárbol.
     * @param lista La lista donde se almacenarán los datos.
     */
    private void toListInOrderRec(Nodo<E> nodo, List<E> lista) {
        if (nodo != null) {
            toListInOrderRec(nodo.izquierda, lista);
            lista.add(nodo.data);
            toListInOrderRec(nodo.derecha, lista);
        }
    }
}