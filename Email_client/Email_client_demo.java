package Email_client;

import javax.mail.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Email_client_demo extends FileHandler {

    private ArrayList<Recipient> recipientsList = new ArrayList<>();
    private ArrayList<BirthdayRecipients> birthdayList = new ArrayList<>();
    private Session session;
    private GmailSender gmailSender;

    //
    public Email_client_demo(String userEmail, String password) {
        gmailSender = new GmailSender(userEmail, password);
        session = gmailSender.getGmailSession();
    }

    // to add details of a new recipient to the file ClientList.txt
    public void UpdateFile(String line) {
        File file = new File("D:\\clientList.txt");
        this.writeFIle(file, line);
    }

    // to create Recipient object of the relevant type
    public void AddRecipient(String details) {
        String[] arr;
        Recipient newRecipient = null;
        if (details.contains("Official")) {
            arr = details.substring(10).split(",", 3);
            newRecipient = new OfficialRecipient(arr[0], arr[1], arr[2]);
        } else if (details.contains("Office_friend")) {
            arr = details.substring(15).split(",", 4);
            newRecipient = new OfficialFriend(arr[0], arr[1], arr[2], arr[3]);
            BirthdayRecipients birthdayRecipient1 = (BirthdayRecipients) newRecipient;
            this.birthdayList.add(birthdayRecipient1);

        } else if (details.contains("Personal")) {
            arr = details.substring(10).split(",", 4);
            newRecipient = new PersonalRecipient(arr[0], arr[1], arr[2], arr[3]);
            BirthdayRecipients birthdayRecipient2 = (BirthdayRecipients) newRecipient;
            this.birthdayList.add(birthdayRecipient2);
        }
        this.recipientsList.add(newRecipient);
    }

    // overriding the method
    // Operation to do on the information read by the file
    void readOperation(String line) {
        this.AddRecipient(line);
    }

    // Creates a list of recipient objects reading the
    // file clientList.txt
    public void CreateRecipientsList() {
        File file = new File("D:\\clientList.txt");
        this.readFile(file);
    }

    // Print the recipients heaving birthday on the given date
    public void PrintBirthdayRecipients(String date) {
        for (BirthdayRecipients birthdayRecipient : this.birthdayList) {
            if (birthdayRecipient.get_birthday().contains(date.substring(5))) {
                birthdayRecipient.printDetails();
            }
        }
    }

    // to send n mail to a recipient
    public void SendEmail(String recipientEmail, String subject, String content) {
        gmailSender.sendMail(session, recipientEmail, subject, content);
        Email email1 = new Email(recipientEmail, subject, content);
        email1.serialize();
    }

    // to send the birthday greeting
    // use a file to save the last date
    // checks the last date that mails have been sent
    // only send greetings if no greetings sent on today
    public void SendBirthdayMessages() {
        File file = new File("D:\\Birthday_sent.txt");
        JustRead justRead = new JustRead();
        justRead.readFile(file);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String today = formatter.format(date);
        if (file.length() != 0 && justRead.line.contains(today)) {
            return;
        } else {
            String subject = "Birthday Wish";
            for (BirthdayRecipients birthdayRecipient : birthdayList) {
                if (birthdayRecipient.get_birthday().contains(today.substring(5))) {
                    String content = birthdayRecipient.get_BirthdayGreeting();
                    Recipient recipient = (Recipient) birthdayRecipient;
                    String email = recipient.get_email_address();
                    this.SendEmail(email, subject, content);
                    justRead.writeFIle(file, today);
                }
            }
        }

    }

    // prints the details of the mails sent on a given date
    public void GetEmailDetails(String date) {
        Email.getEmailDetails(date);
    }

}