package com.ljm.tools.collections;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author liujunmin
 * @since: Created by work on 2022/1/27 17:36
 */
public class TreeUtils {

    /**
     * 构建对象树
     * @param nodes
     * @param <T>
     * @return
     */
    public static <T extends TreeNode<?>> List<T> buildTrees(List<T> nodes){
        List<T> roots = findRootNodes(nodes);
        roots.forEach(e -> setChildren(e, nodes));
        return roots;
    }

    /**
     * 查找根节点
     * @param nodes
     * @param <T>
     * @return
     */
    public static <T extends TreeNode<?>> List<T> findRootNodes(List<T> nodes){
        List<T> roots = new ArrayList<>();
        for (Iterator<T> it = nodes.iterator(); it.hasNext();){
            T node = it.next();
            // 添加根节点
            if (node.root()){
                roots.add(node);
                // 删除已添加的根节点
                it.remove();
            }
        }
        return roots;
    }

    /**
     * 递归设置所有子节点
     * @param parent
     * @param nodes
     * @param <T>
     */
    public static <T extends TreeNode> void setChildren(T parent, List<T> nodes){
        List<T> children = new ArrayList<>();
        Object parentId = parent.id();
        for (Iterator<T> it = nodes.iterator(); it.hasNext();){
            T node = it.next();
            if (Objects.equals(node.parent(), parentId)){
                children.add(node);
                it.remove();
            }
        }
        if (children.isEmpty())
            return;
        parent.setChildren(children);
        children.forEach(e -> setChildren(e, nodes));
    }

    /**
     * 获取所有叶子节点
     * @param nodes
     * @param <T>
     * @return
     */
    public static <T extends TreeNode<?>> List<T> getAllLeafs(List<T> nodes){
        List<T> rootNodes = findRootNodes(nodes);
        List<T> leafs = new ArrayList<>();
        rootNodes.forEach(e -> leafs.addAll(getLeafs(e)));
        return leafs;
    }

    /**
     * 指定树节点下所有叶子节点
     * @param parent
     * @param <T>
     * @return
     */
    public static <T extends TreeNode<?>> List<T> getLeafs(T parent){
        List<T> leafs = new ArrayList<>();
        fillLeaf(parent, leafs);
        return leafs;
    }

    /**
     * 将parent的所有叶子节点填充至leafs列表中
     * @param parent
     * @param leafs
     * @param <T>
     */
    public static <T extends TreeNode> void fillLeaf(T parent, List<T> leafs){
        List<T> children = parent.getChildren();
        if (CollectionUtils.isEmpty(children)){
            leafs.add(parent);
            return;
        }
        for (T child : children){
            fillLeaf(child, leafs);
        }
    }
}
