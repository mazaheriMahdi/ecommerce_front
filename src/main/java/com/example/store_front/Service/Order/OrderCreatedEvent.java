package com.example.store_front.Service.Order;

import java.io.IOException;

public interface OrderCreatedEvent {
    void onOrderCreated() throws IOException, InterruptedException;
}
