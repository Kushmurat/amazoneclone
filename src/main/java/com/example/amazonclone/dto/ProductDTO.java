package com.example.amazonclone.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    @Schema(description = "Название товара", example = "iPhone 15 Pro")
    @NotBlank(message = "Название не может быть пустым")
    private String name;

    @Schema(description = "Описание товара", example = "Смартфон с 256GB памяти")
    @Size(max = 1000, message = "Описание не должно превышать 1000 символов")
    private String description;

    @Schema(description = "Цена", example = "399000.00")
    @NotNull(message = "Цена обязательна")
    @DecimalMin(value = "0.01", message = "Цена должна быть положительной")
    private BigDecimal price;

    @Schema(description = "Категория", example = "Смартфоны")
    private String category;

    @Schema(description = "URL изображения товара", example = "https://firebasestorage.googleapis.com/...")
    private String imageUrl;
}
