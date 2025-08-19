package win.Domaines.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import win.Domaines.entity.Permission;
import win.Domaines.repository.PermissionRepository;

@Service
public class PermissionCheckService {

    private final PermissionRepository permissionRepository;
    private final AntPathMatcher matcher = new AntPathMatcher();

    public PermissionCheckService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    /**
     * Retourne toutes les permissions correspondant à l’URL et à la méthode
     * @param requestUrl URL demandée
     * @param requestMethod GET, POST, PUT, DELETE...
     */
    public List<Permission> getPermissionsForUrlAndMethod(String requestUrl, String requestMethod) {
        return permissionRepository.findAll().stream()
                .filter(p -> matcher.match(p.getUrlPattern(), requestUrl))
                .filter(p -> p.getMethode().equals("*") || p.getMethode().equalsIgnoreCase(requestMethod))
                .toList();
    }
}
