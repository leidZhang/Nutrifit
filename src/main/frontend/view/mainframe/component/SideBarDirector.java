package main.frontend.view.mainframe.component;

import main.frontend.common.ContentBuilder;
import main.frontend.view.mainframe.component.SideBarBuilder;

public class SideBarDirector {
    private ContentBuilder builder;

    public SideBarDirector(ContentBuilder builder) {
        this.builder = (SideBarBuilder) builder;
    }

    public void constructSideBar(String username) {
        builder.setUp();
        ((SideBarBuilder)builder).buildAccountPanel(username);
        builder.buildMainContent();
        ((SideBarBuilder)builder).setListener();
        ((SideBarBuilder)builder).buildFooter();
    }
}
