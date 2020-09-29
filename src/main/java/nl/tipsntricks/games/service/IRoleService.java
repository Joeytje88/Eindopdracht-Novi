package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Role;

public interface IRoleService {
    Role getRoleById (long id);
    Role addRole (Role newRole);
    String deleteRole (long id);
    Role addUsertoRole (long id, Role updatedRol);
    Role AddTestRoleWithUser();

}
