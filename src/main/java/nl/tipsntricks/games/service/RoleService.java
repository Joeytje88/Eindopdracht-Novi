package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Role;
import nl.tipsntricks.games.exception.RoleNotFoundException;
import nl.tipsntricks.games.repository.RoleRepository;

import java.util.Optional;


public class RoleService implements IRoleService {


    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Rol niet gevonden"));

    }

    @Override
    public Role addRole(Role newRole) {
       return null;
    }

    @Override
    public String deleteRole(long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            roleRepository.deleteById(id);
            return "Rol met id" + role.get().getId() + "is verwijderd";
        }
        throw new RoleNotFoundException("Deze rol bestaat niet (meer)");
    }


    @Override
    public Role addUsertoRole(long id, Role updatedRol) {
        return null;
    }

    @Override
    public Role AddTestRoleWithUser() {
        return null;
    }

    private boolean checkIsValidName(String name) {
        if(name.contains("fuck") || name.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

}
