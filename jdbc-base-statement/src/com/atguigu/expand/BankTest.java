package com.atguigu.expand;

import org.junit.Test;

/**
 * @Author 赵伟风
 * Description: 测试类
 */
public class BankTest {

    @Test
    public void testBank() throws Exception {
        BankService bankService = new BankService();
        bankService.transfer("ergouzi", "lvdandan",
                500);
    }

}
