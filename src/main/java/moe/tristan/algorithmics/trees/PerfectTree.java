package moe.tristan.algorithmics.trees;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PerfectTree<T> extends HBalancedTree<T> {

    public PerfectTree(T key, BinaryTree<T> left, BinaryTree<T> right) {
        super(key, left, right);
    }

    public PerfectTree(T key) {
        super(key);
    }

    public PerfectTree(Object[] backingArray, final Class<T> elementsClass) {
        super(fromArray(backingArray, elementsClass));
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

    public Object[] toArray() {
        final int size = this.getSize();
        final Object[] backingArray = new Object[size+1];
        backingArray[0] = size;

        List<BinaryTree<T>> elems = new ArrayList<>();
        elems.add(this);
        for (int i = 1; i <= size; i++) {
            final BinaryTree<T> curElem = elems.remove(0);
            backingArray[i] = curElem.getKey();
            if (curElem.getLeft() != null) {
                elems.add(curElem.getLeft());
            }
            if (curElem.getRight() != null) {
                elems.add(curElem.getRight());
            }
        }

        return backingArray;
    }

    private static <T> PerfectTree<T> fromArray(final Object[] backingArray, final Class<T> elementsClass) {
        final PerfectTree<T> reconstruction = new PerfectTree<>(
                elementsClass.cast(backingArray[1])
        );

        final Function<Consumer<PerfectTree<T>>, Function<T, PerfectTree<T>>> nextOpsSupplier = childConsumer ->
                aKey -> {
                    final PerfectTree<T> createdNode = new PerfectTree<>(aKey);
                    childConsumer.accept(createdNode);
                    return createdNode;
                };

        final List<Function<T, PerfectTree<T>>> nextKeysAcceptors = new LinkedList<>();
        nextKeysAcceptors.add(nextOpsSupplier.apply(reconstruction::setLeft));
        nextKeysAcceptors.add(nextOpsSupplier.apply(reconstruction::setRight));


        for (int i = 2; i < backingArray.length; i++) {
            final PerfectTree<T> nodeAdded = nextKeysAcceptors
                    .remove(0)
                    .apply(elementsClass.cast(backingArray[i]));
            nextKeysAcceptors.add(nextOpsSupplier.apply(nodeAdded::setLeft));
            nextKeysAcceptors.add(nextOpsSupplier.apply(nodeAdded::setRight));
        }

        return reconstruction;
    }
}
