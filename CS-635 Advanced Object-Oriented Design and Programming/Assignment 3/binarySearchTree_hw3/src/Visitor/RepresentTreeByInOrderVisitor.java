package Visitor;

import BinarySearchTree.Node;

public class RepresentTreeByInOrderVisitor implements BinaryTreeRepresentationVisitor {
    private String eliminator;

    public RepresentTreeByInOrderVisitor(){
        eliminator = "-";
    }

    public void setEliminator(String eliminator) {
        this.eliminator = eliminator;
    }

    @Override
    public String representTree(Node node) {
        if(node.isNull()){
            return null;
        }
        String representation = null;
        String leftSubTree = representTree(node.getLeft());
        String rightSubTree = representTree(node.getRight());
        if(leftSubTree!=null){
            representation = String.join(eliminator,leftSubTree,node.getValue());
        }
        else{
            representation = node.getValue();
        }
        if(rightSubTree!=null){
            representation = String.join(eliminator,representation,rightSubTree);
        }
        return representation;
    }
}
