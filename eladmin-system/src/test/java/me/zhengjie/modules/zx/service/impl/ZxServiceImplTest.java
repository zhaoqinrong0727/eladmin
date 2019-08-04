package me.zhengjie.modules.zx.service.impl;

import me.zhengjie.modules.zx.service.ZxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ZxServiceImplTest {
    @Autowired
    private ZxService zxService;

    @Test
    public void getCustomerCount() {
        Map<String, Object> customerCount = zxService.getOrderCount();
        System.out.println(customerCount);
    }
    @Test
    public void getChartData() {
        Map<String, Object> customerCount = zxService.getChartData();
        System.out.println(customerCount.get("monthLine"));
        System.out.println(customerCount.get("monthPayLine"));
    }
}