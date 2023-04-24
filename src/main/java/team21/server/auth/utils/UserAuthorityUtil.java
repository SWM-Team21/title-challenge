package team21.server.auth.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import team21.server.auth.enums.Role;

import java.util.List;

@Component
public class UserAuthorityUtil {
  private final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList(Role.ROLE_USER.name());

  public Role createRoles() {
    return Role.ROLE_USER;
  }

  public List<GrantedAuthority> createAuthorities(Role role) {
    return USER_ROLES;
  }
}
