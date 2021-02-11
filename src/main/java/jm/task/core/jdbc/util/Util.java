package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/new_schema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    Connection connection;
    Configuration configuration;

    public Util() {
        try { // Подключение jdbc
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected");

        } catch (SQLException throwables) {
            System.out.println("Exception connected SQL ");
        }

        try { // подключение Hibernate
            Properties properties = new Properties();
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.setProperty(Environment.HBM2DDL_AUTO, "update");
            properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.setProperty(Environment.USER, USERNAME);
            properties.setProperty(Environment.PASS, PASSWORD);
            properties.setProperty(Environment.URL, URL);

            configuration = new Configuration();
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);

            System.out.println("Подключение хибернейт  прошло успешно");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться и создать базу (Хибернейт)");
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Connection getConnection() {
        return connection;
    }

}

