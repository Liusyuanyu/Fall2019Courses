package OrderStrategy;

import java.util.*;

public class OrderContext {
    private OrderStrategy strategy;
    private Map<String,OrderStrategy> orderMap;
    private String currentMethodName;
    public OrderContext(){
        orderMap = new HashMap<>();
        orderMap.put("Normal",(String nodeValue,String addValue)->{
            return  nodeValue.compareTo(addValue)>=0;
        });
        orderMap.put("Reverse",(String nodeValue,String addValue)->{
            String reverseNodeValue = new StringBuilder(nodeValue).reverse().toString();
            String reverseAddValue = new StringBuilder(addValue).reverse().toString();
            return  reverseNodeValue.compareTo(reverseAddValue)>=0;
        });

        strategy = orderMap.get("Normal");
        currentMethodName = "Normal";
    }

    public void setOrderMethod(String orderMethodName)
    {
        if(!orderMap.containsKey(orderMethodName))
        {
            throw new Error();
        }
        strategy = orderMap.get(orderMethodName);
        currentMethodName = orderMethodName;
    }
    public List<String> getOrderMethodName()
    {
        return new ArrayList<>(orderMap.keySet());
    }
    public Boolean compare(String nodeValue, String addValue)
    {
        return this.strategy.compare(nodeValue,addValue);
    }
    public String getCurrentMethodName(){
        return currentMethodName;
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
//    public String compare(Node node)
//    {
//        return this.strategy.compare(node);
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
