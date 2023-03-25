package sk.stuba.fiit.binarySearchTree;

public class NilNode extends NodeOfTheTree {
    private final boolean BLACK = true;

    NilNode() {
        super(new Data("", 0));
        this.color = BLACK;
    }
}
