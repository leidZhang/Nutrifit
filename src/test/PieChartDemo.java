package test;

// 导入相关的类
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartDemo extends JFrame {
    public PieChartDemo() {
        // 设置窗口的标题
        setTitle("Pie Chart Demo");
        // 设置窗口的大小
        setSize(800, 600);
        // 设置窗口的关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 创建一个面板，用于放置饼图
        JPanel panel = new JPanel();
        // 设置面板的布局为网格布局，一行两列，间隔为10
        panel.setLayout(new GridLayout(1, 2, 10, 10));
        // 创建第一个饼图的数据集，用于存放数据
        DefaultPieDataset dataset1 = new DefaultPieDataset();
        // 向数据集中添加数据，格式为（名称，值）
        dataset1.setValue("A", 20);
        dataset1.setValue("B", 30);
        dataset1.setValue("C", 40);
        dataset1.setValue("D", 10);
        // 创建第一个饼图对象，使用ChartFactory类的createPieChart方法
        // 参数分别为：标题，数据集，是否显示图例，是否显示工具提示，是否生成URL
        JFreeChart chart1 = ChartFactory.createPieChart("Pie Chart 1", dataset1, true, true, false);
        // 创建第一个饼图的面板，使用ChartPanel类
        // 参数为：饼图对象
        ChartPanel chartPanel1 = new ChartPanel(chart1);
        // 将第一个饼图的面板添加到主面板中
        panel.add(chartPanel1);

        // 创建第二个饼图的数据集，用于存放数据
        DefaultPieDataset dataset2 = new DefaultPieDataset();
        // 向数据集中添加数据，格式为（名称，值）
        dataset2.setValue("E", 15);
        dataset2.setValue("F", 25);
        dataset2.setValue("G", 35);
        dataset2.setValue("H", 25);
        // 创建第二个饼图对象，使用ChartFactory类的createPieChart方法
        // 参数分别为：标题，数据集，是否显示图例，是否显示工具提示，是否生成URL
        JFreeChart chart2 = ChartFactory.createPieChart("Pie Chart 2", dataset2, true, true, false);
        // 创建第二个饼图的面板，使用ChartPanel类
        // 参数为：饼图对象
        ChartPanel chartPanel2 = new ChartPanel(chart2);
        // 将第二个饼图的面板添加到主面板中
        panel.add(chartPanel2);

        // 将主面板添加到窗口中
        add(panel);
        // 设置窗口可见
        setVisible(true);
    }

    public static void main(String[] args) {
        // 创建一个PieChartDemo对象，调用构造方法
        PieChartDemo demo = new PieChartDemo();
    }
}

