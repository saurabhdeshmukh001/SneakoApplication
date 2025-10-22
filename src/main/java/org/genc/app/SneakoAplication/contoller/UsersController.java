package org.genc.app.SneakoAplication.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.genc.app.SneakoAplication.dto.UsersDTO;
import org.genc.app.SneakoAplication.service.api.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<UsersDTO> createUser( @Valid  @RequestBody  UsersDTO usersDTO){
        UsersDTO usersResponse=usersService.createUser(usersDTO);
        return new ResponseEntity<>(usersResponse, HttpStatus.OK);

    }

}
