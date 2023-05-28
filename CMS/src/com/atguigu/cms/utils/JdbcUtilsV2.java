package com.atguigu.cms.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author 赵伟风
 * Description:
 *
 * v1.0版本工具类
 *   内部包含一个连接池对象,并且对外提供获取连接和回收连接的方法!
 *
 * 小建议:
 *   工具类的方法,推荐写成静态,外部调用会更加方便!
 *
 * 实现:
 *   属性 连接池对象 [实例化一次]
 *       单例模式
 *       static{
 *           全局调用一次
 *       }
 *   方法
 *       对外提供连接的方法
 *       回收外部传入连接方法
 *
 *
 * TODO:
 *    利用线程本地变量,存储连接信息! 确保一个线程的多个方法可以获取同一个connection!
 *    优势: 事务操作的时候 service 和 dao 属于同一个线程,不同再传递参数了!
 *    大家都可以调用getConnection自动获取的是相同的连接池!
 *
 */
public class JdbcUtilsV2 {


    private static DataSource dataSource = null; //连接池对象

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();


    static{
        //初始化连接池对象
        Properties properties = new Properties();
        InputStream ips = JdbcUtilsV2.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对外提供连接的方法
     * @return
     */
    public static Connection getConnection() throws SQLException {

        //线程本地变量中是否存在
        Connection connection = tl.get();

        //第一次没有
        if (connection == null) {
            //线程本地变量没有,连接池获取
            connection = dataSource.getConnection();
            tl.set(connection);
        }

        return  connection;
    }

    public static void freeConnection() throws SQLException {

        Connection connection = tl.get();
        if (connection != null) {
            tl.remove(); //清空线程本地变量数据
            connection.setAutoCommit(true); //事务状态回顾 false
            connection.close(); //回收到连接池即可
        }

    }

}
