package SortSearch;

import java.util.Scanner;

public class Driver {

    public static int[][] arr = {
            {5, 3, 2, 16},
            {9, 8, 10, 17},
            {4, 7, 11, 18},
            {2, 5, 9, 12},
            {7, 9, 4, 10}
    };
    public static void main(String[] args)
    {
        Sorting sort = new Sorting();
        //store copy of original array in temp
        int temp[][] = sort.copy(arr);

        //print original array
        System.out.println("Original array: ");
        sort.toString(arr);

        //display bubble
        sort.bubbleSort(arr);
        arr = temp;

        //display selection
        sort.selectionSort(arr);
        arr = temp;

        //display shell
        temp = sort.copy(arr);
        sort.shellSort(arr);
        arr = temp;

        //display insertion
        temp = sort.copy(arr);
        sort.insertionSort(arr);
        arr = temp;

        //User search number input:
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a number to search: ");
        int searchInt = sc.nextInt();





    }
}

class Sorting {

    private int row, col;

    //default constructor
    public Sorting()
    {
        row = 5;
        col = 4;
    }


    // array that we want to print
    public void toString(int[][] arr)
    {
        for (int i = 0; i < row; i++)
        {
            System.out.println("");
            for (int j = 0; j < col; j++)
            {
                System.out.print(arr[i][j] + " | ");
            }
        }
        System.out.println();

    }

    public void bubbleSort(int[][] arr)
    {
        System.out.println("Bubble sort array: ");
        for (int i = 0; i < row-1; i++) {
            for (int j = 0; j < row - i - 1; j++)
            {
                if (arr[j][0] > arr[j + 1][0])
                {
                    //swap the items
                    for (int c = 0; c < col; c++)
                    {
                        int temp = arr[j][c];
                        arr[j][c] = arr[j+1][c];
                        arr[j+1][c] = temp;
                    }
                }
            }
        }
        toString(arr);

    }

    public void selectionSort(int[][] arr)
    {
        System.out.println("Selection sorted: ");


        for (int i = 0; i < row - 1; i++)
        {
            int smallestIndex = 0;
            for (int j = i + 1; j < row; j++)
            {
                if (arr[j][1] < arr[smallestIndex][1])
                {
                    smallestIndex = j;
                }

            }
            //swap values in accordance to descending order
            for (int c = 0; c < col; c++)
            {
                int temp = arr[row - 1][c];
                arr[row - 1][c] = arr[smallestIndex][c];
                arr[smallestIndex][c] = temp;

            }

        }
        toString(arr);

    }


    public void shellSort(int[][] arr)
    {
        System.out.println("Shell sort: ");
        for (int i = row/2; i > 0; i /= 2)
        {
            for (int j = i; j < row; j++)
            {
                //temp array to store copy of copied column
                int[] temp = new int[col];

                for (int c = 0; c < col; c++)
                {
                    //copy array of a particular column
                    temp[c] = arr[j][c];
                }
                int k = 0;

                for (k=j; k>=i && arr[k - i][2] > temp[2]; k -= i) {
                    for (int c = 0; c < col; c++)
                    {
                        arr[k][c] = arr[k - i][c];
                    }
                }
                for (int c = 0; c < col; c++)
                {
                    arr[k][c] = temp[c];
                }


            }
        }
        toString(arr);
        

    }

    public void insertionSort(int arr[][])
    {
        System.out.println("Insertion sorted: ");

        for (int i = 1; i < col; i++)
        {
            for (int j = i; j > 0 && arr[row - 1][j] < arr[row - 1][j-1]; j--)
            {
                for (int c = 0; c < row; c++) {
                    //perform swap on the values of the row
                    int temp = arr[c][j];
                    arr[c][j] = arr[c][j - 1];
                    arr[c][j - 1] = temp;

                }
            }
        }
        toString(arr);
    }




    //return a copy of the original array
    public int[][] copy(int[][] arr)
    {
        //declare new copyArr
        int[][] copyArr = new int[row][col];
        // construct nested for loop to
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                copyArr[i][j] = arr[i][j];
            }
        }
        return copyArr;
    }

    public void search(int searchInt, int arr[][])
    {
        int l = 0;
        int r = col - 1;

        while (l <= r)
        {
            int mid = (l + r) / 2;
            if (arr[row - 1][mid] < searchInt)
            {
                l = mid + 1;
            }
            else if (arr[row-1][mid] > searchInt)
            {
                r = mid - 1;
            }
            else
            {
                for (int x = 0; x < row; x++)
                {
                    System.out.println(arr[x][mid]);
                }
                return;
            }

        }
        System.out.println("Could not find column");
    }





}

//end of code
