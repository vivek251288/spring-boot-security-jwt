package com.Java_coder_spring_security.dto;

import java.util.HashSet;
import java.util.Set;
import com.Java_coder_spring_security.entity.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private String username;
    private String password;
    private String name;

    private Set<RoleType> roles = new HashSet<>();
}
