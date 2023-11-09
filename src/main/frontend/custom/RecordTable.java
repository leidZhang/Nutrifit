package main.frontend.custom;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class RecordTable extends JTable {
    private RecordTableModel model;

    public RecordTable(RecordTableModel model) {
        this.model = model;

        setModel(new AbstractTableModel() {
            @Override
            public int getRowCount() {
                int size = model.getPageSize();
                int remainSize = model.getPageNumber() - (model.getCurrentPage() - 1) * model.getPageSize();

                return Math.min(size, remainSize);
            }

            @Override
            public int getColumnCount() {
                return model.getHeader().length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return model.getPageData().get((model.getCurrentPage() - 1) * model.getPageSize() + rowIndex);
            }
        });
    }

    public void nextPage() {
        model.nextPage();
        ((AbstractTableModel) getModel()).fireTableDataChanged();
    }

    public void prevPage() {
        model.prevPage();
        ((AbstractTableModel) getModel()).fireTableDataChanged();
    }

    public void gotoPage(int index) {
        model.gotoPage(index);
        ((AbstractTableModel) getModel()).fireTableDataChanged();
    }
}
