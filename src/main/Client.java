package main;

import main.frontend.FrontEnd;
import main.frontend.view.home.Home;
import main.frontend.view.IContent;
import main.frontend.view.user.Index;
import main.frontend.view.user.Login;
import main.frontend.view.user.UpdateUserProfile;
import main.frontend.view.user.UserProfile;

public class Client {
    public static void main(String[] args) {
        FrontEnd window = new FrontEnd();
        window.setVisible(true);
        IContent index = new Index();
        IContent login = new Login();
        IContent up = new UserProfile();
        IContent hp = new Home();
        window.switchContentPanel(index); // switch to home page
//        window.switchContentPanel(up); // switch to user profile
//        window.switchContentPanel(hp); // switch to home page
    }
}
