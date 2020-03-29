package com.identity.model;

import com.identity.dto.UserResponse;

public interface UserObjectMapper {

	UserResponse userAndUserDetailMap(User user, UserDetail userDetail);
}
