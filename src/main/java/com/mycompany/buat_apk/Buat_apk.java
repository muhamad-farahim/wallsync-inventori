/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.buat_apk;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.buat_apk.config.AppConfig;
import com.mycompany.buat_apk.db.DbConnection;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author najwa amanda
 */
public class Buat_apk {

    public static void main(String[] args) {
        // Set environment variables
        Dotenv dotenv = Dotenv.load();
        AppConfig.setEnvironmentVariable(dotenv);

        //Try the db connections
        try(Connection conn = DbConnection.getConnection()) {
            String query = "SELECT id, nama_produk, harga FROM produk;";
            
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {
                System.out.print("Id: ");
                System.out.println(res.getInt("id"));
                System.out.println("Nama produk: " + res.getString("nama_produk"));
                System.out.println("Harga: " + res.getInt("harga"));
            }

        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return;
        }
        
    }

}
