package org.example.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "person", schema = "structure",
    indexes = {
        @Index(name = "person_email_idx", columnList = "email"),
    }
)
@DynamicUpdate
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Access(AccessType.PROPERTY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "first_name", length = 20)
    @Access(AccessType.PROPERTY)
    @NotEmpty(message = "First name is required")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters long")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "last_name", length = 20)
    @Access(AccessType.PROPERTY)
    @NotEmpty(message = "Last name is required")
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters long")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "email", length = 50)
    @Access(AccessType.PROPERTY)
    @NotEmpty(message = "Email is required")
    @Email(message = "Email must be valid")
    @Getter
    @Setter
    private String email;

    @Column(name = "date_of_birth", columnDefinition = "DATE")
    @Access(AccessType.PROPERTY)
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Getter
    @Setter
    private LocalDate dateOfBirth;

    @Embedded
    @Access(AccessType.PROPERTY)
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street", length = 50)),
            @AttributeOverride(name = "city", column = @Column(name = "city", length = 50)),
            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code"))
    })
    @Getter
    @Setter
    @NotNull(message = "Address is required")
    private Address address;

    @Access(AccessType.PROPERTY)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Getter
    @Setter
    private Role role;

    @Access(AccessType.PROPERTY)
    @Getter
    @Setter
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<BillingDetails> billingDetails = new HashSet<>();

    @Column(name = "creation", columnDefinition = "TIMESTAMP", updatable = false)
    @Access(AccessType.FIELD)
    @CreationTimestamp
    @Getter
    private Instant creation;

    @Version
    @Column(name = "version", columnDefinition = "TIMESTAMP")
    @Access(AccessType.FIELD)
    private Instant version;

    public Person(String firstName, String lastName, String email, LocalDate dateOfBirth, Address address, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.role = role;
    }

    public Person(Long id, String firstName, String lastName, String email, LocalDate dateOfBirth, Address address, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.role = role;
    }

    public void addBillingDetails(BillingDetails billingDetails) {
        this.billingDetails.add(billingDetails);
        billingDetails.setOwner(this);
    }

    public void removeBillingDetails(BillingDetails billingDetails) {
        this.billingDetails.remove(billingDetails);
        billingDetails.setOwner(null);
    }
}