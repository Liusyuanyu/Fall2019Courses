package BinarySearchTree;

import Visitor.BinarySearchTreeVisitor;

//public interface Node {
public interface Node {
//    public String value;
    public String getValue();
    public void add(String value);
    public Boolean isNull();
    public Node getRight();
    public Node getLeft();

    public String accept(BinarySearchTreeVisitor vistor);
}
