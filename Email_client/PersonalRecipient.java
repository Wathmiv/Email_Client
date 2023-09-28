package Email_client;

public class PersonalRecipient extends Recipient implements BirthdayRecipients {
    private String birthday;
    private String nickname;

    public PersonalRecipient(String name, String nickname, String email, String birthday) {
        super(name, email);
        this.birthday = birthday;
        this.nickname = nickname;
    }

    public String get_birthday() {
        return this.birthday;
    }

    public String get_BirthdayGreeting() {
        return ("Hugs and love on your birthday. -Wathmi");
    }

    @Override
    public void printDetails() {
        System.out.println("Personal:\n" +
                " Name: " + get_name() + "\n" +
                " Nickname: " + nickname + "\n" +
                " Email: " + get_email_address() + "\n" +
                " Birthday: " + birthday);
    }
}