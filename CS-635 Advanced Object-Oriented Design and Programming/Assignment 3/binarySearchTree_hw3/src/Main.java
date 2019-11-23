import BinarySearchTree.BinarySearchTreeNode;
import Visitor.*;

import java.util.List;

class Main {

    public static void main(String[] args) {

//        BinarySearchTreeNode root= new BinarySearchTreeNode("5");
//        root.add("3");
//        root.add("8");
//        root.add("4");

//        BinarySearchTreeNode root= new BinarySearchTreeNode("dz");
//        root.add("ay");
//        root.add("cx");
//        root.add("bw");

        BinarySearchTreeNode root= new BinarySearchTreeNode("dy");
        List<String> orderNames = root.getOrderMethodName();
//        root.setOrderMethod(orderNames.get(0));
        root.add("bz");
        root.add("cw");
        root.add("ax");

        RepresentVisitor visitor = new RepresentVisitor();
        System.out.println("Normal Binary Tree: " + root.accept(visitor));

        root.setOrderMethod(orderNames.get(0));
        System.out.println("Reverse Binary Tree: " + root.accept(visitor));

        //Normal  : (dy (bz (ax ()())(cw ()()))())
        //Reverse : (dy (cw (ax ()())())(bz ()()))

//        Normal  : (dy (bz (ax ()())(cw ()()))())
//        Reverse : (dy (ax (cw ()())())(bz ()()))
    }
}
