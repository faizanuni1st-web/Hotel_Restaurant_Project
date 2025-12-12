import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Hotel_Booking_And_Resturant_Billing_System {
    public static Scanner sc = new Scanner (System.in);
    
    public static void restaurant_generate_bill() {
     try {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter room number: ");
        String roomNo = sc.nextLine();

        File orderFile = new File("RestaurantOrders.txt");
        Scanner read = new Scanner(orderFile);

        int total = 0;
        boolean found = false;

        while (read.hasNextLine()) {
            String[] p = read.nextLine().split(",");

            if (p[0].trim().equals(roomNo)) {
                int price = Integer.parseInt(p[3].trim());
                int qty = Integer.parseInt(p[4].trim());
                total += price * qty;
                found = true;
            }
        }
        read.close();

        if (!found) {
            System.out.println("No orders found for this room.");
            return;
        }

        PrintWriter bill = new PrintWriter(new FileWriter("RestaurantBills.txt", true));
        bill.println(roomNo + ", " + total);
        bill.close();

        System.out.println("\n----- Restaurant Bill -----");
        System.out.println("Room No: " + roomNo);
        System.out.println("Total Bill: " + total);

     } catch (Exception e) {
        System.out.println("Error generating bill: " + e.getMessage());
      }
    }

    public static void place_order() {
     try {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter room number: ");
        String roomNo = sc.nextLine();

        File cartFile = new File("RestaurantCart.txt");
        Scanner read = new Scanner(cartFile);

        ArrayList<String> newCart = new ArrayList<>();
        ArrayList<String> orderItems = new ArrayList<>();

        while (read.hasNextLine()) {
            String line = read.nextLine();
            String[] p = line.split(",");

            if (p[0].trim().equals(roomNo)) {
                orderItems.add(line);
            } else {
                newCart.add(line);
            }
        }
        read.close();

        if (orderItems.isEmpty()) {
            System.out.println("Cart is empty for this room.");
            return;
        }

        // Move items to RestaurantOrders.txt
        PrintWriter ord = new PrintWriter(new FileWriter("RestaurantOrders.txt", true));
        PrintWriter hist = new PrintWriter(new FileWriter("RestaurantHistory.txt", true));

        for (String s : orderItems) {
            ord.println(s);
            hist.println("Room " + roomNo + " ordered: " + s);
        }

        ord.close();
        hist.close();

        // Overwrite new cart
        PrintWriter writer = new PrintWriter("RestaurantCart.txt");
        for (String s : newCart) writer.println(s);
        writer.close();

        System.out.println("\nOrder placed successfully!");

     } catch (Exception e) {
        System.out.println("Error placing order: " + e.getMessage());
     }
    }

    public static void update_order() {
     try {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter room number: ");
        String roomNo = sc.nextLine();

        System.out.print("Enter item ID to update: ");
        String itemID = sc.nextLine();

        File file = new File("RestaurantCart.txt");
        Scanner read = new Scanner(file);

        ArrayList<String> list = new ArrayList<>();
        boolean updated = false;

        while (read.hasNextLine()) {
            String line = read.nextLine();
            String[] p = line.split(",");

            if (p[0].trim().equals(roomNo) && p[1].trim().equals(itemID)) {
                System.out.print("Enter new quantity: ");
                int newQty = sc.nextInt();
                sc.nextLine();

                list.add(roomNo + ", " + itemID + ", " + p[2].trim() + ", " + p[3].trim() + ", " + newQty);
                updated = true;
            } else {
                list.add(line);
            }
        }
        read.close();

        if (!updated) {
            System.out.println("Order not found.");
            return;
        }

        PrintWriter writer = new PrintWriter("RestaurantCart.txt");
        for (String s : list) writer.println(s);
        writer.close();

        System.out.println("\nOrder updated successfully!");

     } catch (Exception e) {
        System.out.println("Error updating order: " + e.getMessage());
     }
    }

    public static void add_to_cart() {
     try {
        Scanner sc = new Scanner(System.in);

        // Read menu
        File menuFile = new File("RestaurantMenu.txt");
        Scanner read = new Scanner(menuFile);

        ArrayList<String> menuList = new ArrayList<>();
        while (read.hasNextLine()) {
            menuList.add(read.nextLine());
        }
        read.close();

        // Input user details
        System.out.print("Enter your room number: ");
        String roomNo = sc.nextLine();

        System.out.print("Enter item ID: ");
        String itemID = sc.nextLine();

        boolean found = false;

        String itemName = "";
        int price = 0;

        // Find item in menu
        for (String line : menuList) {
            String[] p = line.split(",");
            if (p[0].trim().equals(itemID)) {
                itemName = p[1].trim();
                price = Integer.parseInt(p[2].trim());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Item ID does not exist.");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        // Add to cart
        PrintWriter writer = new PrintWriter(new FileWriter("RestaurantCart.txt", true));
        writer.println(roomNo + ", " + itemID + ", " + itemName + ", " + price + ", " + qty);
        writer.close();

        System.out.println("\nItem added to cart successfully!");

     } catch (Exception e) {
        System.out.println("Error adding to cart: " + e.getMessage());
     }
    }

    public static void search_menu() {
     try {
        Scanner sc = new Scanner(System.in);
        File file = new File("RestaurantMenu.txt");
        Scanner read = new Scanner(file);

        System.out.print("\nEnter item name to search: ");
        String search = sc.nextLine().toLowerCase();

        boolean found = false;

        System.out.println("\nSearch Results:");

        while (read.hasNextLine()) {
            String line = read.nextLine();
            if (line.toLowerCase().contains(search)) {
                System.out.println(line);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No item found!");
        }

        read.close();

     } catch (Exception e) {
        System.out.println("Error searching menu: " + e.getMessage());
     }
    }

    public static void view_menu() {
     try {
        File file = new File("RestaurantMenu.txt");
        Scanner read = new Scanner(file);

        System.out.println("\n----- Restaurant Menu -----");

        while (read.hasNextLine()) {
            System.out.println(read.nextLine());
        }

        read.close();

     } catch (Exception e) {
        System.out.println("Error reading menu: " + e.getMessage());
     }
    }

    public static void check_guest_status() {
    try {
        File file = new File("Guest.txt");
        Scanner read = new Scanner(file);

        System.out.println("\n----- Guest Status -----");

        while (read.hasNextLine()) {
            String line = read.nextLine();
            System.out.println(line);
        }

        read.close();

     } catch (Exception e) {
        System.out.println("Error reading guest status: " + e.getMessage());
     }
    }

    public static void user_generate_bill() {
    try {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter room number for bill: ");
        String roomNo = sc.nextLine();

        String guestName = "";
        int days = 0;

        // ===== Find guest in Guest.txt =====
        File guestfile = new File("Guest.txt");
        Scanner gr = new Scanner(guestfile);

        boolean guestFound = false;

        while (gr.hasNextLine()) {
            String[] g = gr.nextLine().split(",");
            if (g[0].trim().equals(roomNo)) {
                guestName = g[1].trim();
                days = Integer.parseInt(g[2].trim());
                guestFound = true;
                break;
            }
        }
        gr.close();

        if (!guestFound) {
            System.out.println("No guest found in this room!");
            return;
        }

        // ===== Get price from Room.txt =====
        File roomfile = new File("Room.txt");
        Scanner rr = new Scanner(roomfile);

        int price = 0;

        while (rr.hasNextLine()) {
            String[] r = rr.nextLine().split(",");
            if (r[0].trim().equals(roomNo)) {
                price = Integer.parseInt(r[2].trim());
                break;
            }
        }
        rr.close();

        int totalBill = price * days;

        // ===== Save to Bill.txt =====
        PrintWriter bw = new PrintWriter(new FileWriter("Bill.txt", true));
        bw.println(roomNo + ", " + guestName + ", " + totalBill);
        bw.close();

        System.out.println("\n----- BILL -----");
        System.out.println("Guest: " + guestName);
        System.out.println("Room No: " + roomNo);
        System.out.println("Days: " + days);
        System.out.println("Price per day: " + price);
        System.out.println("Total Bill = " + totalBill);

    } catch (Exception e) {
        System.out.println("Error generating bill: " + e.getMessage());
    }
   }

    public static void book_room() {
    try {
        Scanner sc = new Scanner(System.in);

        // ===== Show available rooms =====
        System.out.println("\n----- Available Rooms -----");
        File roomfile = new File("Room.txt");
        Scanner rf = new Scanner(roomfile);

        ArrayList<String> roomList = new ArrayList<>();
        while (rf.hasNextLine()) {
            roomList.add(rf.nextLine());
        }
        rf.close();

        for (String r : roomList) {
            if (r.contains("Available")) {
                System.out.println(r);
            }
        }

        // ===== Room selection =====
        System.out.print("\nEnter room number to book: ");
        String roomNo = sc.nextLine();

        System.out.print("Enter guest name: ");
        String guestName = sc.nextLine();

        System.out.print("Enter number of days: ");
        int days = sc.nextInt();
        sc.nextLine(); // fix input

        boolean roomFound = false;

        // ===== Update Room.txt =====
        PrintWriter writer = new PrintWriter("RoomTemp.txt");

        for (String r : roomList) {
            if (r.startsWith(roomNo + ",")) {
                roomFound = true;
                String[] parts = r.split(",");
                String type = parts[1].trim();
                String price = parts[2].trim();

                writer.println(roomNo + ", " + type + ", " + price + ", Booked");
            } else {
                writer.println(r);
            }
        }

        writer.close();
        new File("Room.txt").delete();
        new File("RoomTemp.txt").renameTo(new File("Room.txt"));

        if (!roomFound) {
            System.out.println("Room not found.");
            return;
        }

        // ===== Add guest record =====
        PrintWriter gw = new PrintWriter(new FileWriter("Guest.txt", true));
        gw.println(roomNo + ", " + guestName + ", " + days);
        gw.close();

        // ===== Add booking history =====
        PrintWriter hw = new PrintWriter(new FileWriter("BookingHistory.txt", true));
        hw.println(guestName + " booked room " + roomNo + " for " + days + " days.");
        hw.close();

        System.out.println("\nRoom booked successfully!");

    } catch (Exception e) {
        System.out.println("Error booking room: " + e.getMessage());
    }
}

    public static void check_all_rooms(){
         try {
        File file = new File("Room.txt");
        Scanner read = new Scanner(file);

        System.out.println("\n----- All Rooms -----");
        while (read.hasNextLine()) {
            String line = read.nextLine();
            System.out.println(line);
        }
        read.close();

      } catch (Exception e) {
           System.out.println("Error reading rooms: " + e.getMessage());
        }
    }
    public static void hotel_user(String name) {
    while (true) {
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Welcome " + name + "                                    |");
        System.out.println("|                   Hotel User Portal                  |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Press (0-4) to navigate to your option               |");
        System.out.println("| 1. Check All Rooms                                   |");
        System.out.println("| 2. Book Room                                         |");
        System.out.println("| 3. Generate Bill                                     |");
        System.out.println("| 4. Check Guest Status                                |");
        System.out.println("| 0. Return to previous menu                           |");
        System.out.println("|------------------------------------------------------|");

        int choice = sc.nextInt();
        sc.nextLine(); // input fix

        switch (choice) {
            case 1:
                check_all_rooms();
                break;

            case 2:
                book_room();
                break;

            case 3:
                user_generate_bill();
                break;

            case 4:
                check_guest_status();
                break;

            case 0:
                return;
            default:
                System.out.println("Invalid Input Please Try again! ");    
        }
    }
}

    
    public static void Resturant_user(String name) {
    while (true) {
        System.out.println("|------------------------------------------------------|");
        System.out.println("| Welcome " + name + "                                  |");
        System.out.println("|                Restaurant User Portal                |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| 1. View Menu                                         |");
        System.out.println("| 2. Search Menu                                       |");
        System.out.println("| 3. Add to Cart                                       |");
        System.out.println("| 4. Update Order                                      |");
        System.out.println("| 5. Place Order                                       |");
        System.out.println("| 6. Generate Bill                                     |");
        System.out.println("| 0. Back to previous menu                             |");
        System.out.println("|------------------------------------------------------|");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                view_menu();
                break;

            case 2:
                search_menu();
                break;

            case 3:
                add_to_cart();
                break;

            case 4:
                update_order();
                break;

            case 5:
                place_order();
                break;

            case 6:
                restaurant_generate_bill();
                break;

            case 0:
                return;
            default:
                System.out.println("Invalid input Please try again! ");    
        }
    }
}

    public static void user_menu(String name) {
    while (true) {
        System.out.println("|------------------------------------------------------|");
        System.out.println("|       Welcome " + name + " to User Portal            |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("| 1. View Hotel                                        |");
        System.out.println("| 2. View Restaurant                                   |");
        System.out.println("| 3. Back to previous menu                             |");
        System.out.println("|------------------------------------------------------|");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                hotel_user(name);
                break;

            case 2:
                Resturant_user(name);
                break;

            case 3:
                return; 
            default:
                System.out.println("Invalid input please try again!");
                
        }
    }
}

    public static void chkRoom(){
       
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
                int choice = sc.nextInt();
        
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
                int choice = sc.nextInt();
        
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
                int choice = sc.nextInt();
        
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
                int choice = sc.nextInt();
        
    }
    public static void check_menu(){
        try {
          File menufile = new File("ResturantMenu.txt");
          Scanner read = new Scanner(menufile);

          System.out.println("\n---------- Resturant Menu --------------");
          while(read.hasNextLine()){
            System.out.println(read.nextLine());
        }
         
        read.close();
        
     }catch (Exception e){
        System.out.println("Error reading menu: "+ e.getMessage());

     }
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
                int choice = sc.nextInt();
        
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
                int choice = sc.nextInt();
        
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
                int choice = sc.nextInt();
        
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
                int choice = sc.nextInt();
        
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
                int choice = sc.nextInt();
        
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
                int choice = sc.nextInt();
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
        int choice = sc.nextInt();
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
        int choice = sc.nextInt();
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
        System.out.println("|       Welcome "+ user +" to Admin  Portal               |");
        System.out.println("|------------------------------------------------------|");
        System.out.println("|            1. Manage Hotel                           |");
        System.out.println("|            2. Manage Resturant                       |");
        System.out.println("|            3. Back to previous Menu                  |");
        System.out.println("|------------------------------------------------------|");

        int choice = sc.nextInt();
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
        sc.nextLine();
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
        sc.nextLine();
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
        int choice = sc.nextInt();
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
        int choice = sc.nextInt();
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
    public static void initializeFiles() {
    try {

        // ============================
        // ROOM FILE
        // ============================
        File roomfile = new File("Room.txt");
        if (!roomfile.exists()) {

            PrintWriter writer = new PrintWriter(roomfile);

            writer.println("101, Single, 27000, Available");
            writer.println("102, Single, 27000, Available");
            writer.println("103, Double, 35000, Booked");
            writer.println("104, Luxury, 55000, Booked");
            writer.println("105, Double, 35000, Available");
            writer.println("106, Luxury, 55000, Available");
            writer.println("107, VIP Suite, 85000, Booked");
            writer.println("108, VIP Suite, 85000, Available");
            writer.println("109, Single, 27000, Available");
            writer.println("110, Family Suite, 65000, Available");

            writer.close();
            System.out.println("Room.txt created.");
        }


        // ============================
        // GUEST FILE
        // ============================
        File guestfile = new File("Guest.txt");
        if (!guestfile.exists()) {

            PrintWriter writer = new PrintWriter(guestfile);
            writer.println("103, Ali Hassan, 3");
            writer.println("104, Sara Khan, 2");
            writer.println("107, John Peter, 5");
            writer.close();

            System.out.println("Guest.txt created.");
        }


        // ============================
        // BILL FILE
        // ============================
        File billfile = new File("Bill.txt");
        if (!billfile.exists()) {

            PrintWriter writer = new PrintWriter(billfile);
            writer.println("103, Ali Hassan, 105000");
            writer.println("104, Sara Khan, 110000");
            writer.println("107, John Peter, 425000");
            writer.close();

            System.out.println("Bill.txt created.");
        }


        // ============================
        // SERVICES FILE
        // ============================
        File servicefile = new File("Services.txt");
        if (!servicefile.exists()) {

            PrintWriter writer = new PrintWriter(servicefile);
            writer.println("Laundry, 1500");
            writer.println("Breakfast, 2000");
            writer.println("Lunch, 3500");
            writer.println("Dinner, 4000");
            writer.println("Room Cleaning, 1000");
            writer.close();

            System.out.println("Services.txt created.");
        }


        // ============================================
        // RESTAURANT MENU FILE
        // ============================================
        File menuFile = new File("RestaurantMenu.txt");
        if (!menuFile.exists()) {

            PrintWriter writer = new PrintWriter(menuFile);
            writer.println("1, Burger, 300");
            writer.println("2, Pizza, 1800");
            writer.println("3, Biryani, 300");
            writer.println("4, Chicken Karahi, 1500");
            writer.println("5, Fries, 200");
            writer.println("6, Cold Drink, 150");
            writer.println("7, Ice Cream, 250");
            writer.println("8, Sandwich, 350");
            writer.println("9, Nihari, 150");
            writer.println("10, Shwarma, 250");
            writer.close();

            System.out.println("RestaurantMenu.txt created.");
        }


        // ============================================
        // RESTAURANT CART FILE
        // ============================================
        File cartFile = new File("RestaurantCart.txt");
        if (!cartFile.exists()) {
            cartFile.createNewFile(); // empty cart
            System.out.println("RestaurantCart.txt created.");
        }


        // ============================================
        // RESTAURANT ORDER FILE
        // ============================================
        File orderFile = new File("RestaurantOrders.txt");
        if (!orderFile.exists()) {
            orderFile.createNewFile();
            System.out.println("RestaurantOrders.txt created.");
        }


        // ============================================
        // RESTAURANT BILL FILE
        // ============================================
        File restBillFile = new File("RestaurantBills.txt");
        if (!restBillFile.exists()) {
            restBillFile.createNewFile();
            System.out.println("RestaurantBills.txt created.");
        }


        // ============================================
        // RESTAURANT ORDER HISTORY
        // ============================================
        File historyFile = new File("RestaurantHistory.txt");
        if (!historyFile.exists()) {
            historyFile.createNewFile();
            System.out.println("RestaurantHistory.txt created.");
        }


    } catch (Exception e) {
        System.out.println("Error initializing files: " + e.getMessage());
    }
 }


    public static void main(String[] args) {
        initializeFiles();
        intialMenu();
    }
}

