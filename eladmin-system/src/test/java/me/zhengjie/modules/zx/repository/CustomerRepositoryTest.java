package me.zhengjie.modules.zx.repository;

import me.zhengjie.modules.zx.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findByOrgId() {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(-10L);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        java.util.Date from = Date.from(instant);
        List<Customer> wz001 = customerRepository.findByOrgIdAndDate("xinyongxiongmao", new Timestamp(from.getTime()));
        System.out.println(wz001);
    }
}