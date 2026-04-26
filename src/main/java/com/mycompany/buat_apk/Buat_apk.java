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
import com.mycompany.buat_apk.frames.MainFrame;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author najwa amanda
 */
public class Buat_apk {

    public static void main(String[] args) {
        // Set environment variables
        
        MainFrame mf = new MainFrame();
        mf.start();
    }

}
