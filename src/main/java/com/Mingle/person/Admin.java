package com.Mingle.person;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@ApiModel(value="管理员信息",description="管理员的信息")
@Data
public class Admin {
    private Integer id;
    @NotBlank
    private String username;
    private String password;
    private Integer status;
}
