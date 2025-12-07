import java.util.Scanner;
import java.io.*;
public class Hotel_Booking_And_Resturant_Billing_System{
    public static Scanner sc = new Scanner (System.in);
    public static void hotel_user(String name){

    }
    public static void Resturant_user(String name){

    }
    public static void user_menu(String name ){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|       Welcome "+ name +" to User  Portal               |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("|            1. Veiw Hotel                             |");
        System.out.println("|            2. Veiw Resturant                         |");
        System.out.println("|            3. Back to previous Menu                  |");
        System.out.println("|------------------------------------------------------|");
        int choice = input.nextInt();
        switch(choice ){
            case (1):{
                hotel_user(name);
                
                break;
            }
            case (2):{
                Resturant_user(name);
                
                break;
            }
            case (3):{
                User();

            }
            default:
                System.out.println("INVALID OUTPUT! \n Exiting to Main Menu .....");
                intialMenu();}

    }
    public static void chkRoom(){
        try {
            File roomfile = new File("Room.txt");
            if (!roomfile.exists()) {
            roomfile.createNewFile();
            PrintWriter writer = new PrintWriter(roomfile);
            writer.println("Room No: 01 |Status: Single Seater/Booked |Price: 27k |");
            writer.close();
            System.out.println("File Created succesfully");
        }
        
        } catch (Exception e) {
            System.out.println("An Error occured!");
        }
        System.out.println("---------> Displaying Rooms <-----------");
        try {
            File room = new File ("Room.txt");
            Scanner reader = new Scanner(room);
            while(reader.hasNextLine()){
                String Roomstatus = reader.nextLine();
                System.out.println(Roomstatus);
                

            }

        } catch (Exception e) {
            System.out.println("An error occured!");
        } 
        intialMenu();


    }
    public static void bookroom(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome                                   |");
        System.out.println("|                    Hotel Portal                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-6) to navigate to your option               |");
        System.out.println("| 1.Manage Room                                        |");
        System.out.println("| 2.Add Room                                           |");
        System.out.println("| 3.Check Room Status                                  |");
        System.out.println("| 4.Generate Bill                                      |");
        System.out.println("| 5.Search Room                                        |");
        System.out.println("| 6.Add Discount                                       |");
        System.out.println("| 0.Exit()                                             |");
        System.out.println("|------------------------------------------------------|");
                int choice = input.nextInt();
        
    }
    public static void room_discount(){
                System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome                                   |");
        System.out.println("|                    Hotel Portal                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-6) to navigate to your option               |");
        System.out.println("| 1.Manage Room                                        |");
        System.out.println("| 2.Add Room                                           |");
        System.out.println("| 3.Check Room Status                                  |");
        System.out.println("| 4.Generate Bill                                      |");
        System.out.println("| 5.Search Room                                        |");
        System.out.println("| 6.Add Discount                                       |");
        System.out.println("| 0.Exit()                                             |");
        System.out.println("|------------------------------------------------------|");
                int choice = input.nextInt();
        
    }
    public static void checkroom(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome                                   |");
        System.out.println("|                    Hotel Portal                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-6) to navigate to your option               |");
        System.out.println("| 1.Manage Room                                        |");
        System.out.println("| 2.Add Room                                           |");
        System.out.println("| 3.Check Room Status                                  |");
        System.out.println("| 4.Generate Bill                                      |");
        System.out.println("| 5.Search Room                                        |");
        System.out.println("| 6.Add Discount                                       |");
        System.out.println("| 0.Exit()                                             |");
        System.out.println("|------------------------------------------------------|");
                int choice = input.nextInt();
        
    }
    public static void genrateroombill(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome                                   |");
        System.out.println("|                    Hotel Portal                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-6) to navigate to your option               |");
        System.out.println("| 1.Manage Room                                        |");
        System.out.println("| 2.Add Room                                           |");
        System.out.println("| 3.Check Room Status                                  |");
        System.out.println("| 4.Generate Bill                                      |");
        System.out.println("| 5.Search Room                                        |");
        System.out.println("| 6.Add Discount                                       |");
        System.out.println("| 0.Exit()                                             |");
        System.out.println("|------------------------------------------------------|");
                int choice = input.nextInt();
        
    }
    public static void check_menu(){
        try {
            File menufile = new File("Menu.txt");
            if (!menufile.exists()) {
            menufile.createNewFile();
            PrintWriter writer = new PrintWriter(menufile);
            writer.println("Item No: 01 Name: Biryani Price: 350/Plate ");
            writer.println("Item No: 02 Name: Karahi  Price: 1800/kg ");
            writer.println("Item No: 03 Name: chowmin Price: 400/Plate ");
            writer.println("Item No: 04 Name: Momos   Price: 450/Dozen ");
            writer.println("Item No: 05 Name: Chinese Rice Price: 350/Plate ");
            writer.println("Item No: 06 Name: Shwarma Price: 200/Plate ");
            writer.println("Item No: 07 Name: Burger Price: 300/Plate ");
            writer.println("Item No: 08 Name: Pizza Price: 1800/Plate ");
            writer.println("Item No: 09 Name: Nihari Price: 250/Plate ");
            writer.println("Item No: 10 Name: Vegetable Rice Price: 300/Plate ");
            writer.close();
            System.out.println("File Created succesfully");
        }
        
        } catch (Exception e) {
            System.out.println("An Error occured!");
        }
        System.out.println("---------> Displaying Rooms <-----------");
        try {
            File menu = new File ("Menu.txt");
            Scanner reader = new Scanner(menu);
            while(reader.hasNextLine()){
                String menuStatus = reader.nextLine();
                System.out.println(menuStatus);
                

            }

        } catch (Exception e) {
            System.out.println("An error occured!");
        } 
        intialMenu();
        
    }
    public static void additem(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome                                   |");
        System.out.println("|                    Hotel Portal                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-6) to navigate to your option               |");
        System.out.println("| 1.Manage Room                                        |");
        System.out.println("| 2.Add Room                                           |");
        System.out.println("| 3.Check Room Status                                  |");
        System.out.println("| 4.Generate Bill                                      |");
        System.out.println("| 5.Search Room                                        |");
        System.out.println("| 6.Add Discount                                       |");
        System.out.println("| 0.Exit()                                             |");
        System.out.println("|------------------------------------------------------|");
                int choice = input.nextInt();
        
    }
    public static void discount(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome                                    |");
        System.out.println("|                    Hotel Portal                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-6) to navigate to your option               |");
        System.out.println("| 1.Manage Room                                        |");
        System.out.println("| 2.Add Room                                           |");
        System.out.println("| 3.Check Room Status                                  |");
        System.out.println("| 4.Generate Bill                                      |");
        System.out.println("| 5.Search Room                                        |");
        System.out.println("| 6.Add Discount                                       |");
        System.out.println("| 0.Exit()                                             |");
        System.out.println("|------------------------------------------------------|");
                int choice = input.nextInt();
        
    }
    public static void searchroom(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome                                   |");
        System.out.println("|                    Hotel Portal                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-6) to navigate to your option               |");
        System.out.println("| 1.Manage Room                                        |");
        System.out.println("| 2.Add Room                                           |");
        System.out.println("| 3.Check Room Status                                  |");
        System.out.println("| 4.Generate Bill                                      |");
        System.out.println("| 5.Search Room                                        |");
        System.out.println("| 6.Add Discount                                       |");
        System.out.println("| 0.Exit()                                             |");
        System.out.println("|------------------------------------------------------|");
                int choice = input.nextInt();
        
    }
    public static void searchitem(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome                                   |");
        System.out.println("|                    Hotel Portal                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-6) to navigate to your option               |");
        System.out.println("| 1.Manage Room                                        |");
        System.out.println("| 2.Add Room                                           |");
        System.out.println("| 3.Check Room Status                                  |");
        System.out.println("| 4.Generate Bill                                      |");
        System.out.println("| 5.Search Room                                        |");
        System.out.println("| 6.Add Discount                                       |");
        System.out.println("| 0.Exit()                                             |");
        System.out.println("|------------------------------------------------------|");
                int choice = input.nextInt();
        
    }
    public static void generatebill(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome                                   |");
        System.out.println("|                    Hotel Portal                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-6) to navigate to your option               |");
        System.out.println("| 1.Manage Room                                        |");
        System.out.println("| 2.Add Room                                           |");
        System.out.println("| 3.Check Room Status                                  |");
        System.out.println("| 4.Generate Bill                                      |");
        System.out.println("| 5.Search Room                                        |");
        System.out.println("| 6.Add Discount                                       |");
        System.out.println("| 0.Exit()                                             |");
        System.out.println("|------------------------------------------------------|");
                int choice = input.nextInt();
        
    }
    public static void checkstatus(){
        
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome                                   |");
        System.out.println("|                    Hotel Portal                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-6) to navigate to your option               |");
        System.out.println("| 1.Manage Room                                        |");
        System.out.println("| 2.Add Room                                           |");
        System.out.println("| 3.Check Room Status                                  |");
        System.out.println("| 4.Generate Bill                                      |");
        System.out.println("| 5.Search Room                                        |");
        System.out.println("| 6.Add Discount                                       |");
        System.out.println("| 0.Exit()                                             |");
        System.out.println("|------------------------------------------------------|");
                int choice = input.nextInt();
    }
    public static Scanner input = new Scanner (System.in);
    public static void hotel(String user){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome    "+user+"                               |");
        System.out.println("|                    Hotel Portal                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-6) to navigate to your option               |");
        System.out.println("| 1.Check All Rooms                                    |");
        System.out.println("| 2.Book Room for Guest                                |");
        System.out.println("| 3.Check Room Status/Update Room status               |");
        System.out.println("| 4.Generate Bill                                      |");
        System.out.println("| 5.Search Room/Guest                                  |");
        System.out.println("| 6.Add Discount                                       |");
        System.out.println("| 0.Exit()                                             |");
        System.out.println("|------------------------------------------------------|");
        int choice = input.nextInt();
        switch(choice ){
            case (0):{
                exit();
                
                break;
            }
            case (1):{
                chkRoom();
                
                break;
            }
            case (2):{
                bookroom();
                
                break;
            }
            case (3):{
                checkroom();

            }
            case (4):{
                genrateroombill();
                
                break;
            }
            case (5):{
                searchroom();
                
                break;
            }
            case (6):{
                room_discount();

            }
            default:
                System.out.println("INVALID OUTPUT! \n Exiting to Main Menu .....");
                intialMenu();}

    }
    public static void Resturant(String user){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome "+user+ "                                 |");
        System.out.println("|                Resturant Portal                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-6) to navigate to your option               |");
        System.out.println("| 1.Check Menu                                         |");
        System.out.println("| 2.Add Item to Menu                                   |");
        System.out.println("| 3.Check Resturant Status                             |");
        System.out.println("| 4.Generate Bill                                      |");
        System.out.println("| 5.Search Item                                        |");
        System.out.println("| 6.Add Discount                                       |");
        System.out.println("| 0.Exit()                                             |");
        System.out.println("|------------------------------------------------------|");
        int choice = input.nextInt();
        switch(choice ){
            case (0):{
                exit();
                
                break;
            }
            case (1):{
                check_menu();
                
                break;
            }
            case (2):{
                additem();
                
                break;
            }
            case (3):{
                checkstatus();

            }
            case (4):{
                discount();
                
                break;
            }
            case (5):{
                searchitem();
                
                break;
            }
            case (6):{
                generatebill();

            }

            default:
                System.out.println("INVALID OUTPUT! \n Exiting to Main Menu .....");
                intialMenu();}
    }
    public static void admin_menu(String user){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|       Welcome "+ user +" to Admin  Portal             |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("|            1. Manage Hotel                           |");
        System.out.println("|            2. Manage Resturant                       |");
        System.out.println("|            3. Back to previous Menu                  |");
        System.out.println("|------------------------------------------------------|");

        int choice = input.nextInt();
        switch(choice ){
            case (1):{
                hotel(user);
                
                break;
            }
            case (2):{
                Resturant(user);
                
                break;
            }
            case (3):{
                Admin();

            }
            default:
                System.out.println("INVALID OUTPUT! \n Exiting to Main Menu .....");
                intialMenu();}
        
    }
        public static boolean checkUserCredentials(String id, String password) {
    // Validate email format
    if (!id.contains("@") || !id.endsWith("@user.com")) {
        System.out.println("Invalid email format. Must be: username@user.com");
        return false;
    }
    
    String username = id.substring(0, id.indexOf("@"));
    
    try {
        File adminfile = new File("userlog.txt");
        System.out.println("Looking for user file at: " + adminfile.getAbsolutePath());
        
        // Create file if it doesn't exist
        if (!adminfile.exists()) {
            adminfile.createNewFile();
            PrintWriter writer = new PrintWriter(adminfile);
            writer.println("name: user pass: user123");
            writer.close();
            System.out.println("Default user created: user@user.com / user123");
            // Check against default credentials
            return username.equals("user") && password.equals("user123");
        }
        
        // Read and check credentials
        Scanner reader = new Scanner(adminfile);
        while (reader.hasNextLine()) {
            String line = reader.nextLine().trim();
            
            if (line.startsWith("name:") && line.contains("pass:")) {
                int passIndex = line.indexOf(" pass:");
                
                if (passIndex != -1) {
                    String storedUsername = line.substring(5, passIndex).trim();
                    String storedPassword = line.substring(passIndex + 6).trim(); // Skip " pass:"
                    
                    System.out.println("Checking: " + storedUsername + " / " + storedPassword);
                    
                    if (storedUsername.equalsIgnoreCase(username) && 
                        storedPassword.equals(password)) {
                        reader.close();
                        return true;
                    }
                }
            }
        }
        reader.close();
        return false;
        
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
        return false;
    }
}
    public static boolean checkAdminCredentials(String id, String password) {
    // Validate email format
    if (!id.contains("@") || !id.endsWith("@admin.com")) {
        System.out.println("Invalid email format. Must be: username@admin.com");
        return false;
    }
    
    String username = id.substring(0, id.indexOf("@"));
    
    try {
        File adminfile = new File("adminlog.txt");
        System.out.println("Looking for admin file at: " + adminfile.getAbsolutePath());
        
        // Create file if it doesn't exist
        if (!adminfile.exists()) {
            adminfile.createNewFile();
            PrintWriter writer = new PrintWriter(adminfile);
            writer.println("name: admin pass: admin123");
            writer.close();
            System.out.println("Default admin created: admin@admin.com / admin123");
            // Check against default credentials
            return username.equals("admin") && password.equals("admin123");
        }
        
        // Read and check credentials
        Scanner reader = new Scanner(adminfile);
        while (reader.hasNextLine()) {
            String line = reader.nextLine().trim();
            
            if (line.startsWith("name:") && line.contains("pass:")) {
                int passIndex = line.indexOf(" pass:");
                
                if (passIndex != -1) {
                    String storedUsername = line.substring(5, passIndex).trim();
                    String storedPassword = line.substring(passIndex + 6).trim(); // Skip " pass:"
                    
                    System.out.println("Checking: " + storedUsername + " / " + storedPassword);
                    
                    if (storedUsername.equalsIgnoreCase(username) && 
                        storedPassword.equals(password)) {
                        reader.close();
                        return true;
                    }
                }
            }
        }
        reader.close();
        return false;
        
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
        return false;
    }
}
    
