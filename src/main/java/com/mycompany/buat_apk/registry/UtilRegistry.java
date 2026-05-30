package com.mycompany.buat_apk.registry;

import java.io.File;

public class UtilRegistry {

    public static String generateImageName(File file) {
        String originalName = file.getName();
        String extension = "";
        int dotIndex = originalName.lastIndexOf('.');
        if (dotIndex >= 0) {
            extension = originalName.substring(dotIndex);
        }

        return java.util.UUID.randomUUID().toString() + extension;
    } 
}
