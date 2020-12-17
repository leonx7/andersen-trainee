package com.andersen.shop;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;

public class DataSourceFactory {
    private static final Logger logger = LogManager.getLogger(DataSourceFactory.class);

    private DataSourceFactory() {
    }

    public static DataSource getMySQLDataSource() {
        MysqlDataSource mysqlDS;
        mysqlDS = new MysqlDataSource();
        mysqlDS.setURL("jdbc:mysql://localhost:3306/internet_shop?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        mysqlDS.setUser("root");
        mysqlDS.setPassword("root");
        return mysqlDS;
    }
}
