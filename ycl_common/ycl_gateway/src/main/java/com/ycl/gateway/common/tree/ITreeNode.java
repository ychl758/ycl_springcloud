package com.ycl.gateway.common.tree;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: ""
 * @date: 2021/7/29 16:27
 */
public interface ITreeNode<T> {

    public String getNodeId();

    public String getNodeName();

    public String getNodeParentId();

    public Integer getOrderNum();

    public  T  getData();

    public  String getType();


    public  String getCode();
}
