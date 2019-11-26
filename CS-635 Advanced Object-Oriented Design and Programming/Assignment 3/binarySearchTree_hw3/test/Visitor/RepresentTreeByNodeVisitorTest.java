package Visitor;

import BinarySearchTree.BinarySearchTreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepresentTreeByNodeVisitorTest {

    @Test
    void representNode() {
        BinarySearchTreeNode root= new BinarySearchTreeNode("day");
        root.add("bfz");
        root.add("chw");
        root.add("aex");

        RepresentTreeByNodeVisitor testRepresentTreeByNodeVisitor = new RepresentTreeByNodeVisitor();
        String expectRepresent = "(day (bfz (aex ()())(chw ()()))())";
        assertEquals(expectRepresent, testRepresentTreeByNodeVisitor.representTree(root));

        root.add("g");
        root.add("h");
        root.add("e");

        expectRepresent = "(day (bfz (aex ()())(chw ()()))(g (e ()())(h ()())))";
        assertEquals(expectRepresent, testRepresentTreeByNodeVisitor.representTree(root));
    }
}