package com.mycompany.buat_apk.registry;

import com.mycompany.buat_apk.domains.entities.users.User;

public class AppContextRegistry {
    
    static private AppContextRegistry instance = null;
    private User activeUser = null;

    public static AppContextRegistry getInstance() {
        if (AppContextRegistry.instance == null) {
            AppContextRegistry.instance = new AppContextRegistry();
        }

        return AppContextRegistry.instance;
    }

    public void setActiveUser(User user) {
        this.activeUser = user;
    }

    public User getActiveUser() {
        return this.activeUser;
    }
}





