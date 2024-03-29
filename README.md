# Music Festival Application

## Overview

The Music Festival Management System is a comprehensive Java-based application designed to simplify and automate the operations of managing music festivals. It encompasses functionalities ranging from ticket ordering, customer and employee management, to data loading and user interface interactions, making it a robust solution for festival organizers.


## Features

- **Order Management:** Handles all aspects related to ticket orders, including creation, modification, and tracking of orders.
- **Festival Management:** Provides tools for managing festival details such as dates, line-ups, and venue information.
- **User Management:** Incorporates a detailed system for managing users, including customers and employees, with distinct roles and permissions.
- **Data Mangement:** Utilizes a DataLoader to efficiently load and manage festival-related data.
- **Interactive UI:** Offers a FestivalUi class that facilitates interaction with the system through a user-friendly interface.
- **Role-based Access Control:** Differentiates between customer and manager roles, providing appropriate access and functionalities for each.
- **Scenario Testing:** Includes a set of predefined scenarios (SCENARIOS.md) to demonstrate and test the system's capabilities.

## How to Use

1. **Setting Up:** Ensure Java is installed on your system. Download the project files to your local machine.
2. **Launching the Application:** Open a terminal or command prompt, navigate to the project directory, and run the `MusicFestival.java` file to start the application.
3. **Interacting with the System:** Use the console-based interface provided by `FestivalUi.java` to interact with the system. Follow the on-screen instructions to navigate through the different functionalities.

## Project Structure

- `MusicFestival.java`: The main entry point for the application.
- `Festival.java`, `Order.java`, `User.java`, `Customer.java`, `Manager.java`, `Employee.java`: Core classes defining the business logic.
- `DataLoader.java`: Handles the loading of data into the system.
- `FestivalUi.java`: Provides the user interface for interaction.
- `SCENARIOS.md`: Describes various scenarios for testing the system.

## Acknowledgments

This project is the culmination of efforts from a dedicated team of developers who sought to create a flexible and intuitive system for music festival management. While the project has reached its completion and served its intended purpose, the contributions of its creators have laid a solid foundation for efficient festival management practices.

## License

This project is licensed under the MIT License - see the LICENSE file for details.