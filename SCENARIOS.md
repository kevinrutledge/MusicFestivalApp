# Scenario 1: New Customer Experience

## *Objective:* To verify the system's ability to handle new customer registration, product search, and order processing efficiently and accurately.

1. **Create New Account:**
  `Input`: Choose "Create a new account" option. Provide sample customer details:
```bash
Alex Rivera
alexrivera@mail.com
SecurePass123
456 Oak Lane
Springfield
IL
62704
```
`Expected Outcome`: Account creation confirmation. Customer details saved correctly in the system.

2. Log In as New Customer:

`Input:` Log in using the newly created account credentials:
```bash
alexrivera@mail.com
SecurePass123
```
`Expected Outcome:` Successful login. Welcome message displayed with the customer's name.

3. **Search for a Festival by Primary Key (Name):**

`Input:` Search for a specific Festival by its name.
```bash
Coachella Music Festival 2024
```
`Expected Outcome:` Festival details displayed. If not found, display an appropriate message.
```bash
Name: Coachella Music Festival 2024
Date: 2024-04-12
Price: $366.00
City: Indio
State: CA
Tickets Remaining: 316
Genres:
  Electronic
  Hip Hop
  House
  Indie
  Pop
  Rock
Featured Artists:
  Lana Del Rey
  Tyler, The Creator
  Doja Cat
  No Doubt
```

4. **Search for a Festival by Secondary Key (Date + City):**
`Input:` Search for Festival by a secondary key.
```bash
2024-05-04, Las Vegas
```
`Expected Outcome:` List of Festivals matching the secondary key displayed. If no matches, display an appropriate message.
```bash
Name: Lovers & Friends Fest 2024
Date: 2024-05-04
Price: $470.00
City: Las Vegas
State: NV
Tickets Remaining: 0
Genres:
  Hip Hop
  Pop
  R&B
Featured Artists:
  Usher
  Lil Wayne
  Alicia Keys
  Gwen Stefani
```

5. **Place an Order Encompassing Festivals Sold Out and In Stock:**

`Input:` Select a Festival to order. Loop to ask Customer if they want to continue shopping for Festivals. (0 - no, 1 - yes). If no, ask Customer for shipping speed.
```bash
Lovers & Friends Fest 2024
1
Coachella Music Festival 2024
1
Bottlerock Napa Valley 2024
1
Electric Daisy Carnival Orlando 2024
1
2024-07-04, New Orleans
0
```
`Expected Outcome:` User is Prompted Out of Stock Festival. User is Prompted Festival Not in Catalogue. List the LinkedList Festivals. 
```bash
<out of stock message here>

<continue loop and ask Customer for Festivals to order message here>

<valid entry and Festival added message here>

<continue loop and ask Customer for Festivals to order message here>

<valid entry and Festival added message here>

<continue loop and ask Customer for Festivals to order message here>

<invalid entry message here>

<continue loop and ask Customer for Festivals to order message here>

<valid entry and Festival added message here>

<Exit loop>

<Go back to menu>
```

7. **Review Purchase:**

`Input:` Navigate to "View Purchases."
```bash
<int or char key to view purchase>

<int or char key to confirm purchase>

<add to unshippedOrders>
```
`Expected Outcome:` Display the temporary placed order along with any previous orders.
```bash
Order ID: <generated order id upon processing data and temporary orders>
Name: Alex Rivera
Date Purchased: 2024-03-26
Music Festivals:
  Name: Coachella Music Festival 2024
  Date: 2024-04-12
  Price: $366.00
  City: Indio
  State: CA
  Tickets Remaining: 316
  Genres:
    Electronic
    Hip Hop
    House
    Indie
    Pop
    Rock
  Featured Artists:
    Lana Del Rey
    Tyler, The Creator
    Doja Cat
    No Doubt
  
  Name: Bottlerock Napa Valley 2024
  Date: 2024-05-24
  Price: $233.00
  City: Napa
  State: CA
  Tickets: Remaining: 35
  Genres:
    Alternative
    Americana
    Hip Hop
    Latin
    Pop
    R&B
    Reggae
    Rock
  Featured Artists:
    Pearl Jam
    Ed Sheeran
    Stevie Nicks
    Megan Thee Stallion
    
  Name: Essence Music Festival 2024
  Date: 2024-07-04
  Price:$160.00
  City: New Orleans
  State: LA
  Tickets Remaining: 60
  Genres:
    Hip Hop
    Pop
    R&B
  Featured Artists:
    Missy Elliott
    Ms. Lauryn Hill
    Megan Thee Stallion
    Jill Scott
    Wizkid

<Prompt User to confirm purchase message>

<Congratulate User on purchase message>
```

# Scenario 2: Employee Handling Orders

