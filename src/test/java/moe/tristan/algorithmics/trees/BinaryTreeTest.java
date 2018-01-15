package moe.tristan.algorithmics.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryTreeTest {

    private BinaryTree<Integer> testTree = null;

    @BeforeEach
    void setUp() {
        testTree = new BinaryTree<>(1,
                new BinaryTree<>(
                        5,
                        null,
                        new BinaryTree<>(3)
                ),
                new BinaryTree<>(
                        7,
                        new BinaryTree<>(10),
                        new BinaryTree<>(
                                8,
                                new BinaryTree<>(9),
                                null
                        )
                )
        );
    }

    @Test
    void getSize() {
        assertThat(testTree.getSize()).isEqualTo(7);
    }

    @Test
    void getHeight() {
        assertThat(testTree.getHeight()).isEqualTo(4);
    }

    @Test
    void isLeaf() {
        assertThat(testTree.getLeft().getRight().isLeaf()).isTrue();
    }

    @Test
    void visitPrefix() {
        final StringBuilder sb = new StringBuilder(testTree.getSize() * 2);
        testTree.visitPrefix(sb::append);
        assertThat(sb.toString()).isEqualToIgnoringWhitespace("1 5 3 7 10 8 9");
    }

    @Test
    void visitInfix() {
        final StringBuilder sb = new StringBuilder(testTree.getSize() * 2);
        testTree.visitInfix(sb::append);
        assertThat(sb.toString()).isEqualToIgnoringWhitespace("5 3 1 10 7 9 8");
    }

    @Test
    void visitSuffix() {
        final StringBuilder sb = new StringBuilder(testTree.getSize() * 2);
        testTree.visitSuffix(sb::append);
        assertThat(sb.toString()).isEqualToIgnoringWhitespace("3 5 10 9 8 7 1");
    }

    @Test
    void listPrefix() {
        assertThat(testTree.listPrefix()).containsExactly(1, 5, 3, 7, 10, 8, 9);
    }

    @Test
    void listInfix() {
        assertThat(testTree.listInfix()).containsExactly(5, 3, 1, 10, 7, 9, 8);
    }

    @Test
    void listSuffix() {
        assertThat(testTree.listSuffix()).containsExactly(3, 5, 10, 9, 8, 7, 1);
    }

}
