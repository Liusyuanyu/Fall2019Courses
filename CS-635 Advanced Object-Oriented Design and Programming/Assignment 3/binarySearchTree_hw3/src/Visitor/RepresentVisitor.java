package Visitor;

import BinarySearchTree.Node;

public class RepresentVisitor implements BinarySearchTreeVisitor {

    private String representFormat;

    public RepresentVisitor(){
        representFormat = "(%s %s%s)";
    }

    @Override
    public String representNode(Node node) {
        if(node.isNull())
            return "()";
        return String.format(representFormat, node.getValue(),representNode(node.getLeft()),representNode(node.getRight()));
    }
}
