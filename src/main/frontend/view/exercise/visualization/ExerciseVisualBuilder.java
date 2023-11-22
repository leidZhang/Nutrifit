package main.frontend.view.exercise.visualization;

import main.frontend.common.ContentBuilder;
import main.frontend.custom.entry.NfEntry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExerciseVisualBuilder extends ContentBuilder{
    private final int[] ENTRY_DIMENSION = {20, 150};
    private Map<String, NfEntry> entries;
    private Map<String, JButton> buttons;
    private DefaultCategoryDataset barChartDataset;
    private JFreeChart barChart;

    public ExerciseVisualBuilder(JPanel page) {
        super(page);

        String[] entryNames = {"Start Date", "End Date"};
        entries = new LinkedHashMap<>();
        for(String name : entryNames) {
            entries.put(name, new NfEntry(ENTRY_DIMENSION[0], ENTRY_DIMENSION[1]));
            entries.get(name).setTitle(name);
        }

        String[] buttonNames = {"Apply", "Reset", "View Records"};
        buttons = new LinkedHashMap<>();
        for(String name : buttonNames) {
            buttons.put(name, new JButton(name));
        }

        barChartDataset = new DefaultCategoryDataset();
        barChart = ChartFactory.createBarChart(
                "Exercise log",
                "Date",
                "Total exercise minutes",
                barChartDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    @Override
    public void setUp() {
        constraints = new GridBagConstraints();
        page.setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridwidth = 0;
    }

    private void buildDateRow() {
        constraints.gridy = gridy++;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(5, 5,5, 5);
        constraints.gridx = 0;
        page.add(entries.get("Start Date"), constraints);
        constraints.gridx = 1;
        page.add(entries.get("End Date"), constraints);
        constraints.gridx = 2;
        page.add(buttons.get("Apply"), constraints);
        constraints.gridx = 3;
        page.add(buttons.get("Reset"), constraints);
        constraints.gridy = gridy++;
    }

    private void buildBarChart() {
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        ChartPanel chartPanel = new ChartPanel(barChart);
        page.add(chartPanel, constraints);
        constraints.gridy = gridy++;

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setMaximumBarWidth(0.15);
    }

    private void buildBottomRow() {
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        page.add(buttons.get("View Records"), constraints);
        constraints.gridy = gridy++;
    }
    @Override
    public void buildMainContent() {
        buildDateRow();
        buildBarChart();
        buildBottomRow();
    }

    public Map<String, NfEntry> getEntries() { return entries; }

    public Map<String, JButton> getButtons() {
        return buttons;
    }

    public DefaultCategoryDataset getDataSet() {
        return barChartDataset;
    }

}
