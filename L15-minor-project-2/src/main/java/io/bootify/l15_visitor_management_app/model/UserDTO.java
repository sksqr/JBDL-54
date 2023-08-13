package io.bootify.l15_visitor_management_app.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String phone;

    @Size(max = 255)
    private String role;

    @NotNull
    private UserStatus status;

    private String flatNumber;

    private AddressDTO address;

}
