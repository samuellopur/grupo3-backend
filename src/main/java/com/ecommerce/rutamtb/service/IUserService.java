package com.ecommerce.rutamtb.service;

import com.ecommerce.rutamtb.model.User;
import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user);
    void deleteUser(User user);
}
