package main.frontend.view.home;

import main.frontend.common.ContentBuilder;
import main.frontend.custom.entry.NfEntry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class HomeBuilder extends ContentBuilder {
    private final int[] ENTRY_DIMENSION = {20, 150};
    private Map<String, NfEntry> entries;
    private Map<String, JButton> buttons;
    private DefaultCategoryDataset lineChartDataSet;
    private JFreeChart lineChart;

    public HomeBuilder(JPanel page) {
        super(page);

        String[] entryNames = {"Start Date", "End Date", "Date", "Fat Lose"};
        entries = new LinkedHashMap<>();
        for (String name : entryNames) {
            entries.put(name, new NfEntry(ENTRY_DIMENSION[0], ENTRY_DIMENSION[1]));
            entries.get(name).setTitle(name);
        }

        String[] buttonNames = {"Apply", "Reset", "Calculate"};
        buttons = new LinkedHashMap<>();
        for (String name : buttonNames) {
            buttons.put(name, new JButton(name));
        }

        lineChartDataSet = new DefaultCategoryDataset();
        lineChart = ChartFactory.createLineChart(
            "Calorie Intake and Expenditure",
            "Date",
            "kCal",
            lineChartDataSet,
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
        constraints.gridwidth = 0; // one component per row
    }

    private void buildDateRow() {
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

    private void buildLineChart() {
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        ChartPanel chartPanel = new ChartPanel(lineChart);
        page.add(chartPanel, constraints);

        constraints.gridy = gridy++;
        constraints.gridwidth = 4;
        JLabel predictLabel = new JLabel("Enter a Date to Calculate Fat Loss");
        predictLabel.setPreferredSize(new Dimension(500, 30));
        Font font = new Font("Arial", Font.BOLD, 18);
        predictLabel.setFont(font);
        page.add(predictLabel, constraints);
        constraints.gridy = gridy++;
    }

    private void buildPredictEntry() {
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        page.add(entries.get("Date"), constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 2;
        NfEntry entry = entries.get("Fat Lose");
        entry.setEditable(false);
        page.add(entry, constraints);
        constraints.gridy = gridy++;
    }

    private void buildBottomRow() {
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        page.add(buttons.get("Calculate"), constraints);
        constraints.gridy = gridy++;
    }

    @Override
    public void buildMainContent() {
        buildDateRow();
        buildLineChart();
        buildPredictEntry();
        buildBottomRow();
    }

    public Map<String, JButton> getButtons() {
        return buttons;
    }

    public Map<String, NfEntry> getEntries() {
        return entries;
    }

    public DefaultCategoryDataset getDataSet() {
        return lineChartDataSet;
    }
}
