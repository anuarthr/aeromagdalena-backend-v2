package com.data.tallermodelodatos.api;

import com.data.tallermodelodatos.dto.ClienteDto;
import com.data.tallermodelodatos.dto.ClienteCreateRequest;
import com.data.tallermodelodatos.dto.LoginRequest;
import com.data.tallermodelodatos.dto.LoginResponse;
import com.data.tallermodelodatos.dto.UserDto;
import com.data.tallermodelodatos.entities.User;
import com.data.tallermodelodatos.security.services.UserDetailsImpl;
import com.data.tallermodelodatos.security.jwt.JwtUtil;
import com.data.tallermodelodatos.services.ClienteService;
import com.data.tallermodelodatos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) throws URISyntaxException {
        if (userService.existsByUsername(userDto.username())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (userService.existsByEmail(userDto.email())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        ClienteCreateRequest clienteRequest = new ClienteCreateRequest(
                userDto.nombre(),
                userDto.apellido(),
                userDto.email(),
                userDto.username(),
                userDto.password(),
                userDto.direccion(),
                userDto.telefono()
        );
        ClienteDto newCliente = clienteService.crearCliente(clienteRequest);

        User user = new User();
        user.setId(newCliente.idCliente());
        user.setUsername(newCliente.username());
        user.setEmail(newCliente.email());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setNombre(newCliente.nombre());
        user.setApellido(newCliente.apellido());
        user.setDireccion(newCliente.direccion());
        user.setTelefono(newCliente.telefono());
        user.setRoles(Set.of("ROLE_USER"));
        userService.saveUser(user);

        UserDto newUser = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles(),
                user.getNombre(),
                user.getApellido(),
                user.getDireccion(),
                user.getTelefono()
        );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCliente.idCliente())
                .toUri();

        return ResponseEntity.created(location).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserDto userDto = userService.findByUsername(userDetails.getUsername()).orElseThrow();

        return ResponseEntity.ok(new LoginResponse(jwt, userDto));
    }
}

