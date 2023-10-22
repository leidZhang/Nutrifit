package main;

import main.frontend.FrontEnd;
import main.frontend.view.home.HomePage;
import main.frontend.view.IContent;
import main.frontend.view.user.UserProfile;

public class Client {
    public static void main(String[] args) {
        FrontEnd window = new FrontEnd();
        window.setVisible(true);
        IContent up = new UserProfile();
        IContent hp = new HomePage();
        window.switchContentPanel(up);
    }
}
