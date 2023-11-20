package main.frontend.view.meal.visualization;

import main.frontend.common.ContentBuilder;
import main.frontend.custom.entry.NfEntry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MealVisualBuilder extends ContentBuilder {
    private final int DATE_ENTRY_HEIGHT = 20;
    private final int DATE_ENTRY_WIDTH = 200;
    private final String CFG_RECOMMEND = "CFG Recommendation";
    private final Dimension CHART_DIMENSION = new Dimension(420, 500);
    private final Object[][] CFG_RECOMMENDATION = new Object[][]{
            {50.0f, "Vegetables and Fruits"},
            {25.0f, "Protein"},
            {25.0f, "Whole Grain"}
    };
    private Map<String, JButton> buttonMap;
    private Map<String, NfEntry> entries;
    private JFreeChart pieChart;
    private DefaultPieDataset pieDataSet;
    private JFreeChart radarChart;
    private SpiderWebPlot radarPlot;
    private DefaultCategoryDataset radarDataSet;

    public MealVisualBuilder(JPanel page) {
        super(page);
        pieDataSet = new DefaultPieDataset();
        pieChart = ChartFactory.createPieChart("Intake Nutrients", pieDataSet, true, true, false);
        radarDataSet = new DefaultCategoryDataset();
        radarPlot = new SpiderWebPlot(radarDataSet);

        String[] entryNames = {"Start Date", "End Date"};
        entries = new LinkedHashMap<>();
        entries.put(entryNames[0], new NfEntry(DATE_ENTRY_HEIGHT, DATE_ENTRY_WIDTH));
        entries.put(entryNames[1], new NfEntry(DATE_ENTRY_HEIGHT, DATE_ENTRY_WIDTH));

        String[] buttonNames = {"Apply", "Reset", "View Records"};
        buttonMap = new LinkedHashMap<>();
        buttonMap.put(buttonNames[0], new JButton(buttonNames[0]));
        buttonMap.put(buttonNames[1], new JButton(buttonNames[1]));
        buttonMap.put(buttonNames[2], new JButton(buttonNames[2]));
    }

    private void buildDateRow() {
        // Add some empty space after the title
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(20), constraints);
        // cloned from other places
        constraints.gridwidth = 1; // one component per row

        int i = 0;
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            entry.getValue().setTitle(entry.getKey());
            constraints.gridx = i++;
            page.add(entry.getValue(), constraints);
        }

        constraints.gridx = i++;
        page.add(buttonMap.get("Apply"), constraints);
        constraints.gridx = i++;
        page.add(buttonMap.get("Reset"), constraints);

        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.gridy = gridy++;
    }

    private void buildRadarChart() {
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;

        radarPlot.setMaxValue(100);
        radarPlot.setDirection(Rotation.CLOCKWISE);
        radarPlot.setSeriesPaint(0, java.awt.Color.RED);
        radarPlot.setSeriesPaint(1, java.awt.Color.BLUE);

        plotCFGRecommendation(radarDataSet);
        radarChart = new JFreeChart("CFG Alignment", radarPlot);
        ChartPanel chartPanel = new ChartPanel(radarChart);
        chartPanel.setPreferredSize(CHART_DIMENSION);
        chartPanel.setFillZoomRectangle(true);

        page.add(chartPanel, constraints);
    }

    private void buildPieChart() {
        constraints.gridx = 2;
        constraints.gridwidth = 2;
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(CHART_DIMENSION);
        chartPanel.setFillZoomRectangle(true);
        page.add(chartPanel, constraints);
        constraints.gridy = gridy++;

        PiePlot plot = (PiePlot) pieChart.getPlot();
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {2}");
        plot.setLabelGenerator(labelGenerator);
    }

    private void buildRecordButton() {
        constraints.anchor = GridBagConstraints.CENTER;
        // Add some empty space after the title
        constraints.gridx = 1;
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(20), constraints);

        constraints.weightx = 0.5;
        constraints.gridy = gridy++;
        page.add(buttonMap.get("View Records"), constraints);

        constraints.weightx = 0;
        constraints.gridy = gridy++;
    }

    private void plotCFGRecommendation(DefaultCategoryDataset dataset) {
        for (Object[] obj : CFG_RECOMMENDATION) {
            float val = (float) obj[0];
            String category = (String) obj[1];
            dataset.addValue(val, CFG_RECOMMEND, category);
        }
    }

    @Override
    public void setUp() {
        constraints = new GridBagConstraints();
        page.setLayout(new GridBagLayout());

        constraints.weightx = 1;
        constraints.gridwidth = 0; // one component per row
    }

    @Override
    public void buildMainContent() {
        buildDateRow();
        buildRadarChart();
        buildPieChart();
        buildRecordButton();
    }

    public Map<String, JButton> getButtonMap() {
        return buttonMap;
    }

    public Map<String, NfEntry> getEntries() {
        return entries;
    }

    public DefaultPieDataset getPieDataSet() {
        return pieDataSet;
    }

    public DefaultCategoryDataset getRadarPlot() {
        return radarDataSet;
    }
}
