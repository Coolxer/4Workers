package home.models;

import javax.imageio.stream.ImageInputStream;
import javax.swing.text.html.ImageView;

public class User {
    private String username;
    private String password;
    //private String email;
    //private ImageView image;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    */
    /*
    public ImageView getImage() { return image; }

    public void setImage(ImageView image) { this.image = image; }
    */
}
