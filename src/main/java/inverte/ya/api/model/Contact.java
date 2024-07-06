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
public class Contact
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 80, nullable = false)
    private String fullName;

    @Column(length = 80, nullable = false)
    private String email;

    @Column(length = 200, nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
