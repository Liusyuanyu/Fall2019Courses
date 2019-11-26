package BinarySearchTree;

import Visitor.BinaryTreeRepresentationVisitor;
import java.util.List;

public class NullNode implements Node{

    public NullNode(){}
    @Override
    public Boolean isNull(){
        return true;
    }
    @Override
    public void add(String value) {
        throw new RuntimeException("NullNode doesn't implement add function.");
    }
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
    public void setNodeState(Boolean isRoot) {
        throw new RuntimeException("NullNode doesn't implement setNodeState function.");
    }
    @Override
    public List<String> getOrderMethodNames() {
        return null;
    }
    @Override
    public void setOrderMethod(String methodName) {
        throw new RuntimeException("NullNode doesn't implement add function.");
    }
    @Override
    public String accept(BinaryTreeRepresentationVisitor visitor) {
        return null;
    }
}
