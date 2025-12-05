import java.util.Scanner;
public class Hotel_Booking_And_Resturant_Billing_System{
    public static void manageroom(){

    }
    public static void addroom(){
        
    }
    public static void room_discount(){
        
    }
    public static void checkroom(){
        
    }
    public static void genrateroombill(){
        
    }
    public static void manage_menu(){
        
    }
    public static void additem(){
        
    }
    public static void discount(){
        
    }
    public static void searchroom(){
        
    }
    public static void searchitem(){
        
    }
    public static void generatebill(){
        
    }
    public static void checkstatus(){
        
    }
    public static Scanner input = new Scanner (System.in);
    public static void hotel(String user){
        System.out.println("|------------------------------------------------------|");
        System.out.println("|Welcome "+user+ "                                  |");
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
        switch(choice ){
            case (0):{
                exit();
                
                break;
            }
            case (1):{
                manageroom();
                
                break;
            }
            case (2):{
                addroom();
                
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
        System.out.println("| 1.Manage Menu                                        |");
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
                manage_menu();
                
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
    public static void passcheck(String pass,String id){

    }
    public static void idcheck(String id){
        int controll = 1 ;
        while (controll !=3){
        if (!id.contains("@")){
            System.out.println("INVALID INPUT! \n Exiting to Admin Menu");
            Admin();
        }
        if (!id.contains("@admin.com")){
            System.out.println("INVALID INPUT! \n Exiting to Admin Menu");
            Admin();
        }
        System.out.println("Log-In Succesfull . Welcome "+ id.substring(0,id.indexOf("@")));
        String user = id.substring(0,id.indexOf("@"));
        admin_menu(user);
        controll+=1;
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
                
            System.out.print("Enter your Password:");            
            String password= input.nextLine();
            idcheck(id);
            passcheck(password,id);
            count +=1;
            
        }


    }
    public static void User (){
        input.nextLine();

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