    public static void Admin(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|                    Log In Menu Admin                 |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Greeting User!                                        |");
        System.out.println("|Please Enter your Admin Id and Password.              |");
        System.out.println("|Your Id should be in the format\"yourname@admin.com\" |");
        System.out.println("|You have three attempts                               |");
        System.out.println("|Press (0) to navigate to previous window              |");
        System.out.println("|------------------------------------------------------|");
        input.nextLine();
        int count = 1 ;
        while (true ){
            if (count > 3 ){
                System.out.println("You exceed the Log In limit! \n Returning to main menu.");
                intialMenu();
            }

            
        
            System.out.print("Enter your Admin Id: ");
            String id = input.nextLine();
            if (id.equals("0")){
                Login();
            } 
            String name = id.substring(0,id.indexOf("@"));
            System.out.print("Enter your Password:");            
            String password= input.nextLine();
            checkAdminCredentials(id,password);
            if (checkAdminCredentials(id, password)==true){
                admin_menu(name);
            }
            else {
                System.out.println("Invalid Credential!Please Enter Again");
                count +=1;
                continue;
            }

            
            
        }


    }
    public static void User (){
        input.nextLine();
        System.out.println("|------------------------------------------------------|");
        System.out.println("|                    Log In Menu User                  |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Greeting User!                                        |");
        System.out.println("|Please Enter your User  Id and Password.              |");
        System.out.println("|Your Id should be in the format\"yourname@user .com\" |");
        System.out.println("|You have three attempts                               |");
        System.out.println("|Press (0) to navigate to previous window              |");
        System.out.println("|------------------------------------------------------|");
        input.nextLine();
        int count = 1 ;
        while (true ){
            if (count > 3 ){
                System.out.println("You exceed the Log In limit! \n Returning to main menu.");
                intialMenu();
            }

            
        
            System.out.print("Enter your User Id: ");
            String id = input.nextLine();
            if (id.equals("0")){
                Login();
            } 
            String name = id.substring(0,id.indexOf("@"));
            System.out.print("Enter your Password:");            
            String password= input.nextLine();
            checkUserCredentials(id,password);
            if (checkUserCredentials(id, password)==true){
                user_menu(name);
            }
            else {
                System.out.println("Invalid Credential!Please Enter Again");
                count +=1;
                continue;
            }

            
            
        }


    }

