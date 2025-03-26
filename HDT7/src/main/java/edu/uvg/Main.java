package edu.uvg;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para la gestión de productos utilizando un árbol binario de búsqueda.
 * 
 * @autor Javier Alvarado - 24546
 */
public class Main {
    private static BST<Producto> bst = new BST<>();
    private static final String CSV_FILE = "c:/repo/HDT7/appliances.csv"; 

    /**
     * Método principal que ejecuta el menú de opciones.
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== Menú ===");
            System.out.println("1. Cargar datos desde CSV");
            System.out.println("2. Buscar producto por SKU");
            System.out.println("3. Listar productos ordenados por SKU");
            System.out.println("4. Listar productos ordenados por precio ascendente");
            System.out.println("5. Listar productos ordenados por precio descendente");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    cargarDatosCSV();
                    break;
                case 2:
                    buscarProductoPorSKU(scanner);
                    break;
                case 3:
                    listarPorSKU();
                    break;
                case 4:
                    listarPorPrecioAscendente();
                    break;
                case 5:
                    listarPorPrecioDescendente();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    /**
     * Carga los datos desde un archivo CSV y los inserta en el árbol binario de búsqueda.
     */
    private static void cargarDatosCSV() {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            List<String[]> lineas = reader.readAll();
            if (lineas.isEmpty()) {
                System.out.println("El archivo CSV está vacío.");
                return;
            }

            // Obtener la cabecera
            String[] cabecera = lineas.get(0);

            // Encontrar índices de las columnas
            int idxSKU = encontrarIndice(cabecera, "SKU");
            int idxPriceRetail = encontrarIndice(cabecera, "PRICE_RETAIL");
            int idxPriceCurrent = encontrarIndice(cabecera, "PRICE_CURRENT");
            int idxProductName = encontrarIndice(cabecera, "PRODUCT_NAME");
            int idxCategory = encontrarIndice(cabecera, "CATEGORY");

            if (idxSKU == -1 || idxPriceRetail == -1 || idxPriceCurrent == -1 || idxProductName == -1 || idxCategory == -1) {
                System.out.println("Faltan columnas necesarias en el CSV.");
                return;
            }

            // Procesar datos (omitir cabecera)
            for (int i = 1; i < lineas.size(); i++) {
                String[] datos = lineas.get(i);
                String sku = datos[idxSKU];
                double priceRetail = convertirPrecio(datos[idxPriceRetail]);
                double priceCurrent = convertirPrecio(datos[idxPriceCurrent]);
                String productName = datos[idxProductName];
                String category = datos[idxCategory];

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
     * Convierte una cadena de texto que representa un precio a un número.
     * 
     * @param precioTexto La cadena de texto que representa el precio.
     * @return El precio convertido a un número.
     */
    private static double convertirPrecio(String precioTexto) {
        // Eliminar símbolos de moneda y comas
        precioTexto = precioTexto.replaceAll("[^\\d.]", "");
        return Double.parseDouble(precioTexto);
    }

    /**
     * Encuentra el índice de una columna en la cabecera del archivo CSV.
     * 
     * @param cabecera La cabecera del archivo CSV.
     * @param columna El nombre de la columna a buscar.
     * @return El índice de la columna, o -1 si no se encuentra.
     */
    private static int encontrarIndice(String[] cabecera, String columna) {
        for (int i = 0; i < cabecera.length; i++) {
            if (cabecera[i].equalsIgnoreCase(columna)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca un producto por su SKU en el árbol binario de búsqueda.
     * 
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     */
    private static void buscarProductoPorSKU(Scanner scanner) {
        System.out.print("Ingrese el SKU a buscar: ");
        String sku = scanner.nextLine();
        Producto dummy = new Producto(sku, 0, 0, "", "");
        Producto encontrado = bst.buscar(dummy);

        if (encontrado != null) {
            System.out.println("Producto encontrado:");
            System.out.println(encontrado);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    /**
     * Lista los productos ordenados por SKU.
     */
    private static void listarPorSKU() {
        System.out.println("\nProductos ordenados por SKU:");
        bst.inOrdenAscendente();
    }

    /**
     * Lista los productos ordenados por precio ascendente.
     */
    private static void listarPorPrecioAscendente() {
        List<Producto> lista = bst.toListInOrder();
        Collections.sort(lista, Comparator.comparingDouble(Producto::getPrice_Current));
        System.out.println("\nProductos ordenados por precio ascendente:");
        for (Producto p : lista) {
            System.out.println(p);
        }
    }

    /**
     * Lista los productos ordenados por precio descendente.
     */
    private static void listarPorPrecioDescendente() {
        List<Producto> lista = bst.toListInOrder();
        Collections.sort(lista, Collections.reverseOrder(Comparator.comparingDouble(Producto::getPrice_Current)));
        System.out.println("\nProductos ordenados por precio descendente:");
        for (Producto p : lista) {
            System.out.println(p);
        }
    }
}