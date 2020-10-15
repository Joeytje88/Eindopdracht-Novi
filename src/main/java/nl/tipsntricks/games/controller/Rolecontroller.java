package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.Role;
import nl.tipsntricks.games.repository.RoleRepository;
import nl.tipsntricks.games.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
public class Rolecontroller {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping (value= "/api/roles/{id}")
    public Role getRoleById (long id) {
        return roleService.getRoleById(id);
    }

    @GetMapping (value = "api/roles")
    List<Role> getAllRoles(){
        return roleRepository.findAll();
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

