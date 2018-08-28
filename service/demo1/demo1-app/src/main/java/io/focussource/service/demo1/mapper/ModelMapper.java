package io.focussource.service.demo1.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.focussource.service.demo1.api.model.Model;


/**
 * mapper for demo1.
 */
@Mapper
public interface ModelMapper {
    @Select("SELECT CURRENT_TIMESTAMP()")
    Date mysqlTime();

    @Select("SELECT * FROM model")
    List<Model> list();
}
