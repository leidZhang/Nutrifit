package test;

// 导入相关的类
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.util.Rotation;

public class RadarChartDemo extends JFrame {
    public RadarChartDemo() {
        // 设置窗口的标题
        setTitle("Radar Chart Demo");
        // 设置窗口的大小
        setSize(800, 600);
        // 设置窗口的关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 创建一个面板，用于放置雷达图
        JPanel panel = new JPanel();

        // 创建一个DefaultCategoryDataset对象，用于存放数据
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // 向数据集中添加数据，格式为（值，行标签，列标签）
        dataset.setValue(20, "Series 1", "A");
        dataset.setValue(30, "Series 1", "B");
        dataset.setValue(40, "Series 1", "C");
        dataset.setValue(50, "Series 1", "D");
        dataset.setValue(60, "Series 1", "E");
        dataset.setValue(15, "Series 2", "A");
        dataset.setValue(25, "Series 2", "B");
        dataset.setValue(35, "Series 2", "C");
        dataset.setValue(45, "Series 2", "D");
        dataset.setValue(55, "Series 2", "E");

        // 创建一个SpiderWebPlot对象，使用数据集作为参数
        SpiderWebPlot plot = new SpiderWebPlot(dataset);
        // 设置雷达图的最大值为100
        plot.setMaxValue(100);
        // 设置雷达图的方向为顺时针
        plot.setDirection(Rotation.CLOCKWISE);
        // 设置雷达图的颜色为红色和蓝色
        plot.setSeriesPaint(0, java.awt.Color.RED);
        plot.setSeriesPaint(1, java.awt.Color.BLUE);

        // 创建一个JFreeChart对象，使用标题和雷达图对象作为参数
        JFreeChart chart = new JFreeChart("Radar Chart", plot);
// 设置图表的字体为宋体
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
// 设置图表的背景为灰色
        chart.setBackgroundPaint(new Color(215, 215, 215));
// 设置图表的边框为黑色
        chart.setBorderPaint(java.awt.Color.BLACK);
// 创建一个ChartPanel对象，使用图表对象作为参数
        ChartPanel chartPanel = new ChartPanel(chart);

        panel.add(chartPanel);
        // 将主面板添加到窗口中
        add(panel);
        // 设置窗口可见
        setVisible(true);
    }

    public static void main(String[] args) {
        // 创建一个RadarChartDemo对象，调用构造方法
        RadarChartDemo demo = new RadarChartDemo();
    }
}

