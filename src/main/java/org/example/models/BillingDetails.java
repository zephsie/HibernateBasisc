package org.example.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetails {
    @Id
    @GeneratedValue
    @Access(AccessType.PROPERTY)
    @Getter
    @Setter
    private Long id;

    @NotNull(message = "Owner is required")
    @Access(AccessType.PROPERTY)
    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "creation", columnDefinition = "TIMESTAMP", updatable = false)
    @Access(AccessType.FIELD)
    @CreationTimestamp
    @Getter
    private Instant creation;

    @Version
    @Column(name = "version", columnDefinition = "TIMESTAMP")
    @Access(AccessType.FIELD)
    private Instant version;

    public BillingDetails(Person owner) {
        this.owner = owner;
    }

    public BillingDetails(Long id, Person owner) {
        this.id = id;
        this.owner = owner;
    }
}