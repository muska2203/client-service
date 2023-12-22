package com.devmode.clientservice.debts.orders;

import java.util.List;

public interface OrderInfo {

    String getPayerUserId();

    List<OrderItem> getOrderItems();

}
