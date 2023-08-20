package br.com.dbc.vemser.tf03spring.security.permissions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permissions {

    ADMIN_CREATE("admin:create"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),

    PROFESSOR_CREATE("professor:create"),
    PROFESSOR_READ("professor:read"),
    PROFESSOR_UPDATE("professor:update"),
    PROFESSOR_DELETE("professor:delete"),

    ALUNO_CREATE("aluno:create"),
    ALUNO_READ("aluno:read"),
    ALUNO_UPDATE("aluno:update"),
    ALUNO_DELETE("aluno:delete");

    @Getter
    private final String permission;

}
