package com.Mingle.person;

import io.swagger.annotations.ApiModel;
import lombok.Data;


@ApiModel(value="用户信息",description="用户的信息")
@Data
public class Person {

  private Integer id;
  private String name;
  private long age;
}
