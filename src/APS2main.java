public class APS2main {
    public static void main(String[] args) {
        //TEST 5
        BST b = new BST();
        b.insert(29);
        b.insert(15);
        b.insert(43);
        b.insert(7);
        b.insert(23);
        b.insert(35);
        b.insert(51);
        b.delete(29);
        b.printPreorder();
        b.printNodeComparisons();

    }
}
