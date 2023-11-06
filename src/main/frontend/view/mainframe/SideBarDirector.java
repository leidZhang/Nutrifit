package main.frontend.view.mainframe;

import main.frontend.common.PageBuilder;

public class SideBarDirector {
    private SideBarBuilder builder;

    public SideBarDirector(PageBuilder builder) {
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
