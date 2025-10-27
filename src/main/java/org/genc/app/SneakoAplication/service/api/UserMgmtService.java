package org.genc.app.SneakoAplication.service.api;


import org.genc.app.SneakoAplication.dto.UserRegistrationRequestDTO;
import org.genc.app.SneakoAplication.dto.UserRegistrationResponseDTO;

public interface UserMgmtService {

    public UserRegistrationResponseDTO registerNewUser(UserRegistrationRequestDTO userReqDTO);

    public  boolean isNewUser(String userName);
}
