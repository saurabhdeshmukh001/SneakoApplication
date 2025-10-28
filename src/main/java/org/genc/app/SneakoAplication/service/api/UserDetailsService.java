package org.genc.app.SneakoAplication.service.api;

import org.genc.app.SneakoAplication.dto.UserDetailsDTO;

import java.util.List;

public interface UserDetailsService {
    List<UserDetailsDTO> getAllUsers();
    void deleteUserById(Long id);
}
