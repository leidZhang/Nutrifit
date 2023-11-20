package main.frontend.view.home;

import main.frontend.common.Content;
import main.frontend.common.ContentBuilder;
import main.frontend.custom.entry.NfEntry;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Map;


public class Home extends Content {
    private Map<String, NfEntry> entries;
    private Map<String, JButton> buttons;
    private DefaultCategoryDataset dataSet;

    @Override
    public String showContent(JPanel content) {
        ContentBuilder builder = new HomeBuilder(content);
        HomeDirector director = new HomeDirector(builder);
        director.constructPage("Welcome to Nutrifit");

        entries = ((HomeBuilder) builder).getEntries();
        buttons = ((HomeBuilder) builder).getButtons();
        dataSet = ((HomeBuilder) builder).getDataSet();

        return "Switch to Home Page";
    }
}
