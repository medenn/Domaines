package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.Messages;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessagesDTO {
    private Long id;
    private String nom;
    private String email;
    private String objet;
    private String message;

    public static MessagesDTO fromEntity(Messages m) {
        return MessagesDTO.builder()
                .id(m.getId())
                .nom(m.getNom())
                .email(m.getEmail())
                .objet(m.getObjet())
                .message(m.getMessage())
                .build();
    }
}
