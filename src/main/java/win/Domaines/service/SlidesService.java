package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.dto.SlidesDTO;
import win.Domaines.dto.SlideButtonDTO;
import win.Domaines.entity.Slides;
import win.Domaines.entity.SlideButton;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.SlidesRepository;
import win.Domaines.repository.SlideButtonRepository;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SlidesService {

    private final SlidesRepository slidesRepository;
    private final SlideButtonRepository slideButtonRepository;

    public SlidesService(SlidesRepository slidesRepository, SlideButtonRepository slideButtonRepository) {
        this.slidesRepository = slidesRepository;
        this.slideButtonRepository = slideButtonRepository;
    }

    public Slides create(SlidesDTO dto) {
        Slides s = new Slides();
        s.setTitreArabe(dto.getTitrearabe());
        s.setTitreFrancais(dto.getTitrefrancais());
        s.setTextArabe(dto.getTextarabe());
        s.setTextFrancais(dto.getTextfrancais());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            byte[] imageBytes = Base64.getDecoder().decode(dto.getImage());
            s.setImage(imageBytes);
        }

        // Mapping des boutons
        if (dto.getButtons() != null) {
            List<SlideButton> buttons = dto.getButtons().stream()
                    .map(b -> {
                        SlideButton button = new SlideButton();
                        button.setTitreArabe(b.getTitrearabe());
                        button.setTitreFrancais(b.getTitrefrancais());
                        button.setLien(b.getLien());
                        button.setSlide(s);
                        return button;
                    })
                    .collect(Collectors.toList());
            s.setButtons(buttons);
        }

        return slidesRepository.save(s);
    }

    public Slides update(Long id, SlidesDTO dto) {
        Slides s = slidesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Slide not found with id " + id));

        s.setTitreArabe(dto.getTitrearabe());
        s.setTitreFrancais(dto.getTitrefrancais());
        s.setTextArabe(dto.getTextarabe());
        s.setTextFrancais(dto.getTextfrancais());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            byte[] imageBytes = Base64.getDecoder().decode(dto.getImage());
            s.setImage(imageBytes);
        }

        // Mise à jour des boutons
        s.getButtons().clear();
        if (dto.getButtons() != null) {
            List<SlideButton> buttons = dto.getButtons().stream()
                    .map(b -> {
                        SlideButton button = new SlideButton();
                        button.setTitreArabe(b.getTitrearabe());
                        button.setTitreFrancais(b.getTitrefrancais());
                        button.setLien(b.getLien());
                        button.setSlide(s);
                        return button;
                    })
                    .collect(Collectors.toList());
            s.getButtons().addAll(buttons);
        }

        return slidesRepository.save(s);
    }

    public void deleteById(Long id) {
        slidesRepository.deleteById(id);
    }

    public Slides getById(Long id) {
        return slidesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Slide not found with id " + id));
    }

    public List<Slides> getAll() {
        return slidesRepository.findAll();
    }
}
