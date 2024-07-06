package inverte.ya.api.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentaryDTO
{
    private Integer id;

    @NotNull
    @NotEmpty
    private String fullName;

    private String content;

    private LocalDateTime createdAt;

    @JsonBackReference
    private ArticleDTO article;
}

