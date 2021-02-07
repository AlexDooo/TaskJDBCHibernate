package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Bob", "Trenton", (byte) 35);
        userService.removeUserById(2);
        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers().toString());
        userService.dropUsersTable();


    }
}
