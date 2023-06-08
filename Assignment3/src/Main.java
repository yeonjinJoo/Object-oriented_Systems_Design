import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        CommandLineInterface cli = new CommandLineInterface();
        ContactManager manager = new ContactManager(cli);
        int choice;

        Scanner in;
        FileWriter fileWriter;
        PrintWriter out;

        while(true)
        {
            try
            {
                in = new Scanner(new FileInputStream("ContactBook.txt"));
                fileWriter = new FileWriter("ContactBook.txt", true);
                out = new PrintWriter(fileWriter, true);
                choice = cli.getMainMenu();

                switch(choice)
                {
                    case 1:
                        manager.setStorageSize();
                        break;
                    case 2:
                        // 조교님한테 물어보고 구현
                        manager.saveToFile(in, out);
                        break;
                    case 3:
                        // 조교님한테 물어보고 구현
                        manager.loadFromFile(in);
                        break;
                    case 4:
                        manager.createContact(cli.getCreateContactMenu());
                        break;
                    case 5:
                        manager.searchContact(cli.getSearchContactMenu());
                        break;
                    case 6:
                        manager.deleteContact();
                        break;
                    case 7:
                        manager.editContact();
                        break;
                    case 8:
                        cli.printContactInfo(manager);
                        break;
                    case 9:
                        System.exit(0);
                }
                in.close(); fileWriter.close(); out.close();
            }
            catch(NumberChoiceException e) {
                cli.printErrorMessage(e.getMessage());
            }
            catch (StorageException e){
                cli.printErrorMessage(e.getMessage());
            }
            catch(InputMismatchException e){
                cli.printErrorMessage("[InputMismatchException] InputMismatch occurred");
                cli.setScanner(new Scanner(System.in));
            }
            catch (NumberFormatException e){
                cli.printErrorMessage("[NumberFormatException] Phone number includes string other than number");
            }
            catch (FileNotFoundException e) {
                cli.printErrorMessage("[FileNotFoundException] Problem opening files.");
                System.exit(0);
            } catch (ContactNotFoundException e) {
                cli.printErrorMessage(e.getMessage());
            } catch (IOException e) {
                cli.printErrorMessage("[IOException] Problem writing file");
                System.exit(0);
            } catch (Exception e) {
                cli.printErrorMessage(e.getMessage());
            }
        }
    }
}

