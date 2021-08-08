package com.ycl.gateway.common.tree;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 构造树
 * @date: 2021/7/29 16:29
 */
public class GatewayTree<T> implements ITreeNode {
    private String uuid;
    private String parentId;
    private String name;
    private Integer orderNum;
    private String code;
    private String type;
    private T data;

    /**
     * 无参构造方法
     */
    public GatewayTree() {
    }

    /**
     * 构造方法
     *
     * @param uuid     当前节点的id
     * @param parentId 父节点id
     * @param name     节点的描述
     * @param orderNum 节点在树中的排序号
     * @param code     当前节点的code 用于扩展
     * @param type     当前节点的type 可能有文件夹，文件等等
     * @param data     用户自定义的数据
     */
    public GatewayTree(String uuid, String parentId, String name, Integer orderNum, String code, String type, T data) {
        this.uuid = uuid;
        this.parentId = parentId;
        this.name = name;
        this.orderNum = orderNum;
        this.code = code;
        this.type = type;
        this.data = data;
    }

    @Override
    public String getNodeId() {
        return this.uuid;
    }

    @Override
    public String getNodeName() {
        return this.name;
    }

    @Override
    public String getNodeParentId() {
        return this.parentId;
    }

    @Override
    public Integer getOrderNum() {
        return this.orderNum;
    }

    @Override
    public Object getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }


    public String getCode() {
        return code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
