public class BinaryTreeApp {

    public static void main(String[] args){
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        System.out.println("In-order: ");
        bt.traverseInOrder(bt.root);
        System.out.println();

        System.out.println("Pre-order: ");
        bt.traversePreOrder(bt.root);
        System.out.println();

        System.out.println("Post-order: ");
        bt.traversePostOrder(bt.root);
        System.out.println();

        System.out.println("Level-oder(Breadth-First): ");
        bt.traverseBreadthFirst();
    }
}
