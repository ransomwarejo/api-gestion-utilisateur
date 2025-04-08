package com.josias.manageuser.controller.backoffice;


import com.josias.manageuser.dto.AddEmployeRequest;
import com.josias.manageuser.model.User;
import com.josias.manageuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rh")
public class RHController {

    private final UserService userService;

    @PostMapping("/ajouter-employe")
    //@PreAuthorize("hasAuthority('ADD_EMPLOYEE')")
    public ResponseEntity<?> ajouterEmploye(@RequestBody AddEmployeRequest request) {
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setUsername(request.getUsername());

        User savedUser = userService.createUser(newUser, request.getRole());
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/supprimer-employe/{id}")
    @PreAuthorize("hasAuthority('DELETE_EMPLOYEE')")
    public ResponseEntity<String> supprimerEmploye(@PathVariable Long id) {
        return ResponseEntity.ok("Employé supprimé !");
    }
}
