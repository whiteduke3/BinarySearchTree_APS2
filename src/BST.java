public class BST {
    static int primerjave;
    static Node koren;
    static boolean levo;

    public BST() {
        primerjave = 0;
        koren = null;
        levo = true;
    }

    void insert(int key) {
        koren = rekInsert(koren, key);
    }

    private Node rekInsert(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        primerjave++;
        if (key < root.vrednost) {
            root.levi = rekInsert(root.levi, key);
        } else if(key > root.vrednost) {
            root.desni = rekInsert(root.desni, key);
        } else {
            root.stPojavitev += 1;
        }
        return root;
    }

    boolean find(int key) {
        Node n = rekIskanje(koren, key);
        return n != null;
    }

    private Node rekIskanje(Node root, int value) {
        if (root == null || root.vrednost == value) {
            primerjave++;
            return root;
        }
        primerjave++;
        if (root.vrednost > value) {
            return rekIskanje(root.levi, value);
        }
        return rekIskanje(root.desni, value);
    }

    void delete(int key) {
        koren = rekBrisanje(koren, key);
    }

    private Node rekBrisanje(Node root, int value) {
        if (root == null) return root;
        //primerjave++;
        if (value < root.vrednost) {
            primerjave++;
            root.levi = rekBrisanje(root.levi, value);
        } else if (value > root.vrednost) {
            primerjave++;
            root.desni = rekBrisanje(root.desni, value);
        } else {
            if (root.stPojavitev > 1) {
                root.stPojavitev--;
                return root;
            } else if (root.levi == null && root.desni == null) {
                return null;
            } else if (root.levi == null) {
                return root.desni;
            } else if (root.desni == null) {
                return root.levi;
            } else {
                if (levo) {
                    Node max = maxVrednost(root.levi);
                    int kljuc = root.vrednost;
                    int ponovitev = root.stPojavitev;
                    root.vrednost = max.vrednost;
                    root.stPojavitev = max.stPojavitev;
                    max.vrednost = kljuc;
                    max.stPojavitev = ponovitev;
                    root.levi = rekBrisanje(root.levi, max.vrednost);
                    levo = false;
                } else {
                    Node min = minVrednost(root.desni);
                    int kljuc = root.vrednost;
                    int ponovitev = root.stPojavitev;
                    root.vrednost = min.vrednost;
                    root.stPojavitev = min.stPojavitev;
                    min.vrednost = kljuc;
                    min.stPojavitev = ponovitev;
                    root.desni = rekBrisanje(root.desni, min.vrednost);
                    levo = true;
                }
            }


        }
        return root;
    }

    Node maxVrednost(Node root) {
        while (root.desni != null) {
            root = root.desni;
        }
        return root;
    }

    Node minVrednost(Node root) {
        while (root.levi != null) {
            root = root.levi;
        }
        return root;
    }

    void printInorder() {
        rekInorderIzpis(koren);
    }

    private void rekInorderIzpis(Node root) {
        if (root == null) return;
        rekInorderIzpis(root.levi);
        System.out.println(root);
        rekInorderIzpis(root.desni);
    }


    void printPreorder() {
        rekPreorderIzpis(koren);
    }

    private void rekPreorderIzpis(Node root) {
        if (root == null) return;
        System.out.println(root);
        rekPreorderIzpis(root.levi);
        rekPreorderIzpis(root.desni);
    }

    void printPostorder() {
        rekPostorderIzpis(koren);
    }

    private void rekPostorderIzpis(Node root) {
        if (root == null) return;
        rekPostorderIzpis(root.levi);
        rekPostorderIzpis(root.desni);
        System.out.println(root);
    }

    void printNodeComparisons() {
        System.out.println(primerjave);
    }
}

class Node {
    int vrednost;
    int stPojavitev;
    Node levi;
    Node desni;

    public Node(int vrednost) {
        this.stPojavitev = 1;
        this.vrednost = vrednost;
        this.desni = null;
        this.levi = null;
    }

    public String toString() {
        return String.valueOf(this.vrednost);
    }

}