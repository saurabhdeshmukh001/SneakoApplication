package org.genc.app.SneakoAplication.contoller;


import lombok.RequiredArgsConstructor;
import org.genc.app.SneakoAplication.dto.RolesDTO;
import org.genc.app.SneakoAplication.service.api.RolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RolesController {
    private final RolesService rolesService;
    @PostMapping
    public ResponseEntity<RolesDTO> createRole(@RequestBody RolesDTO rolesDTO)
    {
        RolesDTO responseDTO=rolesService.createRole(rolesDTO);
        return new  ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

}
