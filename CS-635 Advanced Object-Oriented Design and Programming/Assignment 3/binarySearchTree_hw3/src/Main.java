import BinarySearchTree.BinarySearchTreeNode;
import BinarySearchTree.Node;
//import Visitor.BinarySearchTreeVisitor;
//import Visitor.RepresentVisitor;
import OrderStrategy.OrderContext;
import Visitor.*;

public class Main {

    public static void main(String[] args) {

//        BinarySearchTreeNode root= new BinarySearchTreeNode("5");
//        root.add("3");
//        root.add("8");
//        root.add("4");

        BinarySearchTreeNode root= new BinarySearchTreeNode("dz");
        root.add("ay");
        root.add("cx");
        root.add("bw");

        RepresentVisitor vistor = new RepresentVisitor();

        System.out.println("Binary Tree: " + root.accept(vistor));

        OrderContext orderWay = new OrderContext();
        orderWay.setOrder("Normal");
        System.out.println("Normal : " + orderWay.executeOrder(root));

        orderWay.setOrder("Reverse");
        System.out.println("Reverse: " + orderWay.executeOrder(root));
    }
}
