package com.AC.ProdOrderManager.services;

import com.AC.ProdOrderManager.dtos.auth.LoginRequestDTO;
import com.AC.ProdOrderManager.dtos.auth.LoginResponseDTO;
import com.AC.ProdOrderManager.dtos.auth.RegisterUserRequestDTO;
import com.AC.ProdOrderManager.exceptions.InvalidDataException;
import com.AC.ProdOrderManager.exceptions.InvalidField;
import com.AC.ProdOrderManager.exceptions.auth.InvalidPasswordException;
import com.AC.ProdOrderManager.exceptions.auth.UserAlreadyExistsException;
import com.AC.ProdOrderManager.exceptions.user.UserNotFoundException;
import com.AC.ProdOrderManager.models.user.UserModel;
import com.AC.ProdOrderManager.models.user.UserRole;
import com.AC.ProdOrderManager.repositories.UserRepository;
import com.AC.ProdOrderManager.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    public void register(RegisterUserRequestDTO body) throws InvalidDataException, UserAlreadyExistsException {
        validateRegister(body);
        userRepository.save(new UserModel(body.login(), passwordEncoder.encode(body.password()), UserRole.valueOf(body.role())));
    }

    public LoginResponseDTO login(LoginRequestDTO body) throws UserNotFoundException, InvalidPasswordException, InvalidDataException {
        UserModel user = validateLogin(body);
        return new LoginResponseDTO(user.getLogin(), tokenService.generateToken(user), user.getRole().getRoleReport());
    }

    private void validateRegister(RegisterUserRequestDTO body) throws InvalidDataException, UserAlreadyExistsException {
        Optional<UserModel> user = userRepository.findByLogin(body.login());

        List<InvalidField> invalidFields = new ArrayList<>();
        Set<String> validRoles = Arrays.stream(UserRole.values())
                .map(Enum::name)
                .collect(Collectors.toSet());

        if (user.isEmpty()) {
            if (body.login() == null || body.login().isBlank()) {
                invalidFields.add(new InvalidField("login", "campo em branco"));
            }
            if (body.password() == null || body.password().isBlank()) {
                invalidFields.add(new InvalidField("senha", "campo em branco"));
            }
            if (body.role() == null || body.role().isBlank()) {
                invalidFields.add(new InvalidField("cargo", "campo em branco"));
            } else if (!validRoles.contains(body.role())) {
                invalidFields.add(new InvalidField("cargo", "cargo inexistente"));
            }

            if (!invalidFields.isEmpty()) {
                throw new InvalidDataException(invalidFields);
            }
        }
        else {
            throw new UserAlreadyExistsException();
        }
    }

    private UserModel validateLogin(LoginRequestDTO body) throws UserNotFoundException, InvalidPasswordException, InvalidDataException {
        List<InvalidField> invalidFields = new ArrayList<>();

        if (body.login() == null || body.login().isBlank()) {
            invalidFields.add(new InvalidField("login", "campo em branco"));
        }
        if (body.password() == null || body.password().isBlank()) {
            invalidFields.add(new InvalidField("senha", "campo em branco"));
        }

        if (!invalidFields.isEmpty()) {
            throw new InvalidDataException(invalidFields);
        }

        UserModel user = userRepository.findByLogin(body.login()).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new InvalidPasswordException();
        }
        return user;
    }
}
