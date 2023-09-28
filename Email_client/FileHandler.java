package Email_client;

import java.io.*;

abstract class FileHandler {
    // write from the beginning of the file
    // take the file to write on and
    // string to be written as the parameter
    public void writeFileFromBeginning(File file, String line) {
        try {
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(file));
            writer1.write(line + '\n');
            writer1.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // append to a file
    // take the file to write on and
    // string to be written as the parameter
    public void writeFIle(File file, String line) {
        try {
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(file, true));
            writer1.write(line + '\n');
            writer1.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // this method should describe the operation
    // to call on the information read by readFile method
    abstract void readOperation(String line);

    // this method reads a file
    // File to be read is given as parameter
    public void readFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            line = reader.readLine();
            while (line != null) {
                this.readOperation(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}