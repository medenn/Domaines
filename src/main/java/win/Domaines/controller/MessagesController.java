package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.MessagesDTO;
import win.Domaines.entity.Messages;
import win.Domaines.service.MessagesService;
import win.Domaines.service.ErrorLogService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    private final MessagesService messagesService;
    private final ErrorLogService errorLogService;

    public MessagesController(MessagesService messagesService, ErrorLogService errorLogService) {
        this.messagesService = messagesService;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MessagesDTO dto) {
        try {
            Messages saved = messagesService.create(dto);
            return ResponseEntity.ok(MessagesDTO.fromEntity(saved));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create message failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating message. Ticket: " + ticket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MessagesDTO dto) {
        try {
            Messages updated = messagesService.update(id, dto);
            return ResponseEntity.ok(MessagesDTO.fromEntity(updated));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update message failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating message. Ticket: " + ticket);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            messagesService.deleteById(id);
            return ResponseEntity.ok("Message deleted successfully");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete message failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting message. Ticket: " + ticket);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Messages message = messagesService.getById(id);
            return ResponseEntity.ok(MessagesDTO.fromEntity(message));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get message by id failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error fetching message. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<MessagesDTO>> getAll() {
        List<MessagesDTO> list = messagesService.getAll()
                .stream()
                .map(MessagesDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
