package org.quick.bank.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String email;

    private String password;

    private String name;

    private Long id;

    public boolean someoneValuesIsNull() {
        if (email == null) return true;
        if (password == null) return true;
        return name == null;
    }
}
