package org.genc.app.SneakoAplication.service.api;

import org.genc.app.SneakoAplication.dto.UsersDTO;

import java.util.List;

public interface UsersService {
    UsersDTO createUser(UsersDTO usersDTO);
    List<UsersDTO> getAllUsers();
    void deleteUserById(Long id);
}
