package com.atguigu.expand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author 赵伟风
 * Description: bank表业务类,添加转账业务
 */
public class BankService {


    /**
     * 转账业务方法
     * @param addAccount  加钱账号
     * @param subAccount  减钱账号
     * @param money  金额
     */
    public void transfer(String addAccount,String subAccount, int money) throws ClassNotFoundException, SQLException {

        System.out.println("addAccount = " + addAccount + ", subAccount = " + subAccount + ", money = " + money);

        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "root");

        int flag = 0;

        //利用try代码块,调用dao
        try {
            //开启事务(关闭事务自动提交)
            connection.setAutoCommit(false);

            BankDao bankDao = new BankDao();
            //调用加钱 和 减钱
            bankDao.addMoney(addAccount,money,connection);
            System.out.println("--------------");
            bankDao.subMoney(subAccount,money,connection);
            flag = 1;
            //不报错,提交事务
            connection.commit();
        }catch (Exception e){

            //报错回滚事务
            connection.rollback();
            throw e;
        }finally {
            connection.close();
        }

        if (flag == 1){
            System.out.println("转账成功!");
        }else{
            System.out.println("转账失败!");
        }
    }

}
