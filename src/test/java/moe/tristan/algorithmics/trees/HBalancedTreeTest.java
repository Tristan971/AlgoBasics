package moe.tristan.algorithmics.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HBalancedTreeTest {

    private HBalancedTree<Integer> balancedTree;
    private HBalancedTree<Integer> unbalancedTree;

    @BeforeEach
    void setUp() {

        balancedTree = new HBalancedTree<>(
                5,
                new HBalancedTree<>(
                        1,
                        new HBalancedTree<>(4),
                        new HBalancedTree<>(3,
                                new HBalancedTree<>(9),
                                new HBalancedTree<>(8)
                        )
                ),
                new HBalancedTree<>(
                        6,
                        new HBalancedTree<>(5),
                        null
                )
        );

        unbalancedTree = new HBalancedTree<>(
                5,
                new HBalancedTree<>(
                        1,
                        new HBalancedTree<>(4),
                        new HBalancedTree<>(
                                3,
                                new HBalancedTree<>(9),
                                new HBalancedTree<>(8)
                        )
                ),
                new HBalancedTree<>(
                        6,
                        new HBalancedTree<>(
                                5,
                                null,
                                new HBalancedTree<>(3)),
                        null
                )
        );

    }

    @Test
    void isBalanced() {
        assertThat(balancedTree.isBalanced()).isTrue();
        assertThat(unbalancedTree.isBalanced()).isFalse();
    }
}