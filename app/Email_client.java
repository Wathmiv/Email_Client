package app;

import Email_client.*;

import java.util.Scanner;

public class Email_client {
    public static void main(String[] args) {
        Email_client_demo emailClient = new Email_client_demo("wathmi.v@gmail.com", "ygydchgvaaglcwio");
        emailClient.CreateRecipientsList();
        emailClient.SendBirthdayMessages();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Enter option type:\s
                1 - Adding a new recipient
                2 - Sending an email
                3 - Printing out all the recipients who have birthdays
                4 - Printing out details of all the emails sent
                5 - Printing out the number of recipient objects in the application""");

        int option = scanner.nextInt();

        switch (option) {
            case 1 -> {
                System.out.print("Enter recipient's details : ");
                scanner.nextLine();
                String line1 = scanner.nextLine();
                emailClient.AddRecipient(line1);
                emailClient.UpdateFile(line1);
            }
            case 2 -> {
                System.out.print("Enter recipient's email: ");
                scanner.nextLine();
                String recipientEmail = scanner.nextLine();
                System.out.print("Enter subject: ");
                String subject = scanner.nextLine();
                System.out.print("Enter content: ");
                String content = scanner.nextLine();
                emailClient.SendEmail(recipientEmail, subject, content);
            }
            case 3 -> {
                System.out.print("Enter the date : ");
                scanner.nextLine();
                String line3 = scanner.nextLine();
                emailClient.PrintBirthdayRecipients(line3);
            }
            case 4 -> {
                System.out.print("Enter date: ");
                scanner.nextLine();
                String line4 = scanner.nextLine();
                emailClient.GetEmailDetails(line4);
            }
            case 5 ->
                System.out.println(Recipient.recipient_count);
        }
    }
}