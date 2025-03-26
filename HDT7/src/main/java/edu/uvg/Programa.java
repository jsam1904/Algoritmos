package edu.uvg;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Programa {
    private static final String CSV_FILE = "ruta_al_archivo.csv"; // Reemplaza con la ruta real
    private static BST bst = new BST(); // Asumiendo que BST es tu estructura de datos

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

    // Método auxiliar para encontrar el índice de una columna
    private static int encontrarIndice(String[] cabecera, String nombreColumna) {
        for (int i = 0; i < cabecera.length; i++) {
            if (cabecera[i].equalsIgnoreCase(nombreColumna)) {
                return i;
            }
        }
        return -1;
    }

    // Clase Producto (ejemplo, ajusta según tu implementación)
    static class Producto {
        String sku;
        double priceRetail;
        double priceCurrent;
        String productName;
        String category;

        Producto(String sku, double priceRetail, double priceCurrent, String productName, String category) {
            this.sku = sku;
            this.priceRetail = priceRetail;
            this.priceCurrent = priceCurrent;
            this.productName = productName;
            this.category = category;
        }
    }

    // Clase BST (ejemplo, ajusta según tu implementación)
    static class BST {
        void insertar(Producto producto) {
            // Implementación del BST aquí
        }
    }

    public static void main(String[] args) {
        cargarDatosCSV();
    }
}