package main.frontend.common;

import main.frontend.FrontEnd;
import main.frontend.session.UserSession;

import javax.swing.*;
import java.util.Map;

public abstract class Content implements IContent {
    protected UserSession instance = UserSession.getInstance();

    @Override
    public abstract String showContent(JPanel content, FrontEnd frontEnd);
}
