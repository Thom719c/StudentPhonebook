package com.company;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args)throws FileNotFoundException{
        Scanner input = new Scanner(System.in);
        Scanner scanN = new Scanner(new File("src/com/company/StudentNameList.txt"));
        Scanner scanP = new Scanner(new File("src/com/company/StudentPhoneNrList.txt"));
        String[] names = new String[10];
        Arrays.fill(names, "nn");
        int[] pNumbers = new int[10];
        updateArray(scanN, scanP, names, pNumbers);
        System.out.println("Enter 1 for new entry" + "\n2 to show list" + "\n3 to delete name/number" + "\n4 to quit");
        int answer = input.nextInt();
        while(answer != 4){
            switch(answer){
                case 1:  //new entry
                    createStudent(input, names, pNumbers);
                    updateFile(names, pNumbers);
                    break;
                case 2:  //Show list
                    showList(names, pNumbers);
                    break;
                case 3:  //Delete name/number
                    deleteStudent(input, names, pNumbers);
                    updateFile(names, pNumbers);
                    break;
                default:
                    System.out.println("Seeya");
            }
            System.out.println("Enter 1 for new entry" + "\n2 to show list" + "\n3 to delete name/number" + "\n4 to quit");
            answer = input.nextInt();
        }
    }
    public static void createStudent(Scanner input, String[] n, int[] p){
        for(int i = 0; i < n.length; i++){
            if(p[i] == 0){
                System.out.println("Enter name");
                n[i] = input.next();
                System.out.println("Enter phonenumber");
                p[i] = input.nextInt();
                break;
            }
        }
    }
    public static void deleteStudent(Scanner input, String[] n, int[] p){
        for(int i = 0; i < p.length; i++){
            System.out.printf("Number: %2d \t %-4s \t %8d \n", i+1, n[i], p[i]);
        }
        System.out.println("Which number do you want to delete?");
        int number = input.nextInt();
        if(number >= 1 && number <= p.length){
            n[number-1] = "nn";
            p[number-1] = 0;
        }
    }
    public static void showList(String[] n, int[] p){
        for(int j = 0; j < p.length; j++){
            System.out.printf("Name: %2s \nPhonenumbers: %7d \n", n[j], p[j]);
        }
        System.out.println();
    }
    public static void updateFile(String[] n, int[] p)throws FileNotFoundException{
        PrintStream outN = new PrintStream(new File("src/com/company/StudentNameList.txt"));
        PrintStream outP = new PrintStream(new File("src/com/company/StudentPhoneNrList.txt"));
        String outputN = "";
        String outputP = "";
        for(int i = 0; i < n.length; i++){
            outputN += n[i] + " ";
            outputP += p[i] + " ";
        }
        outN.println(outputN);
        outP.println(outputP);
    }
    public static void updateArray(Scanner scanN, Scanner scanP, String[] n, int[] p){
        int i = 0;
        String lineN = scanN.nextLine();
        Scanner lineScanN = new Scanner(lineN);
        String lineP = scanP.nextLine();
        Scanner lineScanP = new Scanner(lineP);
        while(lineScanP.hasNextInt()){
            n[i] = lineScanN.next();
            p[i] = lineScanP.nextInt();
            i++;
        }
    }
}
