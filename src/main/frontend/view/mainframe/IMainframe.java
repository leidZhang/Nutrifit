package main.frontend.view.mainframe;

import main.frontend.common.IContent;

import javax.swing.*;
import java.util.Map;


public interface IMainframe {
    void switchContentPanel(IContent content);
    void initialize();
    Map<String, IContent> getPageMap();
    JComponent getContent();
}
