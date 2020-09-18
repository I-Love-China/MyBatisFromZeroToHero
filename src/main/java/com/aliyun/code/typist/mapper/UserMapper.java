package com.aliyun.code.typist.mapper;

import com.aliyun.code.typist.model.SysUser;

public interface UserMapper {
    SysUser selectById(Long id);

    int insert(SysUser sysUser);

    int updateById(SysUser sysUser);

    int deleteById(SysUser sysUser);

    int deleteById(Long id);
}
