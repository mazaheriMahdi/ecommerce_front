package com.example.store_front.Service.Order;

import java.io.IOException;

public interface CreditNotEnoughEvent {
    void OnCreditNotEnoughReceived() throws IOException, InterruptedException;
}

