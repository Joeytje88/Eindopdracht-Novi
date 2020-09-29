package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Role;
import nl.tipsntricks.games.exception.RoleNotFoundException;
import nl.tipsntricks.games.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Rolecontroller {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AppUserController appUserController;

    @GetMapping (value= "/api/roles")
    public List <Role>  getRoles () {
        List <Role> role = roleRepository.findAll();
        return role;
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping (value= "/api/roles/")
    public Role addRole (@RequestBody Role newRole){
        return roleRepository.save(newRole);
    }

    @PreAuthorize("hasRole ('admin')")
    @DeleteMapping
    public String deleteRole (@PathVariable long id){
        Optional <Role> role = roleRepository.findById(id);

        if (role.isPresent()) {
            roleRepository.deleteById(id);
            return "Rol met id" + role.get().getId() + "is verwijderd";
        }
        return "Rol niet gevonden";
    }

    @PreAuthorize("hasRole ('admin')")
    @PutMapping (value = "/api/role/{id}/user")
    public Role addUserToRole (@PathVariable long id,
                               @RequestBody Role newRole) {
        Optional <Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            Role roleFromDb = role.get();
            List<AppUser> rolelist = roleFromDb.getRoles();

            List <Role> roles = new ArrayList<>();
            roles.add(roleFromDb);

            newRole.setRoles(rolelist);
            roleFromDb.setRoles(rolelist);

            return roleRepository.save(newRole);
        }

        throw new RoleNotFoundException("Rol bestaat niet");
    }

}

