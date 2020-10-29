package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Role;

public interface IRoleService {
    Role getRoleById (long id);
    String deleteRole (long id);
//    Role addRole (Role newRole);

}
