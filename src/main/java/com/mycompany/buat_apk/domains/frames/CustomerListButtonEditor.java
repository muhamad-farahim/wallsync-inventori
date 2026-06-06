package com.mycompany.buat_apk.domains.frames;

import com.mycompany.buat_apk.frames.MainFrame;

public class CustomerListButtonEditor extends javax.swing.DefaultCellEditor {
    private final javax.swing.JButton button;
    private final MainFrame mainFrameParent;
    private Long currentId;

    public CustomerListButtonEditor(javax.swing.JTable table, MainFrame mainFrameParent) {
        super(new javax.swing.JTextField());
        this.mainFrameParent = mainFrameParent;

        this.button = new javax.swing.JButton();
        this.button.setOpaque(true);
        this.button.setBackground(new java.awt.Color(9, 60, 93));
        this.button.setForeground(java.awt.Color.WHITE);
        this.button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));

        this.button.addActionListener(e -> {
            if (currentId != null) {
                fireEditingStopped(); 

                this.mainFrameParent.goTo("CUSTOMER_DETAIL", currentId);
            }
        });
    }

    @Override
    public java.awt.Component getTableCellEditorComponent(javax.swing.JTable table, Object value,
            boolean isSelected, int row, int column) {
        // Read index 0 (CUSTOMER ID column) safely
        this.currentId = (Long) table.getValueAt(row, 0);
        this.button.setText((value == null) ? "View" : value.toString());
        return this.button;
    }

    @Override
    public Object getCellEditorValue() {
        return this.button.getText();
    }
}
