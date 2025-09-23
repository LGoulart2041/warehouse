package edu.dio.warehouse.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.dio.warehouse.entity.StockStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record StockSavedResponse(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("amount")
        Long amount,
        @JsonProperty("boughtPrice")
        BigDecimal boughtPrice,
        @JsonProperty("status")
        StockStatus status,
        @JsonProperty("soldPrice")
        BigDecimal soldPrice,
        @JsonProperty("productId")
        UUID productId,
        @JsonProperty("productName")
        String productName
) {
}
