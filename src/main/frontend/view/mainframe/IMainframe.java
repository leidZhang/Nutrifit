package main.frontend.view.mainframe;

import main.frontend.common.Content;
import main.frontend.common.IContent;


public interface IMainframe {
    void switchContentPanel(IContent content);
    void initialize();
}
