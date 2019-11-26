import BinarySearchTree.BinarySearchTreeNode;
import Visitor.*;

import java.util.List;

class Main {

    public static void main(String[] args) {

        BinarySearchTreeNode root= new BinarySearchTreeNode("day");
        List<String> orderNames = root.getOrderMethodNames();
        root.add("bfz");
        root.add("chs");
        root.add("eht");
        root.add("ihu");
        root.add("aex");

        RepresentTreeByNodeVisitor visitor = new RepresentTreeByNodeVisitor();
        RepresentTreeByInOrderVisitor inOrderVisitor = new RepresentTreeByInOrderVisitor();

        System.out.println("Normal Binary Tree: " + root.accept(visitor));
        System.out.println("Normal Inorder Binary Tree: " + root.accept(inOrderVisitor));

        root.setOrderMethod(orderNames.get(0));
        System.out.println("Reverse Binary Tree: " + root.accept(visitor));
        System.out.println("Reverse Inorder Binary Tree: " + root.accept(inOrderVisitor));
    }
}
