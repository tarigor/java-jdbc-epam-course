package com.epam.Main;

import com.epam.service.QueryInstance;
import com.epam.utility.ConnectionInstance;
import com.epam.utility.PropertiesFile;

import java.sql.PreparedStatement;

public class SQLMain {

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3307/my_db";
    public static final String USER = "root";
    public static final String PASSWORD = "755019";
    public static final String AUTO_RECONNECT = "true";
    public static final String CHARACTER_ENCODING = "utf8";
    public static final String USE_UNICODE = "true";

    public static void main(String[] args) {
        PreparedStatement statement;
        PropertiesFile properties = new PropertiesFile();
        ConnectionInstance connection = new ConnectionInstance();
        QueryInstance queryInstance = new QueryInstance();
        try {
            Class.forName(JDBC_DRIVER);
            queryInstance.getOrderInformation(connection
                    .ConnectionCreator(URL, properties.PropertiesFile(USER, PASSWORD, AUTO_RECONNECT, CHARACTER_ENCODING, USE_UNICODE)), 1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
