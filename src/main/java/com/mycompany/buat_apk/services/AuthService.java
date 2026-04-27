package com.mycompany.buat_apk.services;

import com.mycompany.buat_apk.domains.entities.users.User;
import com.mycompany.buat_apk.domains.repositories.UserRepository;

public class AuthService {
    private UserRepository userRepo;

	public AuthService(UserRepository userRepo) {
		this.userRepo = userRepo;
	} 

    public User login(String username, String password) {
        User user = null;

        try{
            user = this.userRepo.getUserByUsername(username);
        }catch( Exception e) {
            System.err.println("Error when getting user by username");
            System.err.println(e.getMessage());
            return null;
        }

        System.out.println(user.getPassword());
        System.out.println(password);

        return user;
    }
}
