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
@RequestMapping("/api/commercial")
public class CommercialController {

    // private final UserService userService;

    @GetMapping("/clients")
    @PreAuthorize("hasAuthority('ACCESS_CLIENTS')")
    public ResponseEntity<String> voirClients() {
        return ResponseEntity.ok("Liste des clients accessibles !");
    }


}
