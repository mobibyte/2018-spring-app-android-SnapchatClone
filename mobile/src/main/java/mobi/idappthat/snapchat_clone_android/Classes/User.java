package mobi.idappthat.snapchat_clone_android.Classes;

import java.util.ArrayList;

/**
 * Created by kolten on 2/20/18.
 */

public class User {

    private String username;
    private String name;
    private String phoneNumber;
    private int uuid;
    private Boolean isLoggedIn;

    public User(String username, String name, int uuid, Boolean isLoggedIn) {
        this.username = username;
        this.name = name;
        this.uuid = uuid;
        this.isLoggedIn = isLoggedIn;
    }

    public User(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    private ArrayList<User> friends;

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }
}
