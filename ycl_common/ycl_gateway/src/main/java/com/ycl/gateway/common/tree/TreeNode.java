package com.ycl.gateway.common.tree;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 树结构
 * @date: 2021/7/29 15:58
 */
@Data
public class TreeNode<T> {

    /**
     * 树节点ID
     */
    @JSONField(ordinal = 1)
    private String nodeId;

    /**
     * 树节点名称
     */
    @JSONField(ordinal = 2)
    private String nodeName;

    /**
     * 父节点ID
     */
    @JSONField(ordinal = 3)
    private String parentNodeId;

    /**
     * 节点在树中的排序号
     */
    @JSONField(ordinal = 4)
    private int orderNum;

    /**
     * 父节点
     */
    @JSONField(ordinal = 5)
    private TreeNode parent;

    /**
     * 当前节点的儿子节点
     */
    @JSONField(ordinal = 6)
    private List<TreeNode> children = new ArrayList<>();

    /**
     *当前节点的子孙节点
     */
    @JSONField(ordinal = 7)
    private List<TreeNode> allChildren = new ArrayList<>();

    /***
     * 用户自定义数据
     */
    @JSONField(ordinal = 8)
    private  T  data;

    /**
     * 当前节点的类型，有文件夹，文件等等
     */
    @JSONField(ordinal = 9)
    private  String type;

    /**
     * 当前节点的编号，用于扩展
     */
    @JSONField(ordinal = 10)
    private  String code;


    public TreeNode(ITreeNode obj) {
        this.nodeId = obj.getNodeId();
        this.nodeName = obj.getNodeName();
        this.parentNodeId = obj.getNodeParentId();
        this.data= (T) obj.getData();
        this.type=obj.getType();
        this.code=obj.getCode();
        this.orderNum = obj.getOrderNum();
    }

    public void addChild(TreeNode treeNode) {
        this.children.add(treeNode);
    }

    public void removeChild(TreeNode treeNode) {
        this.children.remove(treeNode);
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }


    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<TreeNode> getAllChildren() {
        if (this.allChildren.isEmpty()) {
            for (TreeNode treeNode : this.children) {
                this.allChildren.add(treeNode);
                this.allChildren.addAll(treeNode.getAllChildren());
            }
        }
        return this.allChildren;
    }


}