## *Objective:* To assess the functionality available to employees. The scenario emphases order management and customer service.

- **Employee Login:**
  
`Input:` Select "Login as an Employee" and enter employee credentials.
```bash
4
downton_abbas@yahoo.com
1@m@bb@s
```
`Expected Outcome:` Successful login with access to employee-specific functionalities.
```bash
<Main menu displayed here>
Logging in as employee:
Enter your email: 
Enter your password: 
Welcome employee Mohammed Abbas
```

- **Search for an Order:**
  
`Input:` Use the order ID and then customer name to search for orders.
  ```bash
<int or char key to view an order>
<int or char key to view an order by orderID>
100000007
<int or char key to exit loop of viewing order by orderID>
<int or char key to view an order>
<int or char key to view orders by firstName and lastName>
James Brown
<int or char key to exit loop of viewing order by firstName and lastName>
```
`Expected Outcome:` Order details displayed for the searched criteria.
```bash
<Employee menu displayed>

<Enter *int or char* to view an order by ID or *int or char* to view an order by first and last name message>

<Enter Oder ID message>

Order ID: 100000007
Name: Stacey Cahill
Date purchased: 2024-03-13
Music Festivals:
  Sonic Temple Festival 2024
  Cruel World Festival 2024
  Hard Summer 2024
Total Price: $653.17
Shipping Speed: STANDARD
Is Shipped Status: false

<Enter *int or char* key to view more orders by Order ID message>

<Employee menu displayed>

<Enter *int or char* to view an order by ID or *int or char* to view an order by first and last name message>

<Enter First name followed by Last name message>

James Brown

Order ID: 100000013
Name: James Brown
Date Purchased: 2024-03-14
Music Festivals:
  Name: Bottlerock Napa Valley 2024
  Date: 2024-05-24
  Price: $233.00
  City: Napa
  State: CA
  Tickets Remaing: 35
  Genres:
    Alternative
    Americana
    Hip Hop
    Latin
    Pop
    R&B
    Reggae
    Rock
  Featured Artists:
    Pearl Jam
    Ed Sheeran
    Stevie Nicks
    Megan Thee Stallion
  
  Name: Summerfest 2024
  Date: 2024-06-20
  Price: $39.00
  City: Milwaukee
  State: WI
  Tickets Remaining: 140
  Genres:
    Alternative
    Bluegrass
    Country
    Electronic
    Folk
    Hip Hop
    Indie
    Pop
    Rock
  Featured Artists:
    Carly Rae Jepsen
    Keith Urban
    AJR
    Motley Crue
  
Total Price: $272
Shipping Speed: OVERNIGHT
Is Shipped Status: false

Order ID: 100000011
Name: James Brown
Date Purchased: 2024-02-14
Music Festivals:
  Name: Coachella Music Festival 2024
  Date: 2024-04-12
  Price: $366.00
  City: Indio
  State: CA
  Tickets Remaining: 316
  Genres:
    Electronic
    Hip Hop
    House
    Indie
    Pop
    Rock
  Featured Artists:
    Lana Del Rey
    Tyler, The Creator
    Doja Cat
    No Doubt

Total Price: $366.00
Shipping Speed: STANDARD
Is Shipped Status: true

Order ID: 100000017
Name: James Brown
Date Purchased: 2024-02-21
Music Festivals:
  Name: Sick New World 2024
  Date: 2024-04-27
  Price: $392.00
  City: Las Vegas
  State: NV
  Tickets Remaining: 149
  Genres:
    Rock
  Featured Artists:
    System of a Down
    Slipknot
    Alice in Chains
    Bring me the Horizon
    
  Name: Hard Summer 2024
  Date: 2024-08-08
  Price: $202.18
  City: Hollywood
  State: CA
  Tickets Remaining: 120
  Genres:
    Electronic
    Hip Hop
    House
  Featured Artists:
    Disclosure
    Rezzmau5
    FISHER
    Chris Lake
    
Total Price: $594.18
Shipping Speed: RUSH
Is Shipped Status: true

<Enter *int or char* key to view more orders by first and last name message>

<Employee menu displayed>
```

- **View and Prioritize Orders:**
  
`Input:` View orders with the highest priority.
```bash
<int or char key to display unshipped orders>
```
`Expected Outcome:` Orders displayed in order of priority, starting with the highest.
```bash
<Employee menu displayed>

<Orders displayed from heap toString>
```

- **Ship an Order:**

`Input:` Select an order to ship and confirm shipment.
```bash
<int or char key to process order that is front of the heap (highest priority)>

<int or char key to process new order that is front of the heap>

<int or char key to exit shipping an order menu>
```  
`Expected Outcome:` Order status updated to shipped. The order is moved from the unshipped to the shipped orders list.
```bash

```

- **Session Closure:**

