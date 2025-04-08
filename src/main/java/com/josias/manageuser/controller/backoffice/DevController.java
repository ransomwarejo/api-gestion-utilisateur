package com.josias.manageuser.controller.backoffice;


import com.josias.manageuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dev")
public class DevController {
    // private final UserService userService;

    @GetMapping("/docs-techniques")
    @PreAuthorize("hasAuthority('ACCESS_TECH_DOCS')")
    public ResponseEntity<String> voirDocsTechniques() {
        return ResponseEntity.ok("Accès aux documents techniques !");
    }
}
