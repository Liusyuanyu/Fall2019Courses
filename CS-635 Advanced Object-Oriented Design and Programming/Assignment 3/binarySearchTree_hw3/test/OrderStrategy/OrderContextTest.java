package OrderStrategy;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderContextTest {

    @Test
    void orderMethodName() {
        OrderContext testOrder = new OrderContext();
        List<String> orderMethodNames = testOrder.getOrderMethodNames();
        assertEquals(orderMethodNames.get(1),testOrder.getCurrentMethodName());

        testOrder.setOrderMethod(orderMethodNames.get(0));
        assertEquals(orderMethodNames.get(0),testOrder.getCurrentMethodName());
    }

    @Test
    void compare(){
        OrderContext testOrder = new OrderContext();
        List<String> orderMethodNames = testOrder.getOrderMethodNames();

        //Normal order Test
        assertTrue(testOrder.compare("bx","az"));
        assertFalse(testOrder.compare("aaa","bbb"));
        assertTrue(testOrder.compare("babcd","azzzz"));


        //Reverse order Test
        testOrder.setOrderMethod(orderMethodNames.get(0));
        assertFalse(testOrder.compare("bx","az"));
        assertTrue(testOrder.compare("aab","bba"));
        assertFalse(testOrder.compare("babcd","azzzz"));
    }
}