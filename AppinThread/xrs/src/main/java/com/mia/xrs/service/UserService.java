package com.mia.xrs.service;

import com.mia.xrs.dto.UserDto;
import com.mia.xrs.entity.User;

public interface UserService {

    UserDto save(UserDto userDto);
}
