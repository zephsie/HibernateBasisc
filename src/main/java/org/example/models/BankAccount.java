package org.example.models;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "bank_account")
@DynamicUpdate
public class BankAccount extends BillingDetails {
    @Column(name = "bank_name", length = 50)
    @Access(AccessType.PROPERTY)
    @NotEmpty(message = "Bank name is required")
    @Size(min = 2, max = 50, message = "Bank name must be between 2 and 50 characters long")
    @Getter
    @Setter
    private String bankName;

    @Column(name = "swift", length = 11)
    @Access(AccessType.PROPERTY)
    @NotEmpty(message = "Swift is required")
    @Size(min = 11, max = 11, message = "Swift must be 11 characters long")
    @Getter
    @Setter
    private String swift;

    public BankAccount(Person owner, String bankName, String swift) {
        super(owner);
        this.bankName = bankName;
        this.swift = swift;
    }

    public BankAccount(Long id, Person owner, String bankName, String swift) {
        super(id, owner);
        this.bankName = bankName;
        this.swift = swift;
    }
}