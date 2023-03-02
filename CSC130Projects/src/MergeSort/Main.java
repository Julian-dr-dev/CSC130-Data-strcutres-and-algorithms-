package MergeSort;

public class Main {

    public static void main(String[] args)
    {
        int[] randoArr = {2, 8, 5, 3, 9, 4, 1, 7};

        mergeSort l1 = new mergeSort(randoArr);

        System.out.println("Orginal List: ");
        l1.toString(l1.head);

        System.out.println("Sorted List: ");
        l1.toString(l1.sorting(l1.head));


    }
}

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

}

class mergeSort {
    public Node head;
    private Node tail;



    public mergeSort(int[] unsortedArr)
    {
        for (int i : unsortedArr)
        {
            if (head == null)
            {
                Node newNode = new Node(i);
                head = newNode;
                tail = newNode;
            }
            else
            {
                Node newNode = new Node(i);
                tail.next = newNode;
                tail = newNode;

            }
        }

    }
    public void toString(Node node)
    {
        System.out.print("[ ");
        for (; node.next!= null; node = node.next)
        {
            System.out.print(node.data + ", ");
        }
        System.out.println(" ]");

    }

    public Node sorting(Node curr)
    {
        if (curr == null || curr.next == null)
        {
            return curr;
        }
        Node mid = getMid(curr);
        Node midNxt = mid.next;
        mid.next = null;

        Node left = sorting(curr);
        Node right = sorting(midNxt);
        Node list = merge(left, right);

        return list;


    }
    private Node merge(Node left, Node right)
    {
        //if left is null return right if right is null return left
        if (left == null) {
            return right;
        }
        else if (right == null) {
            return left;
        }

        Node myList = null;

        if (left.data <= right.data) {
            myList = left;
            myList.next = merge(left.next, right);
        } else if (left.data > right.data)
        {
            myList = right;
            myList.next = merge(left, right.next);
        }
        return myList;
    }

    private Node getMid(Node curr)
    {

        if (curr == null) {
            return curr;
        }

        Node midNode = curr;

        for (Node endNd = curr.next; endNd != null; endNd = endNd.next)
        {
            // if endNde next is not null
            if (endNd.next != null)
            {
                midNode = midNode.next;
                endNd = endNd.next;
            }

        }
        return midNode;

    }

}
