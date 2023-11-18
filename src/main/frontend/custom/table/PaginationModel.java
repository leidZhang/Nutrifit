package main.frontend.custom.table;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PaginationModel extends DefaultTableModel {
    private List<Object[]> data;
    private int pageSize;
    private int currentPage;

    public PaginationModel(List<Object[]> data, int pageSize) {
        this.data = data;
        this.pageSize = pageSize;
        this.currentPage = 1;
    }

    public void displayRows() {
        while (getRowCount() > 0) {
            removeRow(0);
        }

        List<Object[]> pageData = getPageData();
        System.out.println(pageData.size());
        for (Object[] record : pageData) {
            addRow(record);
        }
    }

    private List<Object[]> getPageData() {
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(currentPage * pageSize, data.size());
        return data.subList(start, end);
    }

    public int getPageCount() {
        return (int) Math.ceil((double) data.size() / pageSize);
    }

    public void nextPage() {
        if (currentPage < getPageCount()) {
            currentPage++;
            displayRows();
        }
    }

    public void prevPage() {
        if (currentPage > 1) {
            currentPage--;
            displayRows();
        }
    }

    public void gotoPage(int index) {
        if (index >= 1 && index <= getPageCount()) {
            currentPage = index;
            displayRows();
        }
    }

    public void setData(List<Object[]> data) {
        this.data = data;
        currentPage = 1;
        displayRows();
    }
}
