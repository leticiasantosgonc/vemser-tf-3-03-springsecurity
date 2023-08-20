package br.com.dbc.vemser.tf03spring.security.roles;

import br.com.dbc.vemser.tf03spring.security.permissions.Permissions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Roles {

    ADMIN(
            Set.of(
                    Permissions.ADMIN_CREATE,
                    Permissions.ADMIN_READ,
                    Permissions.ADMIN_UPDATE,
                    Permissions.ADMIN_DELETE,

                    Permissions.PROFESSOR_CREATE,
                    Permissions.PROFESSOR_READ,
                    Permissions.PROFESSOR_UPDATE,
                    Permissions.PROFESSOR_DELETE,

                    Permissions.ALUNO_CREATE,
                    Permissions.ALUNO_READ,
                    Permissions.ALUNO_UPDATE,
                    Permissions.ALUNO_DELETE
            )
    ),
    PROFESSOR(
            Set.of(
                    Permissions.PROFESSOR_CREATE,
                    Permissions.PROFESSOR_READ,
                    Permissions.PROFESSOR_UPDATE,
                    Permissions.PROFESSOR_DELETE
            )
    ),
    ALUNO(
            Set.of(
                    Permissions.ALUNO_CREATE,
                    Permissions.ALUNO_READ,
                    Permissions.ALUNO_UPDATE,
                    Permissions.ALUNO_DELETE
            )
    );

    @Getter
    private final Set<Permissions> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
