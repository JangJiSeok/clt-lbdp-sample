package com.example.demo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//@SequeceGenerator(name = "TEAM_SEQ_GENERATOR", sequenceName = "TEAM_SEQ", initialValue = 1, allocationSize = 1)

@Entity
@Data
@Table(name = "ordermaster", schema = "world")
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Order {

    // TODO: 2019-08-30 OrderNo = SysDate||Format(ServialNo,00000000001)
    // Custom ID Generator in

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "sequence_orderid", strategy = "com.example.demo.OrderIDGenerator")
    @GeneratedValue(generator = "sequence_orderid")
    private Long id;

    private String emp;
    private String createdate;

    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "id") // FetchType.LAZY
    private List<OrderItem> orderItemList;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }



}