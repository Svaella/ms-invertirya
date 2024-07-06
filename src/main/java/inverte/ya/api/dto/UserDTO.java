package inverte.ya.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO
{
    private Integer id;

    @Size(min = 3, max = 60, message = "{username.size}")
    private String username;
    private String password;

    private boolean enabled;

    private List<ArticleDTO> article;
}

