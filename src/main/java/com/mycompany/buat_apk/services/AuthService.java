package com.mycompany.buat_apk.services;

import com.mycompany.buat_apk.domains.entities.users.User;
import com.mycompany.buat_apk.domains.repositories.UserRepository;

public class AuthService {
    private UserRepository userRepo;

	public AuthService(UserRepository userRepo) {
		this.userRepo = userRepo;
	} 

    public User login(String username, String password) {

    try {

        User user = this.userRepo.getUserByUsername(username);

        if(user == null) {
            return null;
        }

        if(!user.getPassword().equals(password)) {
            return null;
        }

        return user;

    } catch(Exception e) {

        System.err.println(e.getMessage());
        return null;
    }
}
}