`Input:` Choose to quit and write changes to the file.
```bash

```  
`Expected Outcome:` All changes are saved. The system prepares for the next login session without data loss.
```bash

```

# Scenario 3: Manager Updating Catalogue and Handling Products

## *Objective:* To test the system's capability to accommodate product catalogue updates and inventory management by a manager.

- **Manager Login:**
  
`Input:` Choose "Login as a Manager" and use manager credentials.
```bash
tamtastic@gmail.com
1qaz2wsx
```
`Expected Outcome:` Successful login with access to advanced managerial options.
```bash
Welcome manager Tamara White
```

- **Update Festival Catalogue:**

`Input:` Search for a product by its primary key and update its details.
```bash
<int or char key to enter update Festival>

Coachella Music Festival 2024

<int or char key to change price>

450.00

<int or char key to continue making changes>

<int or char key to add featured artists>

Peso Pluma, Blur, J Balvin

<int or char key to confirm changes>
```
`Expected Outcome:` Product details updated in the system. Confirmation of update displayed.
```bash
<enter Festival by its primary key and update details message>

Music festival to update:
  Name: Coachella Music Festival 2024
  Date: 2024-04-12
  Price: $366.00
  City: Indio
  Staate: CA
  Tickets Remaining: 316
  Genres:
    Electronic
    Hip Hop
    House
    Indie
    Pop
    Rock
  Featured Artists
    Lana Del Rey
    Tyler, The Creator
    Doja Cat
    No Doubt

<Display menu of attributes to change of Festival>

<Change price by amount message>

<Continue making changes or exit message>

<Display menu of attributes to change of Festival>

<Add artists seperated by commas message>

<Continue making changes or exit message>

Music festival updated:
  Name: Coachella Music Festival 2024
  Date: 2024-04-12
  Price: $450.00
  City: Indio
  Staate: CA
  Tickets Remaining: 316
  Genres:
    Electronic
    Hip Hop
    House
    Indie
    Pop
    Rock
  Featured Artists
    Lana Del Rey
    Tyler, The Creator
    Doja Cat
    No Doubt
    Peso Pluma
    Blur
    J Balvin
```

- **Add New Festival:**
  
`Input:` Provide details for a new product.
```bash
<int or char key to add new Festival>
Lollapalooza Chicago 2024
2024-08-01
399.00
Chicago
IL
10000
Electronic, Pop, Rock
9
SZA
Tyler, The Creator
Blink-182
The Killers
Future X Metro Boomin
Hozier
Stray Kids
Melanie Martinez
Skrillex
<int or char key to exit loop to add festivals>
```
`Expected Outcome:` New Festival added to the catalogue. Confirmation message displayed.
```bash
<Manager menu displayed>
<Enter the name message>
<Enter the date message>
<Enter the price message>
<Enter the city message>
<Enter the state message>
<Enter the tickets remaining message>
<Enter a comma delimited string of genres message>
<Enter how many featured artists message>
**** Probably no message displayed during featured artists entries ****

New Added Festival:
  Name: Lollapalooza Chicago 2024
  Date: 2024-08-01
  Price: $399.00
  City: Chicago
  State: IL
  Tickets remaining: 10000
  Genres:
    Electronic
    Pop
    Rock
  Featured Artists:
    SZA
    Tyler, The Creator
    Blink-182
    The Killers
    Future X Metro Boomin
    Hozier
    Stray Kids
    Melanie Martinez
    Skrillex

<Prompt Manager if they want to add more Festivals message>

<Manager menu displayed>
```

- **Remove a Product:**
  
`Input:` Search for a product by its primary key and confirm its removal.
```bash
<int or char key to remove a Festival>

Ultra Music Festival Miami 2024

<int or char key to confirm removal>

<int or char key to exit loop of removing festivals>
```
`Expected Outcome:` Product removed from the catalogue. Confirmation of removal displayed.
```bash
<Manager menu displayed>

<Enter Music Festival to remove by its name message>

Removing Music Festival:
  Name: Ultra Music Festival Miami 2024
  Date: 2024-03-24
  Price: $399.95
  City: Miami
  State: FL
  Tickets remaining: 0
  Genres:
    Electronic
  Featured Artists:
    Calvin Harris
    David Guetta
    Martin Garrix
    Tiesto

<Confirm? message>

Removed: Ultra Music Festival Miami 2024
<Remove more festivals? message>

<Manager menu displayed>
```

- **Session Closure:**
  
`Input:` End the session by writing changes to the file.
```bash
<int or char key to exit Manager menu>

<int or char key to exit session>
```
`Expected Outcome:` All changes are correctly saved, ensuring data integrity for the next session.
```bash
<Manager menu displayed>

<Main menu displayed>

<Saving changes to files message>

Goodbye!
```