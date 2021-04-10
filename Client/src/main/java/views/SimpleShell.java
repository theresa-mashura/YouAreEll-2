package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import controllers.IdController;
import controllers.MessageController;
import controllers.TransactionController;
import youareell.YouAreEll;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {


    public static void prettyPrint(String output) {
        // yep, make an effort to format things nicely, eh?
        System.out.println(output);
    }
    public static void main(String[] args) throws java.io.IOException {

        YouAreEll urll = new YouAreEll(new TransactionController(new MessageController(), new IdController()));
        
        String commandLine;

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        // MY NOTES...
        // BufferedReader - reads chunks of characters and stores them in an internal buffer
            // Reader reads from the buffer while it has data instead of directly from the underlying stream
            // Expects a Reader in its constructor
        // InputStreamReader - wraps a Java input stream, turning byte based InputStream into character based Reader
            // Interprets the bytes of an InputStream as test instead of numerical data
        // System.in - typically corresponds to input from the keyboard

        ProcessBuilder pb = new ProcessBuilder();
        // MY NOTES...
        // ProcessBuilder - provides methods for creating and configuring operating system processes
            // Allows us to manage a collection of process attributes
            // Usage examples: change working directory where shell command is running, redirect I/O streams, execute a shell command from Java code

        List<String> history = new ArrayList<String>();
        int index = 0;

        //we break out with <ctrl c>
        while (true) {
            // read what the user enters
            System.out.println("cmd? ");
            commandLine = console.readLine();

            // input parsed into array of strings (command and arguments)
            String[] commands = commandLine.split(" ");
            List<String> list = new ArrayList<String>();

            // if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                list.add(commands[i]);

            }
            System.out.print(list); //***check to see if list was added correctly***
            history.addAll(list);

            // COMMANDS:
                // history - prints out items in history ArrayList with an index number
                // ids - returns a formatted list of ids available to the user
                    // ids your_name your_github_id
                    // doing 2x with 2 different names & same github id = name on server gets changed
                // messages - returns the last 20 messages
                    // messages your_github_id - returns last 20 messages sent to YOU
                    // send your_github_id 'Hello World' - post new message on timeline
                    // send your_github_id 'my string message' to some_friend_githubid
                        // post a message to your friend from you on the timeline
                // !! - returns the last command in the history ArrayList
                // !i - returns the command in history at index i

            try {
                // display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                // Specific Commands.

                // ids
                if (list.contains("ids")) {
                    String results = urll.get_ids();
                    SimpleShell.prettyPrint(results);
                    continue;
                }

                // messages
                if (list.contains("messages")) {
                    String results = urll.get_messages();
                    SimpleShell.prettyPrint(results);
                    continue;
                }


                // you need to add a bunch more.



                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // // wait, wait, what curiousness is this?
                // Process process = pb.start();

                // //obtain the input stream
                // InputStream is = process.getInputStream();
                // InputStreamReader isr = new InputStreamReader(is);
                // BufferedReader br = new BufferedReader(isr);

                // //read output of the process
                // String line;
                // while ((line = br.readLine()) != null)
                //     System.out.println(line);
                // br.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (Exception e /*IOException e*/) {
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }


    }

}