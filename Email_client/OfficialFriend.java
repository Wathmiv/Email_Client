package Email_client;

public class OfficialFriend extends OfficialRecipient implements BirthdayRecipients {
    private String birthday;

    public OfficialFriend(String name, String email, String designation, String birthday) {
        super(name, email, designation);
        this.birthday = birthday;
    }

    public String get_birthday() {
        return this.birthday;
    }

    @Override
    public String get_BirthdayGreeting() {
        return "Wish you a Happy Birthday. -Wathmi";
    }

    // overriding the method
    @Override
    public void printDetails() {
        System.out.println("Office_friend:\n" +
                " Name: " + get_name() + "\n" +
                " Email: " + get_email_address() + "\n" +
                " Designation: " + get_designation() + "\n" +
                " Birthday: " + birthday);
    }
}