package com.epam.Main;

import com.epam.service.Queries;
import com.epam.utility.ConnectionInstance;
import com.epam.utility.PropertiesFile;

import java.util.Properties;

public class SQLMain {

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3307/my_db";
    public static final String USER = "root";
    public static final String PASSWORD = "755019";
    public static final String AUTO_RECONNECT = "true";
    public static final String CHARACTER_ENCODING = "utf8";
    public static final String USE_UNICODE = "true";

    public static void main(String[] args) {
        PropertiesFile properties = new PropertiesFile();
        ConnectionInstance connection = new ConnectionInstance();
        Queries queryInstance = new Queries();
        final Properties propertiesFile = properties.PropertiesFile(USER, PASSWORD, AUTO_RECONNECT, CHARACTER_ENCODING, USE_UNICODE);

        try {
            Class.forName(JDBC_DRIVER);
            queryInstance.setConnection(connection.ConnectionCreator(URL, propertiesFile));
            //1
            queryInstance.getOrderInformation(444);
            System.out.println("Query 1 completed");
            //2
            queryInstance.getOrderPriceLimitedAndConsistingCertainUniqueItems(queryInstance,503.0, 3);
            System.out.println("Query 2 completed");
            //3
            queryInstance.getOrdersNumbersConsistCertainItem("PANASONIC123");
            System.out.println("Query 3 completed");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
