package BinarySearchTree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeNodeTest {

    @Test
    void nodeState() {
        BinarySearchTreeNode testTreeNode = new BinarySearchTreeNode("C");
        assertFalse(testTreeNode.isNull());
    }

    @Test
    void binarySearchTreeCreation() {
        BinarySearchTreeNode testTreeNode = new BinarySearchTreeNode("B");
        testTreeNode.add("A");
        testTreeNode.add("C");
        assertEquals("B",testTreeNode.getValue());
        assertEquals("C",testTreeNode.getRight().getValue());
        assertEquals("A",testTreeNode.getLeft().getValue());
    }

    @Test
    void orderMethodNames() {
        BinarySearchTreeNode testTreeNode = new BinarySearchTreeNode("B");
        List<String> orderMethodNames = testTreeNode.getOrderMethodNames();
        assertEquals(orderMethodNames.get(0), "Reverse");
        assertEquals(orderMethodNames.get(1), "Normal");
    }

    @Test
    void orderMethodSetting() {
        BinarySearchTreeNode testTreeNode = new BinarySearchTreeNode("BY");
        List<String> orderMethodNames = testTreeNode.getOrderMethodNames();
        //Normal Order
        testTreeNode.setOrderMethod(orderMethodNames.get(1));
        testTreeNode.add("AZ");
        testTreeNode.add("CW");
        assertEquals("CW",testTreeNode.getRight().getValue());
        assertEquals("AZ",testTreeNode.getLeft().getValue());

        //Change the order method to "Reverse". It triggers rebuilding
        testTreeNode.setOrderMethod(orderMethodNames.get(0));
        assertEquals("AZ",testTreeNode.getRight().getValue());
        assertEquals("CW",testTreeNode.getLeft().getValue());
    }
}