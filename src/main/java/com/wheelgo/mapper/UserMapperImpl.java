package com.wheelgo.mapper;

import com.wheelgo.dto.UserDto;
import com.wheelgo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());

        return userDto;
    }
}