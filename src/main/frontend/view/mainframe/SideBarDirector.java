package main.frontend.view.mainframe;

import main.frontend.common.ContentBuilder;

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
