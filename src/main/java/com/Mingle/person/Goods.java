package com.Mingle.person;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value="商品信息",description="商品的信息")
@Data
public class Goods {
    private Integer id;
    private String name;
    private String picInfo;
    private Double price;
}
