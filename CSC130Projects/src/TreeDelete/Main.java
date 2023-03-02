package TreeDelete;

import java.io.File;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException
    {
        PrintStream con = new PrintStream(new File("bigOutput.txt"));
        System.setOut(con);

        Tree tree = new Tree("input.txt");
        tree.toString();

        con.close();
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int val)
    {
        this.val = val;
        left = null;
        right = null;
    }

}

class Tree {

    private Node root;
    public Tree(String file) throws FileNotFoundException
    {
        //ssacanner with new file of name input.txt passed in
        Scanner sc = new Scanner(new File("TreeDelete/ouput.txt"));

        //while scanner ibj has valid next line
        while (sc.hasNextLine()) {
            //set string named line to the scanner object nextline
            String line = sc.nextLine();

            if (line.startsWith("delete")) {
                String curr = line.substring("delete ".length());
                delete(Integer.parseInt(curr));
            }
            else
            {
                root = insert(root, Integer.parseInt(line));
            }

        }
    }


    private void delete(int val)
    {
        if (search(root, val) != null)
        {
            root = removing(root, val);
        }
        else
        {
            insert(root, val);
        }
    }

    private Node search(Node root, int val)
    {
        if (root == null || root.val == val)
        {
            return root;
        }
        else if (val < root.val)
        {
            return search(root.left, val);
        }
        else {
            return search(root.right, val);
        }
    }

    private Node removing(Node root, int val) {
        if (root == null) { return root; }

        else if (val < root.val) {
            removing(root.left, val);
        }

        else if (val > root.val) {
            removing(root.right, val);
        }

        else {
            if (root.left == null) { return root.right; }
            else if (root.right == null) { return root.left; }

            root.val = minVal(root.right);
            root.right = removing(root.right, root.val);
        }
      return root;
    }

    private int minVal(Node right)
    {
        int min = right.val;

        while (root.left != null) {
            min = root.left.val;
            root = root.left;
        }
        return min;
    }


    private Node insert(Node node, int value)
    {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        else if (value < root.val)
        {
            root.left = insert(root.left, value);
        }
        else if (value > root.val)
        {
            root.right = insert(root.right, value);
        }
        return root;

    }

    public void toStrings()
    {
        System.out.println("Preorder traversal: ");
        preorder(root);
        System.out.println("inorder traversal: ");
        preorder(root);
        System.out.println("Postorder traversal: ");
        postorder(root);

    }

    private void preorder(Node root) {

        if (root != null) {
            System.out.println(root.val + " | ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    private void inorder(Node root) {

        if (root != null) {
            inorder(root.left);
            System.out.println(root.val + " | ");
            inorder(root.right);
        }
    }

    private void postorder(Node root) {

        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.println(root.val + " | ");
        }
    }

    private void level(Node root) {

        int depth = maxDepth(root) + 1;
        for (int level = 1; level <= depth; level++)
        {
            displayLevel(root, level);
            System.out.println();
        }

    }

    private int maxDepth(Node root) {

        if (root == null) { return -1; }

        int leftDeep = maxDepth(root.left);
        int rightDeep = maxDepth(root.right);

        if (leftDeep > rightDeep) {
            return leftDeep + 1;
        }
        else {
            return rightDeep + 1;
        }
    }

    private void displayLevel(Node root, int level) {

        if (root == null) {
            System.out.println();
            return;

        }
        else if (level == 1) {
            System.out.println(root.val + " | ");

        }
        else if (level > 1) {
            displayLevel(root.left, level - 1);
            displayLevel(root.right, level - 1);

        }
    }




}
