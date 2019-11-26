package Visitor;

import BinarySearchTree.BinarySearchTreeNode;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RepresentTreeByInOrderVisitorTest {

    @Test
    void representTree() {
        BinarySearchTreeNode root= new BinarySearchTreeNode("day");
        List<String> orderNames = root.getOrderMethodNames();
        root.add("bfz");
        root.add("chs");
        root.add("eht");
        root.add("ihu");
        root.add("aex");
        RepresentTreeByInOrderVisitor testInorderVisitor = new RepresentTreeByInOrderVisitor();
        String expectRepresent = "aex-bfz-chs-day-eht-ihu";
        assertEquals(expectRepresent, testInorderVisitor.representTree(root));

        testInorderVisitor.setEliminator("|");
        expectRepresent = "aex|bfz|chs|day|eht|ihu";
        assertEquals(expectRepresent, testInorderVisitor.representTree(root));
    }
}