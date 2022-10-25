import java.util.Scanner;

public class Application {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Application().run();
    }

    /**
     * To run the Duke Application
     */

    public void run() {
        assert false : "input should be a tree";
        greetUser();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = readCommand();
                showLine();
//                Command c = parser.parseCommand(fullCommand);
//                c.execute();
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.displayMessage(e.getErrorMessage());
            } finally {
                showLine();
            }
        }
        endProgram();
    }

    private static void greetUser(){
        System.out.println("Hello I'm MOBLIMA");
        System.out.println("I am a movie booking & listing management application!");
    }
    private static String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    private static void showLine(){
        System.out.println("____________________________");
    }

    private static void endProgram(){
        System.out.println("Bye!");
    }
}
