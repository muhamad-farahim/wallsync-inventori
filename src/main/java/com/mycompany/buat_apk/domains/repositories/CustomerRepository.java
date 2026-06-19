package com.mycompany.buat_apk.domains.repositories;

import java.util.List;

import com.mycompany.buat_apk.domains.entities.customers.CreateCustomer;
import com.mycompany.buat_apk.domains.entities.customers.Customer;
import com.mycompany.buat_apk.domains.entities.customers.CustomerSummary;
import com.mycompany.buat_apk.domains.entities.customers.UpdateCustomer;

public interface CustomerRepository {
    Long createCustomerReturnId(CreateCustomer data);
    List<Customer> getAllCustomer();
    List<CustomerSummary> getAllCustomerSummaries();
    void updateCustomer(UpdateCustomer data);
    void deleteCustomerById(Long id);
    Customer getCustomerById(Long id);
    Customer getCustomerByPhone(String phone);
}
