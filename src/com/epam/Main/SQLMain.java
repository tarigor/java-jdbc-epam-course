package com.epam.Main;

import com.epam.service.Queries;
import com.epam.utility.ConnectionInstance;
import com.epam.utility.PropertiesFile;

import java.sql.SQLException;
import java.util.Properties;

public class SQLMain {

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3307/shop";
    public static final String USER = "root";
    public static final String PASSWORD = "755019";
    public static final String AUTO_RECONNECT = "true";
    public static final String CHARACTER_ENCODING = "utf8";
    public static final String USE_UNICODE = "true";

    public static void main(String[] args) {
        PropertiesFile properties = new PropertiesFile();
        ConnectionInstance connection = new ConnectionInstance();
        Queries queries = new Queries();

        final Properties propertiesFile = properties.PropertiesFile(USER, PASSWORD, AUTO_RECONNECT, CHARACTER_ENCODING, USE_UNICODE);

        try {
            Class.forName(JDBC_DRIVER);
            queries.setConnection(connection.ConnectionCreator(URL, propertiesFile));

            //TASK1
            System.out.println(queries.getDataFromOrderTableFilteredByOrderId(222).toString());
            System.out.println("Query 1 completed");
            //TASK2
            queries.getOrderPriceLimitedAndConsistingCertainUniqueItems(queries, 15000.0, 3);
            System.out.println("Query 2 completed");
            //TASK3
            queries.getOrdersNumbersConsistCertainItem("PANASONIC123");
            System.out.println("Query 3 completed");
            //TASK4
            queries.getOrderNumbersWhichNotConsistOfCertainItemWithinCurrentDay("PANASONIC123");
            System.out.println("Query 4 completed");
            //TASK5
            queries.createNewOrderOfItemsOrderedWithinCurrentDay();
            System.out.println("Query 5 completed");
            //TASK6
            queries.removeAllOrdersWhereExistCertainAmountOfCertainItems("LG1234", 5);
            System.out.println("Query 5 completed");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
