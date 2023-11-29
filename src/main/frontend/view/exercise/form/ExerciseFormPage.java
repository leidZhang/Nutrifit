package main.frontend.view.exercise.form;

import main.backend.common.Result;
import main.backend.exercise.IExerciseController;
import main.backend.exercise.entity.Exercise;
import main.backend.exercise.impl.ExerciseController;
import main.backend.user.entity.User;
import main.frontend.common.ContentBuilder;
import main.frontend.common.IContent;
import main.frontend.custom.dropdown.AutoComboBox;
import main.frontend.custom.entry.NfEntry;
import main.frontend.custom.table.PaginationTable;
import main.frontend.common.Content;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExerciseFormPage extends Content {
    private PaginationTable table;
    private Map<String, JComponent> entries;
    private IExerciseController controller = new ExerciseController();
    private Map<String, JButton> buttons;
    private JMenuItem deleteItem;

    private ActionListener handlePrev() {
        return e -> table.prevPage();
    }

    private ActionListener handleNext() {
        return e -> table.nextPage();
    }

    private ActionListener handleSubmit(User user, JPanel content) {
        return e -> {
            if (!verifyInput()) return;

            String type = (String) ((AutoComboBox) entries.get("Type")).getInput();
            Date date = Date.valueOf(((NfEntry) entries.get("Date")).getInput());
            int duration = Integer.parseInt(((NfEntry) entries.get("Duration(min)")).getInput());
            String intensity = (String) ((AutoComboBox) entries.get("Intensity")).getInput();

            Exercise exercise = new Exercise(date, type, intensity, duration);
            Result res = controller.save(exercise, user);
            if (res.getCode().equals("200")) {
                JOptionPane.showMessageDialog(content, "Record added!", "Message", JOptionPane.INFORMATION_MESSAGE);
                loadExerciseLog(user);
            } else {
                JOptionPane.showMessageDialog(content, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    private ActionListener handleDelete() {
        return e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Object[] rowData = model.getDataVector().elementAt(row).toArray();
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.NO_OPTION) return;

                int id = (int) rowData[0];
                Result res = controller.delete(id);
                if (res.getCode().equals("200")) {
                    JOptionPane.showMessageDialog(null, "Delete success", "Message", JOptionPane.INFORMATION_MESSAGE);
                    model.removeRow(row);
                } else {
                    JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private ActionListener handleBack() {
        return e -> {
            Map<String, IContent> map = frontEnd.get().getPageMap();
            frontEnd.get().switchContentPanel(map.get("Exercise"));
        };
    }

    private boolean verifyInput() {
        // get entries
        NfEntry dateEntry = (NfEntry) entries.get("Date");
        NfEntry durationEntry = (NfEntry) entries.get("Duration(min)");
        // clear message
        dateEntry.setMessage("");
        durationEntry.setMessage("");

        boolean flag = true;
        flag = flag & dateEntry.verifyInput();
        flag = flag & durationEntry.verifyInput();

        return flag;
    }

    private void setRegex() {
        ((NfEntry) entries.get("Date")).setRegex(
                "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",
                "data input format should be yyyy-mm-dd"
        );
        ((NfEntry) entries.get("Duration(min)")).setRegex(
                "^\\d+(\\.\\d+)?$",
                "Height value must be positives number"
        );
    }

    private void loadExerciseLog(User user) {
        Result result = controller.getByUsername(user.getUsername());
        String code = result.getCode();
        if (code.equals("200")) {
            List<Exercise> exercises = (List<Exercise>) result.getData();

            List<Object[]> rowList = new ArrayList<>();
            for (Exercise e : exercises) {
                int id = e.getId();
                Date date = e.getDate();
                String type = e.getType();
                String intensity = e.getIntensity();
                int duration = e.getDuration();
                long burnCalories = e.getBurnCalories();

                rowList.add(new Object[]{id, type, date, duration, intensity, burnCalories});
            }

            table.setModelData(rowList);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public String showContent(JPanel content) {
        User user = instance.getUser();

        //construct page
        ContentBuilder builder = new ExerciseFormBuilder(content);
        ExerciseFormDirector director = new ExerciseFormDirector(builder);
        director.constructPage("Exercise Record");

        table = ((ExerciseFormBuilder)builder).getTable();
        entries = ((ExerciseFormBuilder)builder).getEntries();
        buttons = ((ExerciseFormBuilder)builder).getButtons();
        deleteItem = ((ExerciseFormBuilder)builder).getDeleteItem();
        loadExerciseLog(user);

        buttons.get("Prev Page").addActionListener(handlePrev());
        buttons.get("Next Page").addActionListener(handleNext());
        buttons.get("Save").addActionListener(handleSubmit(user, content));
        buttons.get("Back").addActionListener(handleBack());
        deleteItem.addActionListener(handleDelete());

        setRegex();

        return "Switch to Exercise page";
    }
}