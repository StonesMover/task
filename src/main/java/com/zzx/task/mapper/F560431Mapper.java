package com.zzx.task.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zzx.task.pojo.F560431;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Mapper
@DS("oracle")
@Service
public interface F560431Mapper {
    int InsertF560431(F560431 f560431);
    int DeleteF560431();
}
