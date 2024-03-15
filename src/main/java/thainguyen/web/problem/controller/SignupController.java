package thainguyen.web.problem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import thainguyen.web.problem.dto.UserDto;
import thainguyen.web.problem.service.UserService;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new UserDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "signup";
        }
        if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
            result.addError(new FieldError("userDto", "passwordConfirm", "Mật khẩu không khớp"));
            return "signup";
        } else {
            userService.createUser(userDto);
            return "redirect:signup?success";
        }
    }
}
