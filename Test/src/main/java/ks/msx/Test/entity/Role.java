package ks.msx.Test.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public enum Role {
    USER(List.of(Authority.READ.name())),
    ADMIN(List.of(Authority.WRITE.name(), Authority.READ.name()));

    <E> Role(List<E> name) {

    }

    public String getAuthority(){
        return name();
    }
}
