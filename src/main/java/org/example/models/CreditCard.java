package org.example.models;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "credit_card")
@DynamicUpdate
public class CreditCard extends BillingDetails {

    @Column(name = "card_number", length = 16)
    @Access(AccessType.PROPERTY)
    @NotEmpty(message = "Card number is required")
    @Size(min = 16, max = 16, message = "Card number must be 16 characters long")
    @Getter
    @Setter
    private String cardNumber;

    public CreditCard(Person owner, String cardNumber) {
        super(owner);
        this.cardNumber = cardNumber;
    }

    public CreditCard(Long id, Person owner, String cardNumber) {
        super(id, owner);
        this.cardNumber = cardNumber;
    }
}