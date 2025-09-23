package edu.dio.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static edu.dio.warehouse.entity.StockStatus.UNAVAILABLE;
import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@ToString
public class StockEntity {

    @Id
    private UUID id;

    private Long amount;

    private BigDecimal boughtPrice;

    @Enumerated(STRING)
    private StockStatus status;

    private BigDecimal soldPrice;

    public void decAmount(){
        this.amount -= 1;
        if(this.amount ==0){
            this.status = UNAVAILABLE;
        }
    }

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    public boolean isUnavailable(){
        return status == UNAVAILABLE;
    }

    @PrePersist
    private void prePersist(){
        this.id = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StockEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(boughtPrice, that.boughtPrice) && status == that.status && Objects.equals(soldPrice, that.soldPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, boughtPrice, status, soldPrice);
    }
}
