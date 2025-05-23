package com.example.amazonclone.service;

import com.example.amazonclone.dto.OrderDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class OrderFirestoreService implements OrderService {

    private final static String COLLECTION_NAME = "orders";

    @Override
    public OrderDTO createOrder(String userId, OrderDTO orderDTO) {
        Firestore db = FirestoreClient.getFirestore();
        orderDTO.setStatus("PENDING");
        ApiFuture<DocumentReference> future = db.collection(COLLECTION_NAME).add(orderDTO);
        try {
            orderDTO.setId(future.get().getId());
            return orderDTO;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to create order", e);
        }
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(String userId) {
        Firestore db = FirestoreClient.getFirestore();
        List<OrderDTO> orders = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME)
                    .whereEqualTo("userId", userId)
                    .get();
            List<QueryDocumentSnapshot> docs = future.get().getDocuments();
            for (DocumentSnapshot doc : docs) {
                OrderDTO order = doc.toObject(OrderDTO.class);
                order.setId(doc.getId());
                orders.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch orders", e);
        }
        return orders;
    }
}
