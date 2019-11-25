package BinarySearchTree;

import Visitor.BinarySearchTreeVisitor;

import java.util.List;

public interface Node {
    String getValue();
    void add(String value);
    Boolean isNull();
    Node getRight();
    Node getLeft();

    void setNodeState(Boolean isRoot);
    List<String> getOrderMethodNames();
    void setOrderMethod(String methodName);

    String accept(BinarySearchTreeVisitor visitor);
}
