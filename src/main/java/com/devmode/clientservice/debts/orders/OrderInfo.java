package com.devmode.clientservice.debts.orders;

import java.util.List;

public interface OrderInfo {

    int getPayerUserId();

    List<OrderItem> getOrderItems();

}
