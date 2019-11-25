package BinarySearchTree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NullNodeTest {

    @Test
    void isNull() {
        NullNode testNullNode = new NullNode();
        assertTrue(testNullNode.isNull());
    }

    @Test
    void nonImplementFunctions() {
        NullNode testNullNode = new NullNode();
        assertThrows(RuntimeException.class, ()-> testNullNode.add(""));
        assertThrows(RuntimeException.class, ()-> testNullNode.setNodeState(false));
        assertThrows(RuntimeException.class, ()-> testNullNode.setOrderMethod(""));
    }

    @Test
    void returnNullFunctions() {
        NullNode testNullNode = new NullNode();
        assertNull(testNullNode.getLeft());
        assertNull(testNullNode.getRight());
        assertNull(testNullNode.getValue());
        assertNull(testNullNode.getOrderMethodNames());
        assertNull(testNullNode.accept(null));
    }
}