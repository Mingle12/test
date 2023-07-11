package com.Mingle.dao;

import com.Mingle.person.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;

@Mapper
@Primary
public interface AdminDao extends BaseMapper<Admin> {
}
