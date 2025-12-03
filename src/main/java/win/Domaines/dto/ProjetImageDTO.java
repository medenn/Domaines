package win.Domaines.dto;

import lombok.*;
import win.Domaines.entity.ProjetImage;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetImageDTO {

    private Long id;
    private byte[] image;

    public static ProjetImageDTO fromEntity(ProjetImage pi) {
        return ProjetImageDTO.builder()
                .id(pi.getId())
                .image(pi.getImage())
                .build();
    }
}
