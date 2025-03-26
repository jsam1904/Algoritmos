package edu.uvg;

public class Producto {
    private String SKU;
    private double Price_Retail;
    private double Price_Current;
    private String Product_Name;
    private String Category;

    // Constructor
    public Producto(String SKU, double Price_Retail, double Price_Current, String Product_Name, String Category) {
        this.SKU = SKU;
        this.Price_Retail = Price_Retail;
        this.Price_Current = Price_Current;
        this.Product_Name = Product_Name;
        this.Category = Category;
    }

    // Getters
    public String getSKU() { return SKU; }
    public double getPrice_Retail() { return Price_Retail; }
    public double getPrice_Current() { return Price_Current; }
    public String getProduct_Name() { return Product_Name; }
    public String getCategory() { return Category; }

    // toString para mostrar la información
    @Override
    public String toString() {
        return "SKU: " + SKU + ", Price_Retail: " + Price_Retail + ", Price_Current: " + Price_Current +
               ", Product_Name: " + Product_Name + ", Category: " + Category;
    }
}