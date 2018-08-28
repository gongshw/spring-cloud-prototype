package io.focussource.service.demo1.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * mapper for demo1.
 */
@Mapper
public interface ModelMapper {
    @Select("SELECT CURRENT_TIMESTAMP()")
    Date mysqlTime();
}
