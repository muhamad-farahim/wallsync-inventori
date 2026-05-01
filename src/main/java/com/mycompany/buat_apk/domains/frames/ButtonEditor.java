package com.mycompany.buat_apk.domains.frames;

import javax.swing.*;

import com.mycompany.buat_apk.domains.entities.products.ProductWithStocks;
import com.mycompany.buat_apk.frames.frame_manProduk;

import java.awt.Component;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private final frame_manProduk parentFrame; 

    public ButtonEditor(JCheckBox checkBox, frame_manProduk parent) {
        super(checkBox);
        this.parentFrame = parent;
        button = new JButton();
        button.setOpaque(true);
        
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.table = table;
        button.setText((value == null) ? "View" : value.toString());
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        int modelRow = table.convertRowIndexToModel(table.getEditingRow());
        
        ProductTableModel model = (ProductTableModel) table.getModel();
        ProductWithStocks p = model.getProductAt(modelRow);
        
        parentFrame.openProductDetail(p.getId());
        
        return "View";
    }
}
