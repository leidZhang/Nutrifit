package main.frontend.custom.table;

import main.frontend.custom.table.PaginationModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PaginationTable extends JTable {
    private PaginationModel model;

    public PaginationTable(DefaultTableModel model) {
        super(model);
        this.model = (PaginationModel) model;
        ((PaginationModel) model).displayRows();
    }

    public void nextPage() {
        model.nextPage();
    }

    public void prevPage() {
        model.prevPage();
    }

    public void gotoPage(int index) {
        model.gotoPage(index);
    }

    public void setModelData(List<Object[]> data) {
        model.setData(data);
    }
}
