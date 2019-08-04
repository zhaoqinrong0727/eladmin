package me.zhengjie.modules.zx.repository;

import io.lettuce.core.dynamic.annotation.Param;
import me.zhengjie.modules.zx.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order as o where o.orgId=:orgId and o.lateModify>=:lastModify")
    List<Order> findByOrgIdAndLateModify(@Param("orgId") String orgId, @Param("lastModify")Timestamp lastModify);
}
