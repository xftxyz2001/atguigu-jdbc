package com.atguigu.cms.dao;

import com.atguigu.cms.javabean.Customer;
import com.atguigu.cms.utils.BaseDao;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author 赵伟风
 * Description: customer对应的数据库方法
 */
public class CustomerDao extends BaseDao {



    /**
     * 查询数据库客户集合
     * @return
     */
    public List<Customer> findAll() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {

        List<Customer> customerList = executeQuery(Customer.class, "select * from t_customer");

        return customerList;
    }

    /**
     * 添加客户的方法
     * @param customer
     */
    public void addCustomer(Customer customer) throws SQLException {

        String sql = "insert into t_customer(name,gender,age,salary,phone) values(?,?,?,?,?);";

        executeUpdate(sql, customer.getName(), customer.getGender(),
                customer.getAge(), customer.getSalary(), customer.getPhone());
    }



    /**
     * 修改对象信息
     * @param cust
     * @return 影响行数
     */
    public  int updateById(Customer cust) throws SQLException {

        String sql = "update t_customer set name=?,gender=?,age=?,salary =? , phone = ? where id = ? ;";

        int rows = executeUpdate(sql, cust.getName(), cust.getGender(), cust.getAge(), cust.getSalary(),
                cust.getPhone(), cust.getId());
        return rows;
    }

    /**
     * 根据id查询客户信息
     * @param id
     * @return
     */
    public Customer findById(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {

        String sql = "select * from t_customer where id = ?;";

        List<Customer> customerList = executeQuery(Customer.class, sql, id);

        if (customerList != null && customerList.size() > 0) {
            return customerList.get(0);
        }

        return null;
    }

    public int removeById(int id) throws SQLException {

        String sql = "delete from t_customer where id =? ;";

        int rows = executeUpdate(sql, id);

        return rows;
    }
}
