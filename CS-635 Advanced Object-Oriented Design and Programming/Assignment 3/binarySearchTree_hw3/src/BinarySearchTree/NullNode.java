package BinarySearchTree;

import Visitor.BinarySearchTreeVisitor;

import java.util.List;

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
    public void setNodeState(Boolean isRoot) { }
    @Override
    public List<String> getOrderMethodName() {
        return null;
    }
    @Override
    public void setOrderMethod(String methodName) { }

    @Override
    public String accept(BinarySearchTreeVisitor visitor) {
        return null;
    }
}
