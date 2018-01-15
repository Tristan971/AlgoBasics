package moe.tristan.algorithmics.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PerfectTreeTest {

    private PerfectTree<Integer> perfectTree;
    private PerfectTree<Integer> imperfectTree;

    @BeforeEach
    void setUp() {
        perfectTree = new PerfectTree<>(
                8,
                new PerfectTree<>(
                        5,
                        new PerfectTree<>(
                                6,
                                new PerfectTree<>(3),
                                new PerfectTree<>(18)
                        ),
                        new PerfectTree<>(
                                2,
                                new PerfectTree<>(9),
                                null
                        )
                ),
                new PerfectTree<>(
                        1,
                        new PerfectTree<>(4),
                        new PerfectTree<>(7)
                )
        );

        imperfectTree = new PerfectTree<>(
                8,
                new PerfectTree<>(
                        5,
                        new PerfectTree<>(
                                6,
                                new PerfectTree<>(3),
                                new PerfectTree<>(18)
                        ),
                        new PerfectTree<>(
                                2,
                                new PerfectTree<>(9),
                                null
                        )
                ),
                new PerfectTree<>(
                        1,
                        new PerfectTree<>(4),
                        null
                )
        );
    }

    @Test
    void shouldReturnTrueOnPerfect() {
        assertThat(perfectTree.isPerfect()).isTrue();
    }

    @Test
    void shouldReturnFalseOnImperfect() {
        assertThat(imperfectTree.isPerfect()).isFalse();
    }
}
