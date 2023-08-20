package br.com.dbc.vemser.tf03spring.model;

import br.com.dbc.vemser.tf03spring.security.roles.Roles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity(name = "USUARIO")
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQUENCIA")
    @SequenceGenerator(name = "USUARIO_SEQUENCIA", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "SENHA")
    private String senha;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
