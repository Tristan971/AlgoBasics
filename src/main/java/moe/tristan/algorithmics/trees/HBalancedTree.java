package moe.tristan.algorithmics.trees;

public class HBalancedTree<T> extends BinaryTree<T> {

    public HBalancedTree(T key, BinaryTree<T> left, BinaryTree<T> right) {
        super(key, left, right);
    }

    public HBalancedTree(T key) {
        super(key);
    }

    public HBalancedTree(HBalancedTree<T> referenceTree) {
        super(referenceTree);
    }

    public boolean isBalanced() {
        return listNodes(this::visitInfix).stream().allMatch(node ->
                node.isLeaf() || HBalancedTree.heightDiff(node) <= 1
        );
    }

    private static int heightDiff(final BinaryTree<?> binTree) {
        return Math.abs(Math.subtractExact(
                binTree.getLeft() != null ? binTree.getLeft().getHeight() : 0,
                binTree.getRight() != null ? binTree.getRight().getHeight() : 0
        ));
    }
}
