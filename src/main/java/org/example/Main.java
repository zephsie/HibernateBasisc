package org.example;

import org.example.dao.IDao;
import org.example.dao.PersonDaoSingleton;
import org.example.models.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        IDao<Person> personDao = PersonDaoSingleton.getInstance();

        Address address = new Address("Street", "City", 12345);

        Person person = new Person("John", "Doe", "email@email.com", LocalDate.of(2000, 1, 1), address, Role.USER);

        BillingDetails billingDetails1 = new CreditCard(person, "1234567890123456");
        BillingDetails billingDetails2 = new CreditCard(person, "1111111111111111");

        person.addBillingDetails(billingDetails1);
        person.addBillingDetails(billingDetails2);

        BillingDetails billingDetails3 = new BankAccount(person, "Bank of America", "12345678901");
        BillingDetails billingDetails4 = new BankAccount(person, "Bank of America", "11111111111");

        person.addBillingDetails(billingDetails3);
        person.addBillingDetails(billingDetails4);

        personDao.create(person);

        personDao.read(person.getId()).ifPresent(System.out::println);

        person.setFirstName("Jane");
        person.removeBillingDetails(billingDetails1);

        person.getBillingDetails().forEach(billingDetails -> {
            if (billingDetails instanceof CreditCard) {
                ((CreditCard) billingDetails).setCardNumber("2222222222222222");
            } else if (billingDetails instanceof BankAccount) {
                ((BankAccount) billingDetails).setSwift("22222222222");
            }
        });

        personDao.update(person);

        personDao.read(person.getId()).ifPresent(System.out::println);

        personDao.read().forEach(System.out::println);

        personDao.delete(person);
    }
}