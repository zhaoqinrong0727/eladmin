package me.zhengjie.modules.zx.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "REPORT_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "report_id")
    private String reportId;
    private Timestamp createTime;
    private Timestamp lateModify;
    private boolean pay;

    public Order(String reportId) {
        this.reportId = reportId;
    }

    @Column(name = "channel_no")
    private String channelNo;
    @Column(name = "org_id")
    private String orgId;

    public Order() {
    }
}
