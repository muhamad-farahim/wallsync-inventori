/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buat_apk.domains.frames;

/**
 *
 * @author DANDY
 */
import java.util.*;
import javax.swing.table.AbstractTableModel;
import com.mycompany.buat_apk.domains.entities.stocks.TransactionItem;

public class TransactionTableModel extends AbstractTableModel {

    private final String[] columns = {
        "TIPE",
        "ID",
        "PRODUCT",
        "QTY",
        "PRICE",
        "CUSTOMER",
        "DATE",
        "DESCRIPTION"
            
    };

    private List<TransactionItem> data;

    public TransactionTableModel(
        List<TransactionItem> data
    ) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Object getValueAt(int row, int col) {

        TransactionItem item = data.get(row);

        return switch (col) {
            case 0 -> item.getQuantity() > 0 ? "PURCHASE" : "SALES";
            case 1 -> item.getId();
            case 2 -> item.getProductName();
            case 3 -> Math.abs(item.getQuantity());
            case 4 -> item.getPrice();
            case 5 -> item.getCustomerName();
            case 6 -> item.getCreatedAt();
            case 7 -> item.getDescription();
            default -> null;
        };
    }
}
