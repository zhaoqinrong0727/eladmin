package me.zhengjie.modules.zx.repository;

import io.lettuce.core.dynamic.annotation.Param;
import me.zhengjie.modules.zx.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select distinct c  from Customer as c inner join c.orderList as ro where ro.lateModify>=:lateModify and ro.orgId=:orgId")
    List<Customer> findByOrgIdAndDate(@Param("orgId") String orgId, @Param("lateModify") Timestamp lateModify);


}
