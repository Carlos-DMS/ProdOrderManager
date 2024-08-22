package com.AC.ProdOrderManager.services;

import com.AC.ProdOrderManager.dtos.auth.LoginRequestDTO;
import com.AC.ProdOrderManager.dtos.auth.LoginResponseDTO;
import com.AC.ProdOrderManager.dtos.auth.RegisterRequestDTO;
import com.AC.ProdOrderManager.models.user.UserModel;
import com.AC.ProdOrderManager.models.user.UserRole;
import com.AC.ProdOrderManager.repositories.UserRepository;
import com.AC.ProdOrderManager.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public boolean register(RegisterRequestDTO body) {
        Optional<UserModel> user = userRepository.findByLogin(body.login());

        if (user.isEmpty()) {
            userRepository.save(new UserModel(body.login(), passwordEncoder.encode(body.password()), UserRole.valueOf(body.role())));
            return true;
        }
        return false;
    }

    public LoginResponseDTO login(LoginRequestDTO body) throws RuntimeException {
        UserModel user = userRepository.findByLogin(body.login()).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return new LoginResponseDTO(user.getLogin(), token);
        }
        throw new RuntimeException("Senha incorreta");
    }

}
