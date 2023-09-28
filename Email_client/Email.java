package Email_client;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Email implements Serializable {
    private static final long serialVersionUID = 42456778968L;
    private String receiver;
    private String subject;
    private String content;
    private String date;
    private static File file = new File("D:\\Sent_Emails.ser");

    public Email(String receiver, String subject, String content) {
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.set_date();
    }

    // Setting email sent date
    public void set_date() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.date = formatter.format(date);
    }

    // return the email sent date
    public String get_date() {
        return this.date;
    }

    // print the details of a email object
    public void get_details() {
        System.out.println("Date : " + this.date + '\n' +
                "Recipient : " + this.receiver + '\n' +
                "Subject : " + this.subject + '\n' +
                "Content : " + this.content + '\n');
    }

    // serialize the email object
    public void serialize() {
        try {
            if (file.length() == 0) {
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file, true));
                os.writeObject(this);
                os.close();
            } else {
                MyObjectOutputStream mos = new MyObjectOutputStream(new FileOutputStream(file, true));
                mos.writeObject(this);
                mos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // deserialize file saved email objects
    public static void getEmailDetails(String date) {
        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                try {
                    Email email = (Email) os.readObject();
                    if (email.get_date().contains(date)) {
                        email.get_details();
                    }
                }

                catch (EOFException e) {
                    break;
                }
            }
            os.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}