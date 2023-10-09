package frontend;

import frontend.view.layout.HomePage;
import frontend.view.layout.IContent;
import frontend.view.FrontEnd;
import frontend.view.layout.UserProfile;

public class Client {
    public static void main(String[] args) {
        FrontEnd window = new FrontEnd();
        window.setVisible(true);
        IContent up = new UserProfile();
        IContent hp = new HomePage();
        window.switchContentPanel(up);
    }
}
