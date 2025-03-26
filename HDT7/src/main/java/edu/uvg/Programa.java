package edu.uvg;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Clase principal para la gestión de productos utilizando un árbol binario de búsqueda.
 * 
 * @autor Javier Alvarado - 24546
 */
public class Programa {
    private static final String CSV_FILE = "c:/repo/HDT7/appliances.csv"; // Reemplaza con la ruta real
    private static BST bst = new BST(); // Asumiendo que BST es tu estructura de datos

    /**
     * Carga los datos desde un archivo CSV y los inserta en el árbol binario de búsqueda.
     */
    private static void cargarDatosCSV() {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            // Leer todas las líneas del CSV
            List<String[]> lineas = reader.readAll();
            if (lineas.isEmpty()) {
                System.out.println("El archivo CSV está vacío.");
                return;
            }

            // Obtener la cabecera (primera línea)
            String[] cabecera = lineas.get(0);

            // Encontrar los índices de las columnas necesarias
            int idxSKU = encontrarIndice(cabecera, "SKU");
            int idxPriceRetail = encontrarIndice(cabecera, "PRICE_RETAIL");
            int idxPriceCurrent = encontrarIndice(cabecera, "PRICE_CURRENT");
            int idxProductName = encontrarIndice(cabecera, "PRODUCT_NAME");
            int idxCategory = encontrarIndice(cabecera, "CATEGORY");

            // Verificar que todas las columnas necesarias estén presentes
            if (idxSKU == -1 || idxPriceRetail == -1 || idxPriceCurrent == -1 || idxProductName == -1 || idxCategory == -1) {
                System.out.println("Faltan columnas necesarias en el CSV.");
                return;
            }

            // Procesar cada línea de datos (omitir la cabecera)
            for (int i = 1; i < lineas.size(); i++) {
                String[] datos = lineas.get(i);
                String sku = datos[idxSKU];
                double priceRetail = Double.parseDouble(datos[idxPriceRetail]);
                double priceCurrent = Double.parseDouble(datos[idxPriceCurrent]);
                String productName = datos[idxProductName];
                String category = datos[idxCategory];

                // Crear objeto Producto e insertarlo en el BST
                Producto producto = new Producto(sku, priceRetail, priceCurrent, productName, category);
                bst.insertar(producto);
            }
            System.out.println("Datos cargados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo CSV: " + e.getMessage());
        } catch (CsvException e) {
            System.out.println("Error al parsear el archivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir precios a números: " + e.getMessage());
        }
    }

    /**
     * Encuentra el índice de una columna en la cabecera del archivo CSV.
     * 
     * @param cabecera La cabecera del archivo CSV.
     * @param nombreColumna El nombre de la columna a buscar.
     * @return El índice de la columna, o -1 si no se encuentra.
     */
    private static int encontrarIndice(String[] cabecera, String nombreColumna) {
        for (int i = 0; i < cabecera.length; i++) {
            if (cabecera[i].equalsIgnoreCase(nombreColumna)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Clase que representa un producto.
     */
    static class Producto {
        String sku;
        double priceRetail;
        double priceCurrent;
        String productName;
        String category;

        /**
         * Constructor para crear un nuevo producto.
         * 
         * @param sku El SKU del producto.
         * @param priceRetail El precio de venta al por menor del producto.
         * @param priceCurrent El precio actual del producto.
         * @param productName El nombre del producto.
         * @param category La categoría del producto.
         */
        Producto(String sku, double priceRetail, double priceCurrent, String productName, String category) {
            this.sku = sku;
            this.priceRetail = priceRetail;
            this.priceCurrent = priceCurrent;
            this.productName = productName;
            this.category = category;
        }
    }

    /**
     * Clase que representa un árbol binario de búsqueda (BST).
     */
    static class BST {
        /**
         * Inserta un producto en el árbol binario de búsqueda.
         * 
         * @param producto El producto a insertar.
         */
        void insertar(Producto producto) {
            // Implementación del BST aquí
        }
    }

    /**
     * Método principal que ejecuta el programa.
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        cargarDatosCSV();
    }
}