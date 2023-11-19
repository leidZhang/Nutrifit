package main.frontend.view.zdump.visualization;

import main.frontend.common.ContentBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class VisualBuilder  extends ContentBuilder {

    VisualWidget widget;
    ArrayList<ChartPanel> charts = new ArrayList<>();
    private int gridx = 0;

    public VisualBuilder(JPanel page) {
        super(page);
    }


    VisualBuilder initWidget(String title, int x, int y, int width, int height){
        widget = new VisualWidget();
        widget.setTitle(title);
//        widget.getContentPane().setBackground(Color.WHITE);
        widget.setLocation(x,y);
        widget.setSize(width,height);
        widget.setLayout(new GridBagLayout());
        widget.setVisible(true);
        widget.setModal(false);
        constraints= new GridBagConstraints();
        return this;
    }

    VisualBuilder createChart(Map<String,Float> maps,String title){

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Float> entry : maps.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }
        JFreeChart chart = ChartFactory.createPieChart(
                    title,
                   dataset,
                false, true, true);
        ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setSize(new Dimension(400, 300));
        charts.add(chartPanel);
        return this;
    }

    public void showChart(){
        for (ChartPanel chartPanel : charts) {
            constraints.gridx = gridx++;
            constraints.gridy = 0;
            constraints.gridwidth = 1;
            constraints.gridheight = 1;
            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.fill = GridBagConstraints.BOTH;
            widget.add(chartPanel,constraints);
            chartPanel.setVisible(true);
        }
        System.out.println("show charts");
        widget.revalidate();
        widget.repaint();
    }

    @Override
    public void buildMainContent() {

    }

    @Override
    public void setUp() {

    }
}
