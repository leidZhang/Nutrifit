package main.frontend.common;

import main.frontend.view.mainframe.IContent;
import main.frontend.view.mainframe.IMainframe;
import main.frontend.session.UserSession;

import javax.swing.*;
import java.lang.ref.WeakReference;

public abstract class Content implements IContent {
    protected UserSession instance = UserSession.getInstance();
    protected WeakReference<IMainframe> frontEnd;

    public Content() {
        // default constructor
    }

    @Override
    public abstract String showContent(JPanel content);

    protected abstract void mount(JPanel content);

    public void setMainframe(IMainframe frontEnd) {
        this.frontEnd = new WeakReference<>(frontEnd);
    }
}
