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
            throw new Error("No such of order method name.");
        }
        strategy = orderMap.get(orderMethodName);
        currentMethodName = orderMethodName;
    }
    public List<String> getOrderMethodNames()
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

}
