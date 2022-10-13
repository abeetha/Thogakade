package com.seekerscloud.pos.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_code")
    private String orderId;

    private String data;

    @Column(name = "total_cost")
    private double totalCost;

    @ManyToOne
    @JoinColumn(
            name = "customer_id"
    )
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = {
            CascadeType.ALL
    })
    private List<OrderDetails> details = new ArrayList<>();

    public Order() {
    }

    public Order(String orderId, String data, double totalCost) {
        this.orderId = orderId;
        this.data = data;
        this.totalCost = totalCost;
    }

    public List<OrderDetails> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetails> details) {
        this.details = details;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
