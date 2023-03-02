package AVLTree;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.io.File;

public class AVLmain {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        try {
            tree.insert("input.txt").print();
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
    }
}

class Node {
    public int val;
    public int height;
    public Node left;
    public Node right;

    public Node(int val)
    {
        this.val = val;
        height = 1;
    }
}

class AVLTree {
    private Node root;
    public AVLTree insert(String filename) throws FileNotFoundException
    {
        AVLTree arbol = new AVLTree();
        Scanner file = new Scanner(new File(filename));

        while (file.hasNext())
        {
            arbol.insert(file.next());
        }
    return arbol;
    }

    private void insert(int newInt)
    {
        root = insert(root, newInt);
    }


    private Node insert(Node curr, int newInt)
    {
        if (root == null) { return new Node(newInt); }

        if (newInt < curr.val) { curr.left = insert(curr.left, newInt); }

        else if (newInt > curr.val) { curr.right = insert(curr.right, newInt); }

        //set curr's height to 1 plus the max of the left height and right height
        curr.height = 1 + max(height(root.left), height(root.right));

        int balance = getBalance(curr);

        if (balance > 1 && newInt < curr.left.val) { return rightRotate(curr); }
        else if (balance > 1 && newInt > curr.left.val) {
            root.left = leftRotate(root.left);
            return rightRotate(curr);
        }


        else if (balance < -1 && newInt > curr.right.val)
        {
            return leftRotate(curr);
        }
        else if (balance < -1 && newInt <  curr.right.val)
        {
            root.right = rightRotate(root.left);
            return leftRotate(curr);
        }

        return curr;
        
    }

    private int getBalance(Node root)
    {
        return (root == null) ? 0 : height(root.left) - height(root.right);
    }

    public void print() {
        Node node = root;

        if (node == null) { return; }

        Queue<Node> q = new LinkedList<>();
        q.add(node);
        q.add(null);

        while (!q.isEmpty()) {

            Node nodeN = q.poll();

            if (nodeN == null) {
                if (!q.isEmpty())
                {
                    q.add(null);
                    System.out.println();
                }
            }

            else
            {
                if (nodeN.left != null) { q.add(nodeN.left); }

                if (nodeN.right != null) { q.add(nodeN.right); }
                System.out.print("( " + nodeN.val + ", " + nodeN.height + ", " + getBalance(nodeN));
            }
        }

    }



    private Node leftRotate(Node curr)
    {
        Node r = curr.right;
        Node l = curr.right.left;

        r.left = curr;
        curr.right = l;

        curr.height = max(height(curr.left), height(curr.right)) + 1;

        r.height = max(height(curr.left), height(curr.right)) + 1;

        return r;
    }

    private Node rightRotate(Node curr)
    {
        Node l = curr.left;
        Node r = curr.left.right;

        l.right = curr;

        curr.left = r;
        curr.height = max(height(curr.left), height(curr.right));

        l.height = max(height(l.left), height(l.right)) + 1;
        return l;
    }





    private int max(int leftHeight, int rightHeight) {
        
        if (leftHeight > rightHeight) {
            return leftHeight;
        }
        else { return rightHeight; }
    }

    private int height(Node n) {
        if (n == null) {
            return 0;
        }
        else {
            return n.height;
        }
    }


}


