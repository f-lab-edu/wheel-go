package com.wheelgo.mapper;

import com.wheelgo.dto.UserDto;
import com.wheelgo.entity.User;

public interface UserMapper {
    UserDto toDto(User user);
}