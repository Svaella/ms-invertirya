package inverte.ya.api.controller;

import inverte.ya.api.dto.AuthDTO;
import inverte.ya.api.dto.UserDTO;

// import inverte.ya.api.captcha.ReCaptchaResponse; // Importa la clase desde el paquete `captcha` si decidiste usar este paquete
import inverte.ya.api.model.User;
import inverte.ya.api.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;
    private final ModelMapper mapper;

    @PostMapping()
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO user) {
        UserDTO userDTO = this.convertToDTO(service.save(this.convertToEntity(user)));
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO user) {
        UserDTO userDTO = this.convertToDTO(service.update(this.convertToEntity(user), user.getId()));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable Integer id) {
        UserDTO userDTO = this.convertToDTO(service.readById(id));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = service.readAll().stream().map(this::convertToDTO).toList();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid AuthDTO dto)
    {
        UserDTO userDTO = this.convertToDTO(service.login(dto));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    private User convertToEntity(UserDTO dto) {
        return mapper.map(dto, User.class);
    }

    private UserDTO convertToDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }
}
