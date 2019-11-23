package BinarySearchTree;

import Visitor.BinarySearchTreeVisitor;

public class NullNode implements Node{

    public NullNode(){}
    @Override
    public Boolean isNull(){
        return true;
    }
    @Override
    public void add(String value) {}

    @Override
    public String getValue(){
        return null;
    }

    @Override
    public Node getRight() {
        return null;
    }

    @Override
    public Node getLeft() {
        return null;
    }

    @Override
    public String accept(BinarySearchTreeVisitor vistor) {
        return null;
    }
}
