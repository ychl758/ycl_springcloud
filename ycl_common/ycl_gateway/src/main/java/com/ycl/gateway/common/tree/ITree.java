package com.ycl.gateway.common.tree;

import java.util.List;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 树接口
 * @date: 2021/7/29 16:16
 */
public interface ITree {

    public List<TreeNode> getTree();

    public List<TreeNode> getRoot();

    public TreeNode getTreeNode(String nodeId);


}
