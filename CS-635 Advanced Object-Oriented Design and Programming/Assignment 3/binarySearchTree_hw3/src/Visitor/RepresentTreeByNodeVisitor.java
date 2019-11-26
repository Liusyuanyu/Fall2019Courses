package Visitor;

import BinarySearchTree.Node;

public class RepresentTreeByNodeVisitor implements BinaryTreeRepresentationVisitor {

    private String representFormat;

    public RepresentTreeByNodeVisitor(){
        representFormat = "(%s %s%s)";
    }

    @Override
    public String representTree(Node node) {
        if(node.isNull()){
            return "()";
        }
        return String.format(representFormat, node.getValue(), representTree(node.getLeft()), representTree(node.getRight()));
    }
}
