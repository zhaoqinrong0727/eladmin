package me.zhengjie.modules.zx.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "姓名不能为空")
    @Column(name = "customer_name")
    private String customerName;
    @NotNull(message = "身份证不能为空")
    @Column(name = "id_number")
    private String idNumber;
    @NotNull(message = "电话号码不能为空")
    @Column(name = "customer_phone")
    private String customerPhone;
    @Column(name = "card_no")
    private String cardNo;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Order.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<Order> orderList = new ArrayList<>();

    private Timestamp createTime;

    public Customer() {
    }

    public Customer(@NotNull(message = "姓名不能为空") String customerName,
                    @NotNull(message = "身份证不能为空") String idNumber,
                    @NotNull(message = "电话号码不能为空") String customerPhone, String cardNo) {
        this.customerName = customerName;
        this.idNumber = idNumber;
        this.customerPhone = customerPhone;
        this.cardNo = cardNo;
    }
}
