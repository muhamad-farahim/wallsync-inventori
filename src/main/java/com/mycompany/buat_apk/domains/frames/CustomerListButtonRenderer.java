package com.mycompany.buat_apk.domains.frames;

public class CustomerListButtonRenderer extends javax.swing.JButton implements javax.swing.table.TableCellRenderer {
    public CustomerListButtonRenderer() {
        setOpaque(true);
    }
    @Override
    public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "View" : value.toString());
        setBackground(new java.awt.Color(9, 60, 93));
        setForeground(java.awt.Color.WHITE);
        setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        return this;
    }
}
