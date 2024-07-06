package inverte.ya.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Commentary
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "id_article", nullable = false, foreignKey = @ForeignKey(name = "FK_COMMENTARY_ARTICLE"))
    private Article article;
}

