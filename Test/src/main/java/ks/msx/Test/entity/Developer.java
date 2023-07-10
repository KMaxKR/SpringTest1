package ks.msx.Test.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Developer {
    private String username;
    private String password;
    private Role role;
}
