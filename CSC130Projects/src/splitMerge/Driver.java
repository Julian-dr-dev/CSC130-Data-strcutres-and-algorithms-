package splitMerge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Node {

    private String data;
    Node next;
    private Node head;

    //constructor
    public Node(String data)
    {
        this.data = data;
    }

    //getters and setters
    public String getData()
    {
        return this.data;
    }
    public void setData(String data)
    {
        this.data = data;
    }

    public Node getNext()
    {
        return this.next;
    }
    public void setNext(Node next)
    {
        this.next = next;
    }
}


  class Llist {
      private Node head;

      //inserts node at the beggining of the linked list
      public void insert(String newData) {

          //construct new node with string data
          Node temp = new Node(newData);
          //set new nodes next to head
          temp.setNext(head);
          //stroe new node in head
          head = temp;
      }


      //adds a node at the end of the list
      public void add(String newData) {
          Node temp = new Node(newData);
          Node curr = head;

          //sets head to new node with newdata if list is empty
          if (head == null) {
              head = temp;
          } else {
              for (; curr.getNext() != null; curr = curr.getNext()) {
              }
              curr.setNext(temp);
          }
      }

      public void traverse() {
          if (head == null) {
              System.out.println("List is empty.");
          } else {
              Node curr = head;
              System.out.println("[");
              for (; curr.getNext() != null; curr = curr.getNext()) {
                  System.out.println(curr.getData() + ", ");

              }
              System.out.println(curr.getData() + "]");
          }
      }


      public Llist split() {
          int count = 0;
          for (Node size = head; size != null; size = size.getNext()) {
              count++;
          }
          int half = count / 2;

          //two seperate Linked list to split one into both
          Llist l1 = new Llist();
          Llist l2 = new Llist();
          Node curr = head;

          for (int i = 0; i < count; i++) {

              if (i < half) {
                  l1.add(curr.getData());
              } else {
                  l2.add(curr.getData());
              }
              curr = curr.getNext();
          }
          head = l1.head;
          return l2;
      }


      public void merge(Llist myList2) {
          for (; myList2.head != null; myList2.head = myList2.head.getNext()) {
              add(myList2.head.getData());
          }
      }

      public void remove(String value) {
          if (head != null) {
              if (head.getData() == value) {
                  head = head.next;
              } else {
                  Node curr = head;
                  while (curr.next != null) {
                      if (curr.next.getData() == value) {
                          curr.next = curr.next.next;
                      }
                      curr = curr.next;
                  }}}}}





//-----------------------Driver-----------------------
public class Driver {
    public static void main(String[] args) {

        try {
            Llist myList0 = new Llist();

            //read in file contents for the new list
            Scanner sc0 = new Scanner(new File("myFile.txt"));

            //add names of file to myList0
            while (sc0.hasNextInt()) {
                myList0.add(sc0.next());
            }
            System.out.println("First Linked List: ");
            myList0.traverse();


            Llist myList1 = new Llist();

            Scanner sc1 = new Scanner((new File("file.txt")));

            while (sc1.hasNextInt()) {
                myList1.add(sc1.next());
            }
            //split the list
            Llist myList2 = myList1.split();


            //display both lists
            System.out.println("\nMylist1 linked list -->");
            myList1.traverse();

            System.out.println("\nmyList2 linked list -->");
            myList2.traverse();

            //merge both lists
            myList1.merge(myList2);

            //merged link list
            System.out.println("\nMerged linked lists: ");
            myList1.traverse();


        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }

        //


        //data members

    }
}






