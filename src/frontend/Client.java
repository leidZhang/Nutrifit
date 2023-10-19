package frontend;

import frontend.view.home.HomePage;
import frontend.view.IContent;
import frontend.view.user.UserProfile;

public class Client {
    public static void main(String[] args) {
        FrontEnd window = new FrontEnd();
        window.setVisible(true);
        IContent up = new UserProfile();
        IContent hp = new HomePage();
        window.switchContentPanel(up);
    }
}
