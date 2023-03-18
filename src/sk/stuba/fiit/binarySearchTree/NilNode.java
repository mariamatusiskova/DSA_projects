package sk.stuba.fiit.binarySearchTree;

import sk.stuba.fiit.binarySearchTree.NodeOfTheTree;
import sk.stuba.fiit.program.Data;

public class NilNode extends NodeOfTheTree {
    private final boolean BLACK = true;

    NilNode() {
        super(new Data("", 0));
        this.color = BLACK;
    }
}
