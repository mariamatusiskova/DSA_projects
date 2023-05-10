package sk.stuba.fiit.Tree;

public class LeafNode extends Node {

    boolean value = false;
    public LeafNode(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeafNode node = (LeafNode) o;
        return this.value == node.value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
