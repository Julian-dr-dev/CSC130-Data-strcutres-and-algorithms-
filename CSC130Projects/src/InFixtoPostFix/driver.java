package InFixtoPostFix;
import java.util.Scanner;
import java.util.*;

import static InFixtoPostFix.Postfix.evaluate;

public class driver {

    public static void main(String[] args)
    {
        //setting up user input:
        Scanner input = new Scanner(System.in);
        System.out.println("Please provide expression: ");
        String expression = input.nextLine();

        String postfixExpress = Postfix.infixToPost(expression);

        System.out.println("Postfix expression: " + postfixExpress);

        System.out.println("Answer: " + evaluate(postfixExpress));
    }
}


class Postfix {
    public static String infixToPost(String expression)
    {
        Stack<Character> stack = new Stack<Character>(); //storing operators
        Queue<Character> queue = new Queue<Character>(); //storing
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                queue.add(c);
            }
            else if (c =='(') {
                stack.push(c);
            }
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                {
                    queue.add(stack.pop());
                }
                stack.pop();
            }
            else
            {
               while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c))
               {
                   queue.add(stack.pop());
               }
               stack.push(c);
            }
        }

        while (!stack.isEmpty())
        {
            queue.add(stack.pop());
        }

        String res = "";

        while(!queue.isEmpty())
        {
            res += queue.remove();
        }
        return res;
    }

    //helper for infixToPostfix method
    public static int precedence(char c) {

        switch(c) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    public static int evaluate(String postfixExpress)
    {
        Stack<Integer> storage = new Stack<Integer>();



        for (int i = 0; i< postfixExpress.length(); i++)
        {
            char c = postfixExpress.charAt(i);


            if (Character.isDigit(c))
            {
                storage.push(Character.getNumericValue(c));
            }
            else
            {
                if (storage.size() > 1)
                {
                    int right = storage.pop();
                    int left = storage.pop();
                    storage.push(solve(left, c, right));
                }
            }
        }
        return storage.pop();
    }



    public static Integer solve(int l, char op, int r)
    {
        int res = 0;

        if (op == '+') {
            res = l + r;
        }

        else if (op == '-')
        {
            res = l - r;
        }
        else if (op == '*')
        {
            res = l * r;
        }
        else if (op == '/')
        {
            res = l / r;
        }
        return res;

    }
}

class ListNode<T>
{
    T data;
    ListNode<T> next;

    public ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }
}

class stack<T> {
    private ListNode<T> head;
    private int size;

    //constructor
    public stack() {
        head = null;
        size = 0;
    }

    public boolean isEmpty()
    {
        return (size == 0);
    }

    //peek head/topOfStack data
    public T peek()
    {
        return head.data;
    }

    public T pop() {
        ListNode<T> temp = head;
        head = head.next;
        size--;
        return temp.data;
    }

    //create push method with T data passed in
    public void push(T data)
    {
        ListNode<T> temp = new ListNode<T>(data, null);
        temp.next = head;
        head = temp;
        size++;
    }

    public int size()
    {
        return size;
    }
}


class Queue<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public Queue() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T data)
    {
        ListNode<T> temp = new ListNode<T>(data, null);

        if (head == null && tail == null)
        {
            head = temp;
            tail = temp;
        }
        else {
            tail.next = temp;
            tail = temp;
        }
    }

    public boolean isEmpty()
    {
        return (size == 0);
    }

    public T remove()
    {
        size--;
        T temp = head.data;
        head = head.next;
        return temp;

    }





}
