package moe.tristan.algorithmics.trees;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class BinaryTree<T> {

    private T key;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree(final T key, final BinaryTree<T> left, final BinaryTree<T> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public BinaryTree(final T key) {
        this(key, null, null);
    }

    public int getSize() {
        return 1 + (left == null ? 0 : left.getSize()) + (right == null ? 0 : right.getSize());
    }

    public int getHeight() {
        return 1 + Math.max(left == null ? 0 : left.getHeight(), right == null ? 0 : right.getHeight());
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public void visitPrefix(final Consumer<BinaryTree<T>> onNode) {
        onNode.accept(this);
        if (left != null) {
            left.visitPrefix(onNode);
        }
        if (right != null) {
            right.visitPrefix(onNode);
        }
    }

    public void visitInfix(final Consumer<BinaryTree<T>> onNode) {
        if (left != null) {
            left.visitInfix(onNode);
        }
        onNode.accept(this);
        if (right != null) {
            right.visitInfix(onNode);
        }
    }

    public void visitSuffix(final Consumer<BinaryTree<T>> onNode) {
        if (left != null) {
            left.visitSuffix(onNode);
        }
        if (right != null) {
            right.visitSuffix(onNode);
        }
        onNode.accept(this);
    }

    public List<BinaryTree<T>> listNodes(final Consumer<Consumer<BinaryTree<T>>> visitingStyle) {
        final List<BinaryTree<T>> elems = new ArrayList<>(getSize());
        visitingStyle.accept(elems::add);
        return elems;
    }

    public List<T> listKeysPrefix() {
        return listNodes(this::visitPrefix).stream().sequential()
                .map((Function<BinaryTree<T>, T>) BinaryTree::getKey)
                .collect(Collectors.toList());
    }

    public List<T> listKeysInfix() {
        return listNodes(this::visitInfix).stream().sequential()
                .map((Function<BinaryTree<T>, T>) BinaryTree::getKey)
                .collect(Collectors.toList());
    }

    public List<T> listKeysSuffix() {
        return listNodes(this::visitSuffix).stream().sequential()
                .map((Function<BinaryTree<T>, T>) BinaryTree::getKey)
                .collect(Collectors.toList());
    }


}
