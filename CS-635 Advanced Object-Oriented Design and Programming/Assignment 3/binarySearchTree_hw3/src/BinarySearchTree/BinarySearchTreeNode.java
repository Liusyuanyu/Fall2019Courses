package BinarySearchTree;
import OrderStrategy.OrderContext;
import Visitor.BinarySearchTreeVisitor;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeNode implements Node {

    private String value;
    private Node left;
    private Node right;
    private OrderContext valueOrder;
    private Boolean isRoot;

    public BinarySearchTreeNode(String value){
        this.value = value;
        left = new NullNode();
        right = new NullNode();
        valueOrder = new OrderContext();
        isRoot = true;
    }

    @Override
    public void add(String value) {
        if(valueOrder.compare(this.value,value)){
            if(left.isNull()){
                left = new BinarySearchTreeNode(value);
                left.setNodeState(false);
                left.setOrderMethod(valueOrder.getCurrentMethodName());
            }
            else {
                left.add(value);
            }
        }
        else{
            if(right.isNull()){
                right = new BinarySearchTreeNode(value);
                right.setNodeState(false);
                right.setOrderMethod(valueOrder.getCurrentMethodName());
            }
            else{
                right.add(value);
            }
        }
    }

    @Override
    public String getValue(){ return value; }
    @Override
    public Boolean isNull() { return false; }
    @Override
    public Node getRight() {
        return right;
    }
    @Override
    public Node getLeft() {
        return left;
    }
    @Override
    public void setNodeState(Boolean isRoot) {
        this.isRoot = isRoot;
    }
    @Override
    public List<String> getOrderMethodNames() {
        return valueOrder.getOrderMethodNames();
    }
    @Override
    public void setOrderMethod(String methodName) {
        valueOrder.setOrderMethod(methodName);
        if(isRoot && ( !left.isNull() || !right.isNull()) ){
            rebuildTree();
        }
    }
    @Override
    public String accept(BinarySearchTreeVisitor visitor) {
        return visitor.representNode(this);
    }
    private void rebuildTree(){
        List<String> valueList = new ArrayList<>();
        inOrder(this,valueList);
        valueList.remove(this.value);
        left = new NullNode(); //Delete root's left children
        right = new NullNode();//Delete root's left children
        valueList.forEach(this::add);
    }
    private void inOrder(Node node,List<String> valueList){
        if(node.isNull()){
            return;
        }
        inOrder(node.getLeft(),valueList);
        valueList.add(node.getValue());
        inOrder(node.getRight(),valueList);
    }
}
