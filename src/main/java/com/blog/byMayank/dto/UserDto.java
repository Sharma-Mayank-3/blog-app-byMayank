package com.blog.byMayank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int userId;

    private String userName;
    private int userAge;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
