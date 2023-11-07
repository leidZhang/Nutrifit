package main.frontend.view.mainframe.component;

import main.frontend.common.ContentBuilder;
import main.frontend.view.mainframe.component.SideBarBuilder;

public class SideBarDirector {
    private SideBarBuilder builder;

    public SideBarDirector(ContentBuilder builder) {
        this.builder = (SideBarBuilder) builder;
    }

    public void constructSideBar(String username) {
        builder.setUp();
        builder.buildAccountPanel(username);
        builder.buildMainContent();
        builder.setListener();
        builder.buildFooter();
    }
}
