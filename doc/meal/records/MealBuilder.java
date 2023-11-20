package main.frontend.view.zdump.records;

import main.frontend.common.ContentBuilder;
import main.frontend.custom.table.PaginationModel;
import main.frontend.custom.table.PaginationTable;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @Author : WuXiang
 * @Description :
 * @Date : Created by WuXiang on 2023/11/17 12:43
 * @Email : 1796207106@qq.com
 * @Since : JDK 1.8
 * @PackageName : main.frontend.view.meal
 * @ProjectName : EECS3311Project
 * @Version : 1.0.0
 */
public class MealBuilder  extends ContentBuilder {

    private JButton infoButton;
    private JButton deleteButton;
    private PaginationTable table;
    private JButton nextButton;
    private JButton prevButton;
    private JTextField startTime;
    private JTextField endTime;
    private JButton searchButton;

    public MealBuilder(JPanel page) {
        super(page);
    }

    public JButton getInfoButton() {
        return infoButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public PaginationTable getTable() {
        return table;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getPrevButton() {
        return prevButton;
    }

    public JTextField getStartTime() {
        return startTime;
    }

    public JTextField getEndTime() {
        return endTime;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    @Override
    public void buildMainContent() {

    }

    @Override
    public void setUp() {
        // set up GridBagLayout
        GridBagLayout gridBagLayout = new GridBagLayout();
        page.setLayout(gridBagLayout);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
    }

    private JPopupMenu setPopMenu(PaginationTable table) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem selectItem = new JMenuItem("select"); // only for demonstration
        ActionListener selectListener = e -> {
            String message = "";
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                TableModel model = table.getModel();
                int columnCount = model.getColumnCount();
                Object[] row = new Object[columnCount];
                for (int i=0; i<columnCount; i++) {
                    row[i] = model.getValueAt(selectedRow, i);
                    message += row[i] + "--";
                }
                System.out.println(message.substring(0, message.length() - 2));
            }
        };
        selectItem.addActionListener(selectListener);
        popupMenu.add(selectItem);

        return popupMenu;
    }

    @Override
    public void buildTitle(String title) {
        // set up button attributes
        JLabel label = new JLabel(title); // get login user info
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(200, 20));
        label.setBackground(Color.black);
        // set up common constraints for buttons
         constraints.anchor = GridBagConstraints.NORTHWEST; // align: top left
         constraints.weightx = 1.0; // allocate horizontal space
         constraints.weighty = 1.0; // allocate vertical space
         constraints.insets = new Insets(5, 5, 5, 5); // component border
        // add button to the first row and first column
         constraints.gridx = 0; // column 0
         constraints.gridy = 0; // row 0
         page.add(label,  constraints);
    }

    public void createMealTable() {
        java.util.List<Object[]> rowList = new ArrayList<>();
        PaginationModel model = new PaginationModel(rowList, 5);
        model.setColumnIdentifiers(new Object[]{"#","Date", "Type", "Total Calories (kCal)"});
        table = new PaginationTable(model);
        table.setRowHeight(25);
        Font font = new Font("Arial", Font.PLAIN, 16);
        table.setFont(font);
        table.getTableHeader().setFont(font);
        JPopupMenu popupMenu = setPopMenu(table);
        table.setComponentPopupMenu(popupMenu); // set pop menu
        // hide id
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

//         table.addMouseListener(mealTableListener);
         constraints.gridwidth= GridBagConstraints.REMAINDER;
         constraints.gridx = 0;
         constraints.gridy = 3;
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900, 500));
        page.add(scrollPane,  constraints);

    }

    public void createAddButton(ActionListener listener) {
        //add insert button
        constraints.gridwidth= 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        JButton addButton = new JButton("insert");
        //add listener
        addButton.addActionListener(listener);
        page.add(addButton, constraints);

    }

    public JButton createDeleteButton(ActionListener deleteListener) {
        //add delete button
        constraints.gridwidth= 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        deleteButton = new JButton("delete");
        page.add(deleteButton, constraints);
        //add listener
        deleteButton.addActionListener(deleteListener);
        deleteButton.setEnabled(false);
        return deleteButton;
    }

    public void createSearchTextFieldAndButton(ActionListener searchListener) {
        //add search button
        startTime = new JTextField("", 10);
        constraints.gridwidth= 2;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        page.add(startTime, constraints);

        endTime = new JTextField("", 10);
        constraints.gridwidth= 2;
        constraints.gridheight = 1;
        constraints.gridx = 3;
        constraints.gridy = 2;
        page.add(endTime, constraints);

        constraints.gridwidth= 1;
        constraints.gridheight = 1;
        constraints.gridx = 5;
        constraints.gridy = 2;
        searchButton = new JButton("Period Visualizion");
        searchButton.addActionListener(searchListener);
        page.add(searchButton, constraints);
    }
    public void createPageButtons() {
        //add page button
        constraints.gridwidth= 1;
        constraints.gridheight = 1;
        constraints.gridx = 4;
        constraints.gridy = 1;
        nextButton = new JButton("Next Page");
//        ActionListener nextListener = e -> table.nextPage();
//        nextButton.addActionListener(nextListener);
        page.add(nextButton, constraints);

        constraints.gridwidth= 1;
        constraints.gridheight = 1;
        constraints.gridx = 5;
        constraints.gridy = 1;
        prevButton = new JButton("Prev Page");
//        ActionListener prevListener = e -> table.prevPage();
//        prevButton.addActionListener(previousListener);
        page.add(prevButton, constraints);

    }

    public void createInfoButton(ActionListener infoListener){
        //add info button
        constraints.gridwidth= 1;
        constraints.gridheight = 1;
        constraints.gridx = 3;
        constraints.gridy = 1;
        infoButton = new JButton("info");
        page.add(infoButton, constraints);
        infoButton.setEnabled(false);
        infoButton.addActionListener(infoListener);
    }


}
