package org.genc.app.SneakoAplication.service.api;

import org.genc.app.SneakoAplication.dto.UserDetailsDTO;

import java.util.List;

public interface UserDetailsService {
   public List<UserDetailsDTO> getAllUsers();
    public void deleteUserById(Long id);
    public  UserDetailsDTO findById(Long id);
    public  Long TotalUsers();


}
