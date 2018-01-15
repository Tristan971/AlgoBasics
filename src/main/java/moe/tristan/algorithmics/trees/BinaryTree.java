package moe.tristan.algorithmics.trees;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Data
public final class BinaryTree<T> {

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

    public void visitPrefix(final Consumer<T> onKey) {
        onKey.accept(key);
        if (left != null) {
            left.visitPrefix(onKey);
        }
        if (right != null) {
            right.visitPrefix(onKey);
        }
    }

    public void visitInfix(final Consumer<T> onKey) {
        if (left != null) {
            left.visitInfix(onKey);
        }
        onKey.accept(key);
        if (right != null) {
            right.visitInfix(onKey);
        }
    }

    public void visitSuffix(final Consumer<T> onKey) {
        if (left != null) {
            left.visitSuffix(onKey);
        }
        if (right != null) {
            right.visitSuffix(onKey);
        }
        onKey.accept(key);
    }

    private List<T> listImpl(final Consumer<Consumer<T>> visitingStyle) {
        final List<T> elems = new ArrayList<>(getSize());
        visitingStyle.accept(elems::add);
        return elems;
    }

    public List<T> listPrefix() {
        return listImpl(this::visitPrefix);
    }

    public List<T> listInfix() {
        return listImpl(this::visitInfix);
    }

    public List<T> listSuffix() {
        return listImpl(this::visitSuffix);
    }


}
