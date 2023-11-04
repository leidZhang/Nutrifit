package main.frontend.view.user;
import javax.swing.*;
import javax.swing.SpinnerDateModel;
import java.util.Date;

public class TimePickerPanel {
    private final JSpinner timeSpinner;

    public TimePickerPanel() {
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.HOUR_OF_DAY);
        timeSpinner = new JSpinner(model);

        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "yyyy-MM-dd");
        timeSpinner.setEditor(timeEditor);
    }

    public JComponent getComponent() {
        return timeSpinner;
    }

    public Date getSelectedTime() {
        return (Date) timeSpinner.getValue();
    }

    public void setSelectedTime(Date time) {
        timeSpinner.setValue(time);
    }
}
