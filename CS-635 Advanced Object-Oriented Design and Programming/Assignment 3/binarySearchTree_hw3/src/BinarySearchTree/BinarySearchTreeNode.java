package BinarySearchTree;

public class BinarySearchTreeNode implements Node {

    private String value;
    private Node left;
    private Node right;

    public BinarySearchTreeNode(String value){
        this.value = value;
        left = new NullNode();
        right = new NullNode();
    }

    @Override
    public void add(String value) {
        if(this.value.compareTo(value)>=0){
            if(left.isNull()){
                left = new BinarySearchTreeNode(value);
            }
            else {
                left.add(value);
            }
        }
        else{
            if(right.isNull()){
                right = new BinarySearchTreeNode(value);
            }
            else{
                right.add(value);
            }
        }
    }

    @Override
    public String getValue(){
        return value;
    }

    @Override
    public Boolean isNull() {
        return false;
    }

    @Override
    public Node getRight() {
        return right;
    }

    @Override
    public Node getLeft() {
        return left;
    }

    public String TestPresent(Node node) {
        if(node.isNull())
            return "()";
//        String representFormat = "(%s (%s)(%s))";
        String representFormat = "(%s %s%s)";
        return String.format(representFormat, node.getValue(),TestPresent(node.getLeft()),TestPresent(node.getRight()));
    }
}
