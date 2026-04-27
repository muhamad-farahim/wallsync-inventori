package com.mycompany.buat_apk.frames;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mycompany.buat_apk.config.AppConfig;

import io.github.cdimascio.dotenv.Dotenv;

public class MainFrame extends JFrame {

    private JPanel mainContent;
    private CardLayout cardLayout;

    private frame_manProduk frameProductList;

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

        this.add(this.mainContent);

        this.frameProductList = fMproduk;

        //CRUD PRODUCT
        this.mainContent.add(fProduk.getContentPane(), "PRODUCT_CREATE");
        this.mainContent.add(fMproduk.getContentPane(), "PRODUCT_LIST");
        

        //AUTH
        this.mainContent.add(fLogin.getContentPane(), "LOGIN");
    }

    public void start() {
        this.setVisible(true);
        this.goTo("LOGIN");
    }
    
    protected void goTo(String name) {
        if(name.equals("PRODUCT_LIST")){
            this.frameProductList.loadTableData();
        }
        this.cardLayout.show(this.mainContent, name);
    }
}
