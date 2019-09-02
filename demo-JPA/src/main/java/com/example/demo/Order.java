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


enum Currency  {
      DEFAULT(0) { public void showCurrency(){ System.out.println("Current currency id: " + getId());} }
    , DOLLLAR(1) { public void showCurrency(){ System.out.println("Current currency id: " + getId());} }
    , EURO(2)    { public void showCurrency(){ System.out.println("Current currency id: " + getId());} }
    , YEN(3)     { public void showCurrency(){ System.out.println("Current currency id: " + getId());} }
    , YUAN(4)    { public void showCurrency(){ System.out.println("Current currency id: " + getId());} };

    int id;
    private Currency(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

}


@Entity
@Data
@Table(name = "ordermaster", schema = "world")
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Order extends AuditModel {

    // TODO: 2019-08-30 OrderNo = SysDate||Format(ServialNo,00000000001)
    // Custom ID Generator in

    @Id
    @Column(name = "id",insertable = false, updatable = false, nullable = false)
    @GenericGenerator(name = "sequence_orderid", strategy = "com.example.demo.OrderIDGenerator")
    @GeneratedValue(generator = "sequence_orderid")
    private Long id;

    private String emp;
    private String createdate;

    @OneToMany(mappedBy = "id",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) // FetchType.LAZY
    private List<OrderItem> orderItemList;

    @Transient
    private Currency currency;

    @Transient
    private int testAttr;


}