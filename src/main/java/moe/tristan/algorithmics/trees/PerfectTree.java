package moe.tristan.algorithmics.trees;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PerfectTree<T> extends HBalancedTree<T> {

    public PerfectTree(T key, BinaryTree<T> left, BinaryTree<T> right) {
        super(key, left, right);
    }

    public PerfectTree(T key) {
        super(key);
    }

    public boolean isPerfect() {
        final int height = this.getHeight();

        List<BinaryTree<T>> curLevelNodes = Collections.singletonList(this);

        for (int currentLevel = 0; currentLevel < height - 1; currentLevel++) {
            if (curLevelNodes.size() != Math.pow(2, currentLevel)) {
                return false;
            }
            curLevelNodes = curLevelNodes.stream()
                    .map(node -> Arrays.asList(node.getLeft(), node.getRight()))
                    .flatMap(List::stream)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return true;
    }

}
