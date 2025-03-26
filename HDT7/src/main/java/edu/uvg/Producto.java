package edu.uvg;

/**
 * Clase que representa un producto.
 * 
 * @autor Javier Alvarado - 24546
 */
public class Producto implements Comparable<Producto> {
    private String SKU;
    private double Price_Retail;
    private double Price_Current;
    private String Product_Name;
    private String Category;

    /**
     * Constructor para crear un nuevo producto.
     * 
     * @param SKU El SKU del producto.
     * @param Price_Retail El precio de venta al por menor del producto.
     * @param Price_Current El precio actual del producto.
     * @param Product_Name El nombre del producto.
     * @param Category La categoría del producto.
     */
    public Producto(String SKU, double Price_Retail, double Price_Current, String Product_Name, String Category) {
        this.SKU = SKU;
        this.Price_Retail = Price_Retail;
        this.Price_Current = Price_Current;
        this.Product_Name = Product_Name;
        this.Category = Category;
    }

    /**
     * Obtiene el SKU del producto.
     * 
     * @return El SKU del producto.
     */
    public String getSKU() { return SKU; }

    /**
     * Obtiene el precio de venta al por menor del producto.
     * 
     * @return El precio de venta al por menor del producto.
     */
    public double getPrice_Retail() { return Price_Retail; }

    /**
     * Obtiene el precio actual del producto.
     * 
     * @return El precio actual del producto.
     */
    public double getPrice_Current() { return Price_Current; }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public String getProduct_Name() { return Product_Name; }

    /**
     * Obtiene la categoría del producto.
     * 
     * @return La categoría del producto.
     */
    public String getCategory() { return Category; }

    /**
     * Compara este producto con otro producto por SKU.
     * 
     * @param otro El otro producto a comparar.
     * @return Un valor negativo, cero o positivo si este producto es menor, igual o mayor que el otro producto.
     */
    @Override
    public int compareTo(Producto otro) {
        return this.SKU.compareTo(otro.SKU);
    }

    /**
     * Compara este producto con otro objeto para verificar si son iguales.
     * 
     * @param obj El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto otro = (Producto) obj;
        return SKU.equals(otro.SKU);
    }

    /**
     * Calcula el código hash del producto basado en el SKU.
     * 
     * @return El código hash del producto.
     */
    @Override
    public int hashCode() {
        return SKU.hashCode();
    }

    /**
     * Devuelve una representación en texto del producto.
     * 
     * @return Una cadena de texto que representa el producto.
     */
    @Override
    public String toString() {
        return "SKU: " + SKU + ", Price_Retail: " + Price_Retail + ", Price_Current: " + Price_Current +
               ", Product_Name: " + Product_Name + ", Category: " + Category;
    }
}