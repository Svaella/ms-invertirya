package inverte.ya.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthDTO
{
    @Email(message = "El email debe ser válido.")
    private String email;

    @NotEmpty
    @NotNull
    @Length(min = 8, max = 60, message = "La contraseña debe tener entre 8 y 60 caracteres.")
    private String password;

}

