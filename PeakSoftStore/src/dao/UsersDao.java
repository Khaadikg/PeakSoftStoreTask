package dao;

import model.Users;
import java.util.ArrayList;

public class UsersDao {

    private static ArrayList<Users> usersDB = new ArrayList<>();

    public static void addUserToDB(Users user) {
        UsersDao.usersDB.add(user);
    }

    public static boolean checkUser(String log, String pas) {
        for (Users users : UsersDao.usersDB) {
            if (users.getLogin().equals(log) && users.getPassword().equals(pas)) {
                return true;
            }
        }
        return false;
    }
}

