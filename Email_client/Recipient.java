
package Email_client;

public class Recipient {
    private String name;
    private String email_address;
    public static int recipient_count = 0;

    public Recipient(String name, String email) {
        this.name = name;
        this.email_address = email;
        recipient_count += 1;
    }

    public String get_name() {
        return this.name;
    }

    public String get_email_address() {
        return this.email_address;
    }
}