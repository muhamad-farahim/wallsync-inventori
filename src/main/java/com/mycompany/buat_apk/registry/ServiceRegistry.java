package com.mycompany.buat_apk.registry;

import java.sql.Connection;
import java.sql.SQLException;

import com.mycompany.buat_apk.db.DbConnection;
import com.mycompany.buat_apk.domains.repositories.CategoryRepository;
import com.mycompany.buat_apk.domains.repositories.CustomerRepository;
import com.mycompany.buat_apk.domains.repositories.ProductRepository;
import com.mycompany.buat_apk.domains.repositories.StockRepository;
import com.mycompany.buat_apk.domains.repositories.UserRepository;
import com.mycompany.buat_apk.repo.mysql.CategoryRepo;
import com.mycompany.buat_apk.repo.mysql.CustomerRepo;
import com.mycompany.buat_apk.repo.mysql.ProductRepo;
import com.mycompany.buat_apk.repo.mysql.StockRepo;
import com.mycompany.buat_apk.repo.mysql.UserRepo;
import com.mycompany.buat_apk.services.AuthService;
import com.mycompany.buat_apk.services.CategoryService;
import com.mycompany.buat_apk.services.CustomerService;
import com.mycompany.buat_apk.services.ProductService;
import com.mycompany.buat_apk.services.UserService;
import com.mycompany.buat_apk.services.StockService;


public class ServiceRegistry {

    public static ServiceRegistry instance;

    //Services
    public ProductService productService;
    public CategoryService categoryService;
    public AuthService authService;
    public CustomerService customerService;
    public UserService userService;
    public StockService stockService;

    private ServiceRegistry() {

        Connection conn = null;

        try {
            conn = DbConnection.getConnection();
        }catch(SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            System.exit(1);
        }
        System.out.print("is conn null: ");
        System.out.println(conn);

        ProductRepository productRepository = new ProductRepo(conn);
        StockRepository stockRepository = new StockRepo(conn);
        CategoryRepository categoryRepository = new CategoryRepo(conn);
        UserRepository userRepository = new UserRepo(conn);
        CustomerRepository customerRepository = new CustomerRepo();
        StockRepository stockRepo = new StockRepo(conn);

        this.productService = new ProductService(productRepository, stockRepository);
        this.categoryService = new CategoryService(categoryRepository);
        this.authService = new AuthService(userRepository);
        this.customerService = new CustomerService(customerRepository);
        this.userService = new UserService(userRepository);
        this.stockService = new StockService(stockRepo);

    }

    public static ServiceRegistry getInstance() {
        if (ServiceRegistry.instance == null) {
            ServiceRegistry.instance = new ServiceRegistry();
        }

        return ServiceRegistry.instance;
    }
}
