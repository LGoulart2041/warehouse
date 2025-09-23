package edu.dio.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.dio.warehouse.entity.StockStatus;

import java.util.UUID;

public record StockStatusMessage(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("status")
        StockStatus status
) {
}
