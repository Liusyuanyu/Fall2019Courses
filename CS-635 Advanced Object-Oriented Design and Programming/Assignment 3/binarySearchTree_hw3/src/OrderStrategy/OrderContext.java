package OrderStrategy;
import BinarySearchTree.Node;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class OrderContext {
    private OrderStrategy strategy;
    private Map<String,OrderStrategy> orderMap;

    public OrderContext(){
        orderMap = new HashMap<>();
        orderMap.put("Normal",(String value1, String value2)->{
            return  value1.compareTo(value2)>=0;
        });
        orderMap.put("Reverse",(String value1, String value2)->{
            String reverseValue1 = new StringBuilder(value1).reverse().toString();
            String reverseValue2 = new StringBuilder(value1).reverse().toString();
            return  reverseValue1.compareTo(reverseValue2)>=0;
        });
    }
    public Boolean executeOrder(String value1, String value2)
    {
        return this.strategy.execute(value1,value2);
    }

//    public OrderContext(){
//        orderMap = new HashMap<>();
//        orderMap.put("Normal",(Node node)->{
//            List<String> valueList=new ArrayList<String>();
//            inOrder(node,valueList);
//            return String.join(" | ", valueList);
//        });
//
//        orderMap.put("Reverse",(Node node)->{
//            List<String> valueList=new ArrayList<String>();
//            inOrder(node,valueList);
//            valueList.sort(  Comparator.comparing(element->new StringBuilder(element).reverse().toString()));
//            return String.join(" | ",valueList);
//        });
//    }
//    public String executeOrder(Node node)
//    {
//        return this.strategy.execute(node);
//    }

//    public List<String> getOrderingWay()
//    {
//        return new ArrayList<>(orderMap.keySet());
//    }
//    public void setOrder(String order)
//    {
//        if(!orderMap.containsKey(order))
//        {
//            throw new UnsupportedOperationException();
//        }
//        this.strategy = orderMap.get(order);
//    }
//
//    private void inOrder(Node node,List<String> valueList){
//        if(node.isNull()){
//            return;
//        }
//        inOrder(node.getLeft(),valueList);
//        valueList.add(node.getValue());
//        inOrder(node.getRight(),valueList);
//    }
//
//    private String rebuildByReversingString(Node node){
//        List<String> valueList = new ArrayList<>();
//        inOrder(node,valueList);
//        valueList.sort(  Comparator.comparing(element->new StringBuilder(element).reverse().toString()));
//        return String.join(" | ",valueList);
//    }

}
