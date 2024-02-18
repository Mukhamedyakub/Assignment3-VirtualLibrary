package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderBook {
    private int id;
    private int order_id;
    private int book_id;
}
