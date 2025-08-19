package win.Domaines.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;

@Component
public class TicketLogger {

    @Value("${ticket.log.path}")
    private String ticketPath;

    public void createTicket(String errorMessage, Throwable ex) {
        try {
            Files.createDirectories(Paths.get(ticketPath));
            String fileName = "ticket_" + System.currentTimeMillis() + ".log";
            File file = new File(ticketPath, fileName);
            try (FileWriter fw = new FileWriter(file)) {
                fw.write("=== Ticket d'erreur ===\n");
                fw.write("Date: " + LocalDateTime.now() + "\n");
                fw.write("Message: " + errorMessage + "\n\n");
                if (ex != null) {
                    fw.write("Stacktrace:\n");
                    ex.printStackTrace(new PrintWriter(fw));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
