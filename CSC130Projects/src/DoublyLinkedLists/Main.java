package DoublyLinkedLists;


import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args)
    {

        try {
            Scanner file = new Scanner(new File("myFile"));
            Doubly list = new Doubly();

            while (file.hasNext()) {
                String name = file.next();
                if (name.equals("delete"))
                {
                    name = file.next();
                    list.delete(name);
                } else
                {
                    list.insert(name);
                }

                PrintStream output = new PrintStream(new File("output.txt"));
                System.setOut(output);
                list.traverseAscending();
                System.out.println("==============");
                list.traverseDescending();
                System.setOut(output);
                output.close();
            }
            file.close();


        } catch (FileNotFoundException e)
        {
            System.out.println("File not found!");

        }



    }
}
class Node {
    private String data;
    private Node next;
    private Node prev;

    public Node(String data) { this.data = data;
    }

    public String getData() { return data;
    }

    public void setData(String data) { this.data = data;
    }

    public Node getNext() { return next;
    }

    public void setNext(Node next) { this.next = next;
    }

    public Node getPrev() { return prev;
    }

    public void setPrev(Node prev) { this.prev = prev;
    }
}


class Doubly {
    private Node head;
    private Node tail;
    private int size;

    public void delete(String name) {
        for (Node curr = head; curr != null; curr = curr.getNext()) {
            if (name.compareToIgnoreCase(curr.getData()) == 0) {
                break;
            } else if (curr == null) {
                return;
            }

        }
        Node temp = head;
        if (name.compareToIgnoreCase(head.getData()) == 0) {
            temp.getNext().setPrev(null);
            head = head.getNext();
        } else {
            for (; name.compareToIgnoreCase(temp.getData()) != 0; temp = temp.getNext()) {
            }
            //if tail
            if (temp.getNext() == null) {
                tail = tail.getPrev();
                tail.setNext(null);

            } else {
                temp.getPrev().setNext(temp.getNext());
                temp.getNext().setPrev(temp.getPrev());
            }

        }


    }

    public void insert(String name) {
        //current node
        Node curr = new Node(name);

        //next and previous holders for curr
        Node currNxt = head;
        Node currPrev = head;
        if (head == null) {
            head = curr;
            tail = head;
            size = 1;
        } else if (curr.getData().compareToIgnoreCase(head.getData()) < 0) {
            curr.setNext(head);
            head.setPrev(curr);
            head = curr;
            curr.setPrev(null);
        } else if (curr.getData().compareToIgnoreCase(tail.getData()) > 0) {
            curr.setPrev(tail);
            tail.setNext(curr);
            tail = curr;
            curr.setNext(null);
        } else {

            for (Node temp = head; temp.getData().compareToIgnoreCase(curr.getData()) < 0; temp = temp.getNext()) {
                currNxt = curr.getNext();
                currPrev = curr.getPrev();
            }
            currNxt = currNxt.getNext();
            currNxt.setPrev(curr);
            currPrev.setNext(curr);
            curr.setPrev(currPrev);
            curr.setNext(currNxt);


        }
    }

        public void traverseAscending()
        {
            for (Node iter = head; iter != null; iter.getNext()) {
                System.out.println(iter.getData());
            }

        }
        public void traverseDescending()
        {
            for (Node iter = tail; iter != null; iter.getPrev()) {
                System.out.println(iter.getData());
            }

        }


}