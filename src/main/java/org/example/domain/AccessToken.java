package org.example.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AccessToken {
    private String subject;
    private Role role;
    private Long userId;

//    @JsonIgnore
//    public boolean hasRole(String roleName) {
//        if (roles == null) {
//            return false;
//        }
//        return roles.contains(roleName);
//    }
}
