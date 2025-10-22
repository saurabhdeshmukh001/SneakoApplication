package org.genc.app.SneakoAplication.service.api;

import org.genc.app.SneakoAplication.dto.AuthResponse;
import org.genc.app.SneakoAplication.dto.RegisterRequest;

public interface AuthService {
    AuthResponse authenticate(String username, String password);
    AuthResponse register(RegisterRequest request);
}
