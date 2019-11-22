import BinarySearchTree.BinarySearchTreeNode;

public class Main {

    public static void main(String[] args) {

        BinarySearchTreeNode root= new BinarySearchTreeNode("5");
        root.add("3");
        root.add("8");
        root.add("4");

        System.out.println(root.TestPresent(root));
    }
}
