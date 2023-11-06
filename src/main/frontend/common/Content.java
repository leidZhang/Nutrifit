package main.frontend.common;

import main.frontend.view.mainframe.FrontEnd;
import main.frontend.session.UserSession;

import javax.swing.*;

public abstract class Content implements IContent {
    protected UserSession instance = UserSession.getInstance();

    @Override
    public abstract String showContent(JPanel content, FrontEnd frontEnd);
}
