package win.Domaines.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ErrorLogService {

    @Value("${app.error.log-path}")
    private String logPath;

    /**
     * Log the error, create ticket id and return it.
     */
    public String logError(String message, Throwable ex) {
        String ticket = UUID.randomUUID().toString();
        try {
            Path dir = Paths.get(logPath);
            if (!Files.exists(dir)) Files.createDirectories(dir);

            String fileName = String.format("ticket_%s_%s.log", LocalDateTime.now().toLocalDate(), ticket);
            Path file = dir.resolve(fileName);
            try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(file, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
                pw.println("=== Ticket: " + ticket + " ===");
                pw.println("Date: " + LocalDateTime.now());
                pw.println("Message: " + message);
                if (ex != null) {
                    pw.println("Exception: " + ex.getClass().getName());
                    pw.println("Detail: " + ex.getMessage());
                    pw.println("Stacktrace:");
                    ex.printStackTrace(pw);
                }
                pw.println("==================================");
            }
        } catch (IOException ioe) {
            // fallback to stderr
            System.err.println("Unable to write ticket file: " + ioe.getMessage());
        }
        return ticket;
    }
}
