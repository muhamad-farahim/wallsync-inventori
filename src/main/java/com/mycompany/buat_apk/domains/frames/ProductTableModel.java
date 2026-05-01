package com.mycompany.buat_apk.domains.frames;

import javax.swing.table.AbstractTableModel;

import com.mycompany.buat_apk.domains.entities.products.ProductWithStocks;

import java.util.List;

public class ProductTableModel extends AbstractTableModel {
    private final List<ProductWithStocks> products;
    private final String[] columnNames = {"Nama Produk", "Kategori", "Harga", "Stok", "Status", "Action"};

    public ProductTableModel(List<ProductWithStocks> products) {
        this.products = products;
    }

    @Override
    public int getRowCount() { return products.size(); }

    @Override
    public int getColumnCount() { return columnNames.length; }

    @Override
    public String getColumnName(int col) { return columnNames[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        ProductWithStocks p = products.get(row);
        return switch (col) {
            case 0 -> p.getName();
            case 1 -> p.getCategoryName();
            case 2 -> p.getPrice();
            case 3 -> p.getStocks();
            case 4 -> p.getStocks() < 1 ? "Out of Stock" : "In stock";
            case 5 -> "View";
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 5; // Only the 'Action' column is clickable/editable
    }

    // This is how we retrieve the ID later
    public ProductWithStocks getProductAt(int row) {
        return products.get(row);
    }
}
