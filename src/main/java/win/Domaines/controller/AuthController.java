package win.Domaines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import win.Domaines.dto.AuthRequest;
import win.Domaines.dto.AuthResponse;
import win.Domaines.service.AuthService;
import win.Domaines.service.ErrorLogService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final ErrorLogService errorLogService;

    public AuthController(AuthService authService, ErrorLogService errorLogService){
        this.authService = authService;
        this.errorLogService = errorLogService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req){
        try {
            String token = authService.authenticate(req.getUsername(), req.getPassword());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception ex) {
            String ticket = errorLogService.logError("Authentication failed: " + ex.getMessage(), ex);
            return ResponseEntity.status(401).body("Authentication failed. Ticket: " + ticket);
        }
    }
}
