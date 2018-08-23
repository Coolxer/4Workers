package home.models;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
    private List<User> list = new ArrayList<User>();
    private UsersDatabase usersDatabase;

    public UsersList(){
        usersDatabase = new UsersDatabase();
        list = usersDatabase.getUsersList();
    }

    public void addUser(String username, String password, String email){
        usersDatabase.registerUser(username, password, email);
        updateList();
    }

    public int getListSize(){
        return list.size();
    }

    private void updateList(){
        list = usersDatabase.getUsersList();
    }

    public boolean userExists(User user){
        return list.contains(user);
    }
}
