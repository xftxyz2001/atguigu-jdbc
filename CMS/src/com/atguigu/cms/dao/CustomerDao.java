package com.atguigu.cms.dao;

import com.atguigu.cms.javabean.Customer;
import com.atguigu.cms.utils.BaseDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 赵伟风
 * Description: 客户进行数据库操作的类
 */
public class CustomerDao extends BaseDao {


    public List<Customer> queryList() throws Exception {
        ArrayList<Customer> list = query(Customer.class, "select * from t_customer");
        return list;
    }

    public void insertCustomer(Customer customer) throws SQLException {
       int rows = update("insert into t_customer(name,gender,age,salary,phone) values (?,?,?,?,?)",
                customer.getName(), customer.getGender(),customer.getAge(),customer.getSalary(),customer.getPhone());
    }

    public Customer queryById(int id) throws Exception {
        Customer customer = queryBean(Customer.class, "select * from t_customer where id = ?", id);
        return customer;
    }

    public int deleteCustomer(int id) throws SQLException {
        return update("delete from t_customer where id =?", id);
    }

    public int updateCustomer(Customer cust) throws SQLException {
        return update("update t_customer set name = ? , gender = ? , age = ? ," +
                "salary = ? , phone = ? where id = ? ;", cust.getName(), cust.getGender(),
                cust.getAge(), cust.getSalary(), cust.getPhone(), cust.getId());
    }
}
