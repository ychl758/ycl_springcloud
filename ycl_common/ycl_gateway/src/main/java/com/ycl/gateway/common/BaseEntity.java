package com.ycl.gateway.common;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 实体的基类
 * @date: 2021/7/30 11:31
 */
@Data
public class BaseEntity  implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 树节点ID
     */
    private String nodeId;

    /**
     * 树节点名称
     */
    private String nodeName;

    /**
     * 父节点ID
     */
    private String parentNodeId;

    /**
     * 节点在树中的排序号
     */
    private int orderNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /***
     * 更新时间
     */
    private Date updateTime;

}
