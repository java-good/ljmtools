package com.ljm.tools.collections;

import java.util.List;

/**
 * @author liujunmin
 * @since: Created by work on 2022/1/27 17:31
 */
public interface TreeNode<T> {

    /**
     * 获取节点id
     * @return
     */
    T id();

    /**
     * 获取节点的父节点id
     * @return
     */
    T parent();

    /**
     * 是否根节点
     * @return
     */
    boolean root();

    /**
     * 设置子节点列表
     * @param children
     */
    void setChildren(List<? extends TreeNode> children);

    /**
     * 获取所有子节点
     * @return
     */
    List<? extends TreeNode> getChildren();
}
