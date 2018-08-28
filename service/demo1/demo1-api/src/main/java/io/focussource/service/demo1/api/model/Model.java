package io.focussource.service.demo1.api.model;

import java.util.Date;

import lombok.Data;

/**
 * model data class.
 */
@Data
public class Model {
    private long id;

    private String content;

    private Date createTime;

    private Date updateTime;
}
