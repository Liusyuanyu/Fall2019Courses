package BinarySearchTree;

public class NullNode implements Node{

    public NullNode(){}
    @Override
    public void add(String value) {}

    @Override
    public String getValue(){
        return null;
    }

    @Override
    public Boolean isNull(){
        return true;
    }

    @Override
    public Node getRight() {
        return null;
    }

    @Override
    public Node getLeft() {
        return null;
    }
}
