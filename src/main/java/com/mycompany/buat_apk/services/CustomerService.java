package com.mycompany.buat_apk.services;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.buat_apk.domains.entities.customers.CreateCustomer;
import com.mycompany.buat_apk.domains.entities.customers.Customer;
import com.mycompany.buat_apk.domains.entities.customers.UpdateCustomer;
import com.mycompany.buat_apk.domains.repositories.CustomerRepository;

public class CustomerService {

    private final CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Long createCustomer(CreateCustomer data) {
        if (data == null || data.getName() == null || data.getName().trim().isEmpty()) {
            System.err.println("Validation failed: Customer name is required.");
            return 0L;
        }
        Long id = this.customerRepo.createCustomerReturnId(data);
        return (id != null) ? id : 0L;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = this.customerRepo.getAllCustomer();
        return (list != null) ? list : new ArrayList<>();
    }

    public boolean updateCustomer(UpdateCustomer data) {
        if (data == null || data.getId() == null || data.getId() <= 0) {
            System.err.println("Validation failed: Invalid customer ID for update.");
            return false;
        }
        this.customerRepo.updateCustomer(data);
        return true;
    }

    public boolean deleteCustomerById(Long id) {
        if (id == null || id <= 0) {
            System.err.println("Validation failed: Invalid customer ID for deletion.");
            return false;
        }
        this.customerRepo.deleteCustomerById(id);
        return true;
    }

    public Customer getCustomerById(Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        return this.customerRepo.getCustomerById(id);
    }

    public Customer getCustomerByPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return null;
        }
        return this.customerRepo.getCustomerByPhone(phone.trim());
    }
}
