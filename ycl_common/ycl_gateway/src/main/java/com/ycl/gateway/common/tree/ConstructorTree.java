package com.ycl.gateway.common.tree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.ycl.gateway.common.Constant;

import java.util.List;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 构造获取树对象
 * @date: 2021/7/30 11:00
 */
public class ConstructorTree {

    /**
     * 构造方法
     */
    private ConstructorTree() {
    }

    /**
     * 构建树
     * @param list 查询出来需要构建成树组成数据
     * @return 树的json字符串
     */
    public static String getTree(List<ITreeNode> list) {
        Tree tree = new Tree(list);
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add(Constant.TREE_PARENT);
        filter.getExcludes().add(Constant.TREE_ALL_CHILDEN);
        return JSON.toJSONString(tree.getRoot(), filter);
    }

    /**
     * 根据树的id 寻找该id下的其他全部节点
     *
     * @param list   查询出来的数据
     * @param treeId 需要构建的下级的树id
     * @return  树的json字符串
     */
    public static String getTree(List<ITreeNode> list, String treeId) {
        Tree tree = new Tree(list);
        TreeNode treeNode = tree.getTreeNode(treeId);
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add(Constant.TREE_PARENT);
        filter.getExcludes().add(Constant.TREE_ALL_CHILDEN);
        return JSON.toJSONString(treeNode, filter);
    }
}