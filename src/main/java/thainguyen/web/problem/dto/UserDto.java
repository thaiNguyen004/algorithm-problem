package thainguyen.web.problem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import thainguyen.web.problem.validation.Password;

@Getter
@Setter
public class UserDto {
    @NotBlank(message = "Tên không được để trống")
    private String firstName;
    @NotBlank(message = "Họ không được để trống")
    private String lastName;
    @NotBlank(message = "Email không được để trống")
    private String email;
    @NotBlank(message = "Username không được để trống")
    private String username;
    @Password
    private String password;
    private String passwordConfirm;

}
