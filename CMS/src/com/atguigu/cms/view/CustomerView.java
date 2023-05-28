package com.atguigu.cms.view;

import com.atguigu.cms.javabean.Customer;
import com.atguigu.cms.service.CustomerService;

import java.util.List;

/**
 * 这是主控模块, 负责菜单显示和用户交互. 也称为UI, 内部要频繁到管理器对象, 所以使用对象关联
 */
public class CustomerView {

    /**
     * 关联到的管理器对象
     */
    private CustomerService customerService = new CustomerService();

    /**
     * 进入主菜单, 是项目的真正入口, 不可以轻易结束
     */
    public void enterMainMenu() {
        // 1) 声明布尔
        boolean loopFlag = true;
        // 2) 写循环
        do {
            System.out.println("\n--------------------------------客户信息管理--------------------------------------\n");
            listAllCustomers();
            System.out.print("1 添加客户 2 修改客户 3 删除客户 4 客户列表 5 退   出  请选择(1 - 5) : ");
            // 读取用户选择
            char choice = KeyboardUtility.readMenuSelection();
            switch (choice) {
                case '1' : addNewCustomer(); break;
                case '2' : modifyCustomer(); break;
                case '3' : deleteCustomer(); break;
                case '4' : listAllCustomers(); break;
                case '5' :
                    System.out.print("确认是否退出(Y/N) : ");
                    // 获取用户输入的确认
                    char confirm = KeyboardUtility.readConfirmSelection();
                    if (confirm == 'Y') {
                        loopFlag = false;
                    }
                    break;
            }
        } while (loopFlag);
    }

    /**
     * 添加新员工
     */
    private void addNewCustomer() {
        Customer customer = new Customer();
        System.out.println("---------------------添加客户---------------------");
        System.out.print("姓名 : ");
        String name = KeyboardUtility.readString(10);
        customer.setName(name);
        System.out.print("性别 : ");
        String gender = KeyboardUtility.readString(1);
        customer.setGender(gender);
        System.out.print("年龄 : ");
        int age = KeyboardUtility.readInt();
        customer.setAge(age);
        System.out.print("工资 : ");
        int salary = KeyboardUtility.readInt();
        customer.setSalary(salary);
        System.out.print("电话 : ");
        String phone = KeyboardUtility.readString(15);
        customer.setPhone(phone);
        // 通过调用管理器对象完成 员工添加
        customerService.addCustomer(customer);
        System.out.println("---------------------添加完成---------------------");
    }

    /**
     * 修改员工
     */
    private void modifyCustomer () {
        System.out.println("---------------------修改客户---------------------");
        System.out.print("请选择待修改客户ID(-1退出) : ");
        // 获取用户输入的id
        int id = KeyboardUtility.readInt();
        if (id == -1) {
            return;
        }
        // 根据编号定位要修改的目标对象
        Customer target = customerService.getCustomer(id);
        if (target == null) {
            System.out.println("--------------指定ID[" + id + "]的客户不存在-----------------");
            return;
        }
        System.out.println("<直接回车表示不修改>");
        System.out.print("姓名(" + target.getName() + ") : ");
        String name = KeyboardUtility.readString(10, target.getName());
        target.setName(name);
        System.out.print("年龄(" + target.getAge() + ") : ");
        int age = KeyboardUtility.readInt(target.getAge());
        target.setAge(age);
        System.out.print("工资(" + target.getSalary() + ") : ");
        int salary = KeyboardUtility.readInt((int) target.getSalary());
        target.setSalary(salary);
        System.out.print("电话(" + target.getPhone() + ") : ");
        String phone = KeyboardUtility.readString(15, target.getPhone());
        target.setPhone(phone);

        customerService.modifyCustomer(id, target);

        System.out.println("---------------------修改完成---------------------");
    }

    /**
     * 删除员工
     */
    private void deleteCustomer () {
        System.out.println("---------------------删除客户---------------------");
        System.out.print("请选择待删除客户ID(-1退出) : ");
        // 获取用户输入的ID
        int id = KeyboardUtility.readInt();
        if (id == -1) {
            return;
        }
        System.out.print("确认是否删除(Y/N) : ");
        // 获取用户输入的确认
        char confirm = KeyboardUtility.readConfirmSelection();
        if (confirm == 'Y') {
            boolean flag = customerService.removeCustomer(id);
            if (flag) {
                System.out.println("---------------------删除完成---------------------");
            } else {
                System.out.println("--------------指定ID[" + id + "]的客户不存在-----------------");
            }
        }
    }

    /**
     * 员工列表
     */
    private void listAllCustomers() {
        System.out.println("---------------------------------客户列表--------------------------------------");
        // 真的获取所有员工
        List<Customer> list = customerService.getList();
        if (list == null || list.size() == 0) {
            System.out.println("没有数据, 请添加新数据...");
        } else {
            System.out.println("ID\t姓名\t\t性别\t\t年龄\t\t工资\t\t\t电话");
            for (Customer customer : list) {
                System.out.println(customer);
            }
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

}
