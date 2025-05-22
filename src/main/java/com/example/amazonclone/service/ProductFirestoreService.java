package com.example.amazonclone.service;

import com.example.amazonclone.dto.ProductDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductFirestoreService {

    private static final String COLLECTION_NAME = "products";

    public ProductDTO saveProduct(ProductDTO product) {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> future = firestore.collection(COLLECTION_NAME).add(product);
        try {
            future.get(); // дождёмся результата (можно сделать асинхронно)
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сохранении товара", e);
        }
        return product;
    }

    public List<ProductDTO> getAllProducts() {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();
        List<ProductDTO> productList = new ArrayList<>();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (DocumentSnapshot doc : documents) {
                productList.add(doc.toObject(ProductDTO.class));
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении списка товаров", e);
        }
        return productList;
    }
}
