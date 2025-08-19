package win.Domaines.service;

import org.springframework.stereotype.Service;
import win.Domaines.dto.MessagesDTO;
import win.Domaines.entity.Messages;
import win.Domaines.exception.ResourceNotFoundException;
import win.Domaines.repository.MessagesRepository;

import java.util.List;

@Service
public class MessagesService {

    private final MessagesRepository messagesRepository;

    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public Messages create(MessagesDTO dto) {
        Messages m = new Messages();
        m.setNom(dto.getNom());
        m.setEmail(dto.getEmail());
        m.setObjet(dto.getObjet());
        m.setMessage(dto.getMessage());
        return messagesRepository.save(m);
    }

    public Messages update(Long id, MessagesDTO dto) {
        Messages m = messagesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id " + id));

        m.setNom(dto.getNom());
        m.setEmail(dto.getEmail());
        m.setObjet(dto.getObjet());
        m.setMessage(dto.getMessage());

        return messagesRepository.save(m);
    }

    public void deleteById(Long id) {
        messagesRepository.deleteById(id);
    }

    public Messages getById(Long id) {
        return messagesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id " + id));
    }

    public List<Messages> getAll() {
        return messagesRepository.findAll();
    }
}
