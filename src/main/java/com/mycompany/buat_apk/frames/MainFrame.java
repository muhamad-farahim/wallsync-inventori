package com.mycompany.buat_apk.frames;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mycompany.buat_apk.config.AppConfig;

import io.github.cdimascio.dotenv.Dotenv;

public class MainFrame extends JFrame {

    private JPanel mainContent;
    private CardLayout cardLayout;
    private frame_produk frameProductCreate;
    private frame_manProduk frameProductList;
    private frame_detail frameDetailProduct;
    private frame_editProduk frameEditProduk;
    private frame_sales frameSales; 
    private frame_purchases framePurchases; 
    private frame_customerDetail frameCustomerDetail;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        this.cardLayout = new CardLayout();
        this.mainContent = new JPanel(this.cardLayout);

        Dotenv dotenv = Dotenv.load();
        AppConfig.setEnvironmentVariable(dotenv);

        frame_produk fProduk = new frame_produk(this);
        frame_manProduk fMproduk = new frame_manProduk(this);
        frame_login fLogin = new frame_login(this);
        frame_detail frDetailProduct = new frame_detail(this);
        frame_editProduk fEditProduk = new frame_editProduk(this);
        frame_sales fSales = new frame_sales(this);
        frame_purchases fPurchases = new frame_purchases(this);
        frame_customer fCustomer = new frame_customer(this);
        frame_customerDetail fCustomerDetail = new frame_customerDetail(this);

        this.add(this.mainContent);
        this.frameProductCreate = fProduk;
        this.frameProductList = fMproduk;
        this.frameDetailProduct = frDetailProduct;
        this.frameEditProduk = fEditProduk;
        this.frameSales = fSales;
        this.framePurchases = fPurchases;
        this.frameCustomerDetail = fCustomerDetail;

        //CRUD PRODUCT
        this.mainContent.add(fProduk.getContentPane(), "PRODUCT_CREATE");
        this.mainContent.add(fMproduk.getContentPane(), "PRODUCT_LIST");
        this.mainContent.add(frDetailProduct.getContentPane(), "PRODUCT_DETAIL");
        this.mainContent.add(fEditProduk.getContentPane(), "PRODUCT_EDIT");
        this.mainContent.add(fSales.getContentPane(), "PRODUCT_SALES");
        this.mainContent.add(fPurchases.getContentPane(), "PRODUCT_PURCHASES");
        this.mainContent.add(fCustomer.getContentPane(), "CUSTOMER_CREATE");
        this.mainContent.add(frameCustomerDetail.getContentPane(), "CUSTOMER_DETAIL");

        //AUTH
        this.mainContent.add(fLogin.getContentPane(), "LOGIN");
    }

    public void start() {
        this.setVisible(true);
        this.goTo("LOGIN");
    }
    
    public void goTo(String name) {
        if(name.equals("PRODUCT_CREATE")){
        this.frameProductCreate.loadCategories();
        }

        
        if(name.equals("PRODUCT_LIST")){
            this.frameProductList.loadTableData();
        }

        if(name.equals("USER_LIST") || 
                name.equals("CATEGORY_LIST") || 
                name.equals("CUSTOMER_LIST") || 
                name.equals("TRANSAKSI_LIST") ||
                name.equals("PRINT")
        ){
            this.cardLayout.show(this.mainContent, "PRODUCT_LIST");
            this.frameProductList.goToInner(name);
            return;
        }

        if(name.equals("TRANSACTION_LIST")){

        }

        this.cardLayout.show(this.mainContent, name);
    }
    
    public void goTo(String name, Long id) {
        if(name.equals("PRODUCT_DETAIL")){
            this.frameDetailProduct.loadData(id);;
        }
        
        if(name.equals("PRODUCT_EDIT")) {
            this.frameEditProduk.loadFormData(id);
        }
        
        if(name.equals("PRODUCT_SALES")) {
            this.frameSales.loadData(id);
        }

        if(name.equals("CUSTOMER_DETAIL")){
            this.frameCustomerDetail.loadFormData(id);
        }
        
        if(name.equals("PRODUCT_PURCHASES")){
            this.framePurchases.loadData(id);
        }

        this.cardLayout.show(this.mainContent, name);
    }
}