    public static void exit (){
        System.out.println("Exiting program ! Good Bye");
        System.exit(0);
        }
    public static void Login(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|                     Log in Menu                      |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("|            1. Log In as an Admin                     |");
        System.out.println("|            2. Log in as a User                       |");
        System.out.println("|            3. Back to Main Menu                      |");
        System.out.println("|------------------------------------------------------|");
        int choice = input.nextInt();
        switch(choice ){
            case (1):{
                Admin();
                ;
                break;
            }
            case (2):{
                User();
                
                break;
            }
            case (3):{
                intialMenu();

            }
            default:
                System.out.println("INVALID OUTPUT! \n Exiting to Main Menu .....");
                intialMenu();

        }
    }
    public static void intialMenu(){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|                      Main Menu                       |");
        System.out.println("|       Hotel Booking and Resturant Billing System     |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Greeting User! Please Input Your choice .            |");
        System.out.println("|            1. Log-In Menu                            |");
        System.out.println("|            2. Exit                                   |");
        System.out.println("|------------------------------------------------------|");
        int choice = input.nextInt();
        switch(choice){

            case (1):{
                System.out.println("Executing your choice \nEntering Log-in Menu \nPlease wait a bit ......... ");
                Login();
                break;}
            case (2):{
                exit();}
            default:
                System.out.println("INVALID CHOICE! Exiting .....");
                exit();
            
        }
    }
    public static void main(String[] args) {
        intialMenu();
    }
}

