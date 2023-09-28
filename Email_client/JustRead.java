package Email_client;

public class JustRead extends FileHandler {
    public String line;

    // store each line read to the line
    // but finally only the last line can be accessed
    @Override
    void readOperation(String line) {
        this.line = line;
    }
}