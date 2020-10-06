package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.Role;
import nl.tipsntricks.games.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
public class Rolecontroller {

    @Autowired
    private RoleService roleService;

    @GetMapping (value= "/api/roles")
    public Role getRoleById (long id) {
        return roleService.getRoleById(id);
    }

//    @PreAuthorize("hasRole('admin')")
//    @PostMapping (value= "/api/roles/")
//    public Role addRole (Role newRole){
//        return roleService.addRole(newRole);
//    }

    @PreAuthorize("hasRole ('admin')")
    @DeleteMapping (value = "/api/roles/{id}")
    public String deleteRole (long id){
     return roleService.deleteRole(id);
    }
}

