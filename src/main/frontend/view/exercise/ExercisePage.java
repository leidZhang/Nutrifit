package main.frontend.view.exercise;

import main.backend.exercise.IExerciseMapper;
import main.backend.exercise.entity.Exercise;
import main.backend.exercise.impl.ExerciseMapper;
import main.backend.user.entity.User;
import main.frontend.custom.table.PaginationModel;
import main.frontend.custom.table.PaginationTable;
import main.frontend.session.UserSession;
import main.frontend.common.Content;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExercisePage extends Content {
    UserSession instance = UserSession.getInstance();
    IExerciseMapper mapper = new ExerciseMapper(); // replace it when ExerciseController is completed

    private void setUp(JPanel content, GridBagConstraints gridBagConstraints) { // delete this and use builder pattern
        // set up GridBagLayout
        content.setLayout(new GridBagLayout());

        // set up common constraints for buttons
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST; // align: top left
        gridBagConstraints.weightx = 1.0; // allocate horizontal space
        gridBagConstraints.weighty = 1.0; // allocate vertical space
        gridBagConstraints.insets = new Insets(5, 5, 5, 5); // component border

        // add button to the first row and first column
        gridBagConstraints.gridx = 0; // column 0
        gridBagConstraints.gridy = 0; // row 0
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
    public String showContent(JPanel content) {
        // only for demonstrating pagination table, implement your own exercise page with builder pattern
        content.removeAll();

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setUp(content, gridBagConstraints);

        // get login user info
        JLabel label = new JLabel("Welcome to Exercise page");
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(700, 20));
        content.add(label, gridBagConstraints);

        User user = instance.getUser();
        String[] header = {"Date", "Type", "Intensity", "Burn Calories"};
        try {
            java.util.List<Exercise> list = mapper.getByUsername(user.getUsername());
            java.util.List<Object[]> rowList = new ArrayList<>();
            for (Exercise e : list) {
                int id = e.getId();
                Date date = e.getDate();
                String type = e.getType();
                int duration = e.getDuration();
                long burnCalories = e.getBurnCalories();

                rowList.add(new Object[]{id, date, type, duration, burnCalories});
            }

            PaginationModel model = new PaginationModel(rowList, 5);
            model.setColumnIdentifiers(new Object[]{"#", "Date", "Type", "Duration (min)", "Burn Calories"});

            PaginationTable table = new PaginationTable(model);
            table.setRowHeight(25);
            Font font = new Font("Arial", Font.PLAIN, 16);
            table.setFont(font);
            table.getTableHeader().setFont(font);
            JPopupMenu popupMenu = setPopMenu(table);
            table.setComponentPopupMenu(popupMenu); // set pop menu
            // hide id
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);

            gridBagConstraints.gridy = 1;
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(800, 500));
            content.add(scrollPane, gridBagConstraints);

            gridBagConstraints.gridy = 2;
            JButton nextButton = new JButton("Next Page");
            ActionListener nextListener = e -> table.nextPage();
            nextButton.addActionListener(nextListener);
            content.add(nextButton, gridBagConstraints);

            gridBagConstraints.gridy = 3;
            JButton prevButton = new JButton("Prev Page");
            ActionListener prevListener = e -> table.prevPage();
            prevButton.addActionListener(prevListener);
            content.add(prevButton, gridBagConstraints);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "Switch to Exercise page";
    }
}
