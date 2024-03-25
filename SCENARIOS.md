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