/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.buat_apk;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author najwa amanda
 */
public class Buat_apk {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.out.println(dotenv.get("DB_URL"));
        System.out.println(dotenv.get("DB_USERNAME"));
        System.out.println(dotenv.get("DB_PASSWORD"));
        System.out.println("Hello World!!");
    }
}
