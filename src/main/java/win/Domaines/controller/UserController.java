package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.UserDTO;
import win.Domaines.service.ErrorLogService;
import win.Domaines.service.UserService;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ErrorLogService errorLogService;

    public UserController(UserService userService, ErrorLogService errorLogService){
        this.userService = userService;
        this.errorLogService = errorLogService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDTO dto){
        try {
            UserDTO result = userService.create(dto);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Create user failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error creating user. Ticket: " + ticket);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> list(){
        return ResponseEntity.ok(userService.listAll());
    }

    // GET user by id
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.getById(id));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Get user failed: " + ex.getMessage(), ex);
            return ResponseEntity.status(404).body("Not found. Ticket: " + ticket);
        }
    }

    // PUT update user by id
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserDTO dto){
        try {
            UserDTO updatedUser = userService.update(id, dto);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Update user failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error updating user. Ticket: " + ticket);
        }
    }

    // DELETE user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            userService.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Delete user failed: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body("Error deleting user. Ticket: " + ticket);
        }
    }
}

