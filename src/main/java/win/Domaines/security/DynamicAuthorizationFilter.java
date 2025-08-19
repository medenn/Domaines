package win.Domaines.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import win.Domaines.entity.Permission;
import win.Domaines.service.PermissionCheckService;

@Component
public class DynamicAuthorizationFilter extends OncePerRequestFilter {

    private final PermissionCheckService permissionCheckService;

    public DynamicAuthorizationFilter(PermissionCheckService permissionCheckService) {
        this.permissionCheckService = permissionCheckService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String requestUrl = request.getRequestURI();
        String requestMethod = request.getMethod(); // GET, POST, PUT, DELETE...

        // Autoriser directement /api/auth/**
        if (requestUrl.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Autoriser tous les GET publics sauf exceptions
        if ("GET".equalsIgnoreCase(requestMethod)) {
            if (!(requestUrl.startsWith("/api/roles/") ||
                  requestUrl.startsWith("/api/users/") ||
                  requestUrl.startsWith("/api/permissions/") ||
                  requestUrl.startsWith("/api/messages/"))) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        // Vérification des permissions pour les endpoints protégés
        List<Permission> permissions = permissionCheckService.getPermissionsForUrlAndMethod(requestUrl, requestMethod);

        if (permissions.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Access denied: no permission configured for this URL and method");
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Access denied: not authenticated");
            return;
        }

        // Vérifier si l'utilisateur a au moins un rôle correspondant à une des permissions
        boolean hasRole = permissions.stream().anyMatch(p ->
                authentication.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals(p.getRole()))
        );

        if (!hasRole) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Access denied: insufficient permissions");
            return;
        }

        // Autorisé
        filterChain.doFilter(request, response);
    }
}
