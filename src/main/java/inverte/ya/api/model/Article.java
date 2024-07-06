package inverte.ya.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String type;

    @Column(columnDefinition = "TEXT", length = 5000)
    private String content;
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT", length = 5000)
    private String imgUrl;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    List<Commentary> commentaries;
}

