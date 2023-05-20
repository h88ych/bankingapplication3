/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankingapplication3;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class BankingApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int option = 0;
        
        Account account;
        String name;
        int number;
        double balance , amount;
        
        Bank bank = new Bank("ABC");
        
        while(option != 6){
            System.out.println("Main Menu");
            System.out.println("1.Display All Accout");
            System.out.println("2.Open New Accout");
            System.out.println("3.Close Existing Accout");
            System.out.println("4.Deposit");      
            System.out.println("5.Withdraw");
            
            System.out.println();
            System.out.print("Enter your choice = ");
            option =scan.nextInt();
            scan.nextLine(); // read when click enter
            System.out.println();
            
            switch(option){ //response code for choose menu
                case 1:
                    bank.listAccount();
                    break;
                case 2:
                    number = randomAccountNumber(); //make it automatic / random
                    System.out.print("Enter Account Name = ");
                    name = scan.nextLine(); //String
                    System.out.print("Enter Initial Balance = ");
                    balance = scan.nextDouble();
                    account = new Account( number,  name,  balance);
                    bank.openAccount(account);
                    break;
                case 3:
                    System.out.print("Enter Account Nunber = ");
                    number = scan.nextInt();
                    bank.closeAccount(number);
                    System.out.println("Account is deleted");
                    System.out.println(); //space
                    break;
                case 4:
                    System.out.print("Enter Account number = ");
                    number = scan.nextInt();
                    account = bank.getAccount(number);
                    System.out.print("Enter Amount = ");
                    amount = scan.nextDouble();
                    bank.depositMoney(account, amount);
                    break;
                case 5:
                    System.out.print("Enter Account number = ");
                    number = scan.nextInt();
                    account = bank.getAccount(number);
                    System.out.print("Enter Amount = ");
                    amount = scan.nextDouble(); 
                    bank.withdrawMoney(account, amount);
            }
        }
    }
    public static int randomAccountNumber(){ //have to create obj then can use it
        Random ran = new Random();
        int accNumber = 100000 + ran.nextInt(900000);
        return accNumber;
    }
}
