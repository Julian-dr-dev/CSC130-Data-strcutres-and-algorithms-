package RadixSort;

import java.util.Queue;

public class Driver {
    public static void main(String[] args)
    {
        int[] arr = {736, 81, 101, 28, 35, 66, 92, 5, 273};

        int[] arrCopy = radix.copy(arr, arr.length);

        int digitLength = radix.getMaxLength(arr, arr.length);

        radix.sortAsc(arr, digitLength);

        arr = arrCopy;

        radix.sortDesc(arr, digitLength);

    }
}

class Node {
    public int data;
    public Node next;

    public Node(int data)
    {
        this.data = data;
    }

}

class queue {
    private Node head, tail;
    //no paramater isEmpty() method
    public boolean isEmpty()
    {
        if (head == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void enq(int val)
    {
        //create new Node object from int val
        Node newNode = new Node(val);

        if (head == null)
        {
            head = newNode;
            tail = newNode;
        }

        else
        {
            Node curr = head;

            while (curr.next != null)
            {
                curr = curr.next;
            }
            curr.next = newNode;
            newNode.next = null;
        }


    }


    public int dequeue()
    {
        int data = head.data;
        head = head.next;

        if (head == null)
        {
            tail = null;
        }
        return data;
    }

}

class radix {

    public static int[] copy(int[] arr, int length) {
        int[] temp = new int[length];

        for (int i = 0; i < length; i++)
        {
            temp[i] = arr[i];
        }
        return temp;
    }

    public static int getMaxLength(int[] arr, int length)
    {
        int max = arr[0];
        for (int i = 1; i < length; i++)
        {
            if (max < arr[i])
            {
                max = arr[i];
            }
        }

        int maxDigit = 1;
        for (int j = max; j > 1; j /= 10) {
            maxDigit = maxDigit * 10;
        }

        maxDigit /= 10;
        return maxDigit;
    }

    public static void sortAsc(int[] arr, int digitLength)
    {
        System.out.println("Ascending sorted: ");
        queue[] qArr = new queue[10];

        for (int i = 0; i < 10; i++) {
            qArr[i] = new queue();
        }

        for (int powerTen = 1; powerTen <= digitLength; powerTen *= 10) {

            for (int i = 0; i < arr.length; i++) {
                int digit = getDigit(arr[i], powerTen);
                qArr[digit].enq(arr[i]);
            }
            int item = 0;

            for (int qNum = 0; qNum < 10; qNum++) {
                for(; !qArr[qNum].isEmpty(); item++) {
                    arr[item] = qArr[qNum].dequeue();
                }
            }
        }
        toString(arr);
    }


//-------------------------------------------
    public static void sortDesc(int[] arr, int digitLength)
    {
        System.out.println();
        System.out.println("Descending sorted ");

        queue[] qArr = new queue[10];

        for (int i = 0; i < 10; i++) {
            qArr[i] = new queue();
        }

        for (int powerTen = 1; powerTen <= digitLength; powerTen *= 10)
        {
            for (int i = 0; i < arr.length; i++)
            {
                int digit = getDigit(arr[i], powerTen);
                qArr[digit].enq(arr[i]);
            }
            int item = 0;

            for (int qNum = 9; qNum >= 0; qNum--) {
                for (; !qArr[qNum].isEmpty(); item++)
                {
                    arr[item] = qArr[qNum].dequeue();
                }
            }
        }
        toString(arr);

    }

    private static int getDigit(int i, int powerTen) {
        return (i / powerTen) % 10;
    }


    private static void toString(int[] arr) {
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + ", ");

        }
        System.out.println();
    }
}