package com.goktech.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * {@code @Description:} 数据库连接池工具类
 */
public class DpUtils {
    /**
     * 创建 Druid 数据库连接池
     */
    private static DataSource source = null;
    
    static {
        try {
            // 使用当前的类获取 classLoader
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            source = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnectionByDruid() throws Exception {
        Connection connection = source.getConnection();
        return connection;
    }
    
    
    /**
     * 使用 DBUtils 工具类关闭资源
     *
     * @param resultSet
     * @param preparedStatement
     * @param connection
     */
    public static void closeResourceByDBUtils(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        DbUtils.closeQuietly(resultSet);
        DbUtils.closeQuietly(preparedStatement);
        DbUtils.closeQuietly(connection);
    }
}