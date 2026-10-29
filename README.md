# SmartInventory: Java OOP & File Management System

SmartInventory is an interactive inventory management system built in Java. Developed as a practical transition from academic algorithms to end-to-end application design, this project focuses on Object-Oriented Programming (OOP) principles and local data persistence.

## Tech Stack & Concepts
* **Language:** Java
* **Core Concepts:** Object-Oriented Programming (Classes, Inheritance, Encapsulation), Dynamic Data Structures (`ArrayList`), File I/O operations, String manipulation.

## Core Features

* **Data Persistence (Load & Save):** 
  * Automatically parses and loads existing stock from a local text file (`stoc.txt`) upon initialization using `File` and `Scanner`.
  * Exports and overwrites the final inventory state to the hard drive upon application exit via `FileWriter`, ensuring no data loss between sessions.
* **Inventory Management (Create & Read):** Allows users to seamlessly add new product entries to the dynamic list and perform search queries to check current stock levels.
* **Sales Processing (Update):** Implements dedicated sales logic to decrease stock safely. Includes state validation (e.g., negative stock protection) to ensure data integrity during transactions.

##  Technical Highlights
* **OOP Architecture:** Models the real-world inventory using customized classes, constructors, and inheritance (`extends`) to separate logic and maintain clean code.
* **Dynamic Memory:** Utilizes Java's `ArrayList` for flexible data storage, allowing the inventory system to scale dynamically without the fixed limits of standard arrays.
* **Data Parsing:** Employs string manipulation techniques (like `.split()`) to deserialize plain text data into complex Java objects.

---
### Getting Started
1. Clone the repository: `git clone https://github.com/yourusername/SmartInventory.git`
2. Ensure you have the Java Development Kit (JDK) installed.
3. Compile and run the main class.
4. Make sure `stoc.txt` is in the root directory for the app to load initial data.
