package main.frontend.custom;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class RecordTableModel<T> extends DefaultTableModel {
    private List<T> data;
    private String[] header;
    private int pageSize;
    private int currentPage;

    public RecordTableModel(String[] header, List<T> data, int pageSize) {
        this.header = header;
        this.data = data;
        this.pageSize = pageSize;
        this.currentPage = 1;

        setHeader();
    }

    public List<T> getPageData() {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(currentPage * pageSize, data.size());
        return data.subList(startIndex, endIndex);
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageNumber() {
        return (int) Math.ceil((double) data.size() / pageSize);
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader() {
        for (String title : header) {
            addColumn(title);
        }
    }

    public void nextPage() {
        if (currentPage < getPageNumber()) {
            currentPage++;
        }
    }

    public void prevPage() {
        if (currentPage > 1) {
            currentPage--;
        }
    }

    public void gotoPage(int page) {
        if (page >= 1 && page <= getPageNumber()) {
            currentPage = page;
        }
    }
}
