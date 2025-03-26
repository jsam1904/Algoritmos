package edu.uvg;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase de prueba para la clase BST.
 * 
 * @autor Javier Alvarado - 24546
 */
public class TestBST {

    /**
     * Prueba la inserción y búsqueda de productos en el árbol binario de búsqueda.
     */
    @Test
    public void testInsertAndSearch() {
        BST<Producto> bst = new BST<>();

        // Insertar productos
        Producto p1 = new Producto("001", 10.0, 8.0, "Prod1", "Cat1");
        Producto p2 = new Producto("002", 20.0, 18.0, "Prod2", "Cat2");
        bst.insertar(p1);
        bst.insertar(p2);

        // Buscar productos
        Producto encontrado1 = bst.buscar(new Producto("001", 0, 0, "", ""));
        assertNotNull(encontrado1);
        assertEquals("001", encontrado1.getSKU());

        Producto encontrado2 = bst.buscar(new Producto("002", 0, 0, "", ""));
        assertNotNull(encontrado2);
        assertEquals("002", encontrado2.getSKU());

        // Buscar producto no existente
        Producto noEncontrado = bst.buscar(new Producto("003", 0, 0, "", ""));
        assertNull(noEncontrado);
    }
}