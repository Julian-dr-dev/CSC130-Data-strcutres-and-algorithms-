package HeapProject;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;
public class heapDriver {
    public static void main(String[] args) throws FileNotFoundException, IOException

    {
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();


        File file = new File("myTextInput.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st ;

        String[] sArr = new String[100];


        int[] iArr = new int[100];
        while ((st = br.readLine()) != null)

        {

             sArr = st.split(",");

        }

        for(int i=0;i<sArr.length;i++)

        {

            iArr[i] = Integer.parseInt(sArr[i]);

            pQueue.add(iArr[i]);

        }


        System.out.println("Status of heap:");


        int count = 1;


        Iterator it1 = pQueue.iterator();

        while (it1.hasNext())

        {

            System.out.println("Level " + (int)Math.floor(Math.log(count)/Math.log(2)) + ": " + it1.next());
            count++;

        }

        int itrCount = 1;

        while(itrCount <= 5)

        {

            pQueue.poll();

            System.out.println("After removing " + itrCount + " root element(s): ");
            System.out.println("Status of heap:");

            Iterator<Integer> it2 = pQueue.iterator();
            count = 1;

            while (it2.hasNext())

            {
                System.out.println("Level " + (int)Math.floor(Math.log(count)/Math.log(2)) + ": " + it2.next());
                count++;
            }
            itrCount++;

        }

    }

}






