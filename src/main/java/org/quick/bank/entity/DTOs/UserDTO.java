package org.quick.bank.entity.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.quick.bank.entity.models.User;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;
    private String email;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createAccount;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.createAccount = user.getCreateAccount();
    }
}
