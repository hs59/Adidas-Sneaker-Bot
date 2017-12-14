package main.Java.ADC;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
        // Scanner obj to get username and password from user
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your username?: ");
        String username = scan.nextLine();
        System.out.println("What is your password?: ");
        String password = scan.nextLine();

        // Create new client and pass credentials
        HTMLunit client = new HTMLunit(username, password);

        // Login
        client.login();

        /** // Scrape some stuff
        String page = client.get("https://calgary.bibliocommons.com/user_dashboard");
        Elements header = Jsoup.parse(page).select("cp_header");
        for(Element message : header) {
            System.out.println(message.text() + "\n");
         */
        }
    }

