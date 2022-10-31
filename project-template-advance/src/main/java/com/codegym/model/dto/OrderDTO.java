package com.codegym.model.dto;

import com.codegym.model.Order;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {
    private int id;
    private String name;
    private String phone;
    private String address;
    private int idStatus;
    private BigDecimal total;

    private List<OrderItemDTO> orderItemDTOList;
    public OrderDTO(){

    }

    public void setOrderToOrderDTO(Order order) {
        this.id = order.getId();
        this.name = order.getName();
        this.phone = order.getPhone();
        this.address = order.getAddress();
        this.idStatus = order.getIdStatus();
        this.total = order.getTotal();
    }
    public void setOrderItemsToOrderDTO(List<OrderItemDTO> orderItemDTOList){
        this.orderItemDTOList = orderItemDTOList;
    }
    public OrderDTO(int id, String name, String phone, String address, int idStatus, BigDecimal total) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.idStatus = idStatus;
        this.total = total;
    }

    public OrderDTO(int id, String name, String phone, String address, int idStatus, BigDecimal total, List<OrderItemDTO> orderItemDTOList) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.idStatus = idStatus;
        this.total = total;
        this.orderItemDTOList = orderItemDTOList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderItemDTO> getOrderItemDTOList() {
        return orderItemDTOList;
    }

    public void setOrderItemList(List<OrderItemDTO> orderItemDTOList) {
        this.orderItemDTOList = orderItemDTOList;
    }
}
