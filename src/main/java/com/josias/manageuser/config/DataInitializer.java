package com.josias.manageuser.config;

import com.josias.manageuser.model.Permission;
import com.josias.manageuser.model.Role;
import com.josias.manageuser.model.User;
import com.josias.manageuser.repository.PermissionRepository;
import com.josias.manageuser.repository.RoleRepository;
import com.josias.manageuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) {
        // Vérifier et insérer les permissions
        List<Permission> permissions = List.of(
                new Permission("VIEW_EMPLOYEES"),
                new Permission("ADD_EMPLOYEE"),
                new Permission("DELETE_EMPLOYEE"),
                new Permission("ACCESS_TECH_DOCS"),
                new Permission("ACCESS_CLIENTS")
        );

        for (Permission permission : permissions) {
            if (!permissionRepository.existsByName(permission.getName())) {
                permissionRepository.save(permission);
            }
        }

        // Récupérer les permissions après insertion pour éviter les doublons en mémoire
        Permission viewEmployees = permissionRepository.findByName("VIEW_EMPLOYEES");
        Permission addEmployee = permissionRepository.findByName("ADD_EMPLOYEE");
        Permission deleteEmployee = permissionRepository.findByName("DELETE_EMPLOYEE");
        Permission accessTechDocs = permissionRepository.findByName("ACCESS_TECH_DOCS");
        Permission accessClients = permissionRepository.findByName("ACCESS_CLIENTS");

        // Vérifier et insérer les rôles
        List<Role> roles = List.of(
                new Role("DG", List.of(viewEmployees, addEmployee, deleteEmployee, accessTechDocs, accessClients)),
                new Role("ASSISTANTE_DIRECTION", List.of(viewEmployees)),
                new Role("DEVELOPPEUR", List.of(accessTechDocs)),
                new Role("RH", List.of(addEmployee, deleteEmployee)),
                new Role("COMMERCIAL", List.of(accessClients))
        );

        for (Role role : roles) {
            if (!roleRepository.existsByName(role.getName())) {
                roleRepository.save(role);
            }
        }

        // Vérifier et insérer l'utilisateur DG
        if (!userRepository.existsByEmail("dg@gmail.com")) {
            User superAdmin = new User("dg@gmail.com", new BCryptPasswordEncoder().encode("password"));
            superAdmin.getRoles().add(roleRepository.findByName("DG")); // Récupérer le rôle depuis la DB
            userRepository.save(superAdmin);
        }
    }

}

