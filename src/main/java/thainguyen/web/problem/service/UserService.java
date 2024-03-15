package thainguyen.web.problem.service;

import thainguyen.web.problem.dto.UserDto;
import thainguyen.web.problem.model.ApplicationUser;

public interface UserService {
    ApplicationUser createUser(UserDto userDto);
}
