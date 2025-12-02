# 𝙋𝙧𝙤𝙟𝙚𝙘𝙩 𝙆𝘼𝙇𝙄𝙆𝘼𝙎𝘼𝙉: 𝙏𝙧𝙖𝙘𝙞𝙣𝙜 𝙋𝙡𝙖𝙨𝙩𝙞𝙘 𝙒𝙖𝙨𝙩𝙚 𝙍𝙚𝙙𝙪𝙘𝙩𝙞𝙤𝙣 🌍♻️

## Table of Contents

- [Project Description](#projectdescription)
- [OOP Concepts Applied](#oopconceptsapplied)
- [Program Structure](#programstructure)
- [How to Run the Program](#howtoruntheprogram)
- [Sample Output](#sampleoutput)
- [Author and Acknowledgement](#authorandacknowledgement)

## Project Description
**Project KALIKASAN: Tracing Plastic Waste Reduction** is a Java-based program that tracks and records each user’s plastic waste reduction activities. The system allows users to input data about their daily plastic usage and recycling habits. Using Java’s data handling and file management features, it securely stores and updates each user’s progress. It also generates reports showing how much plastic waste each user has reduced over time. Through this project, Java technology is used to promote environmental responsibility and encourage sustainable living practices.

## OOP Concepts Applied
In this project, we used several Object-Oriented Programming (OOP) concepts to make the system more organized and efficient. We started by applying **encapsulation**, which allowed us to hide sensitive data like user records inside classes and only expose what was necessary through getters and setters. This kept the program safe from accidental data changes. We also used **inheritance** to create reusable class structures, making the code easier to maintain and extend in the future. Another important concept we applied was **polymorphism**, which helped us create flexible methods that can behave differently depending on the object calling them. This made the program more adaptable when dealing with different types of user data. The use of **abstraction** also simplified complex processes by breaking them down into clear, manageable functions. applying these OOP principles made the project cleaner, more scalable, and easier to understand.

## Program Structure
The program is structured around several main classes, each designed to handle a specific role. At the center of everything is the **User** class, which stores the user’s name, daily waste details, and progress data. We also created a **PlasticTracker** class that manages all the calculations and updates related to plastic waste reduction. The **FileHandler** class is responsible for reading and writing user data so the system can store progress securely. Another important class is the **ReportGenerator**, which compiles the user’s data and converts it into readable progress reports. All these classes work together using simple relationships that make the system easy to navigate. The structure follows a text-based hierarchy where each class has one specific job, helping avoid confusion and unnecessary complications. This organized setup allows the program to run smoothly and makes future improvements easier to add.

## How to Run the Program
Follow the steps below to compile and run the program using the command line:
  
**1. Save the Program**

    Copy the entire Java code into a file named KalikasanApp.java.

    (Make sure all classes are inside the same file.)
**2. Open the Terminal or Command Prompt**

Navigate to the folder where KalikasanApp.java is saved using:

   cd path/to/your/folder
   
**3. Compile the Program**

Type:

       javac KalikasanApp.java

(If there are no errors, this will generate multiple .class files (one for each class)). 

**4. Run the Program**
   
    Type:
    
     java KalikasanApp

(You should now see the main menu, and you can begin interacting with the program by choosing options (1–5 or 0)).

## Sample Output
Welcome to KALIKASAN — Plastic Waste Tracker

Menu:
1. Create user
2. Add daily entry
3. Show user reports
4. System summary
5. Delete user
0. Exit

Choose: 1
Enter name: Ana
Choose type (1 = Student, 2 = Household, 3 = Business): 1
Created user: Ana (ID = 1)




Menu:
1. Create user
2. Add daily entry
3. Show user reports
4. System summary
5. Delete user
0. Exit

Choose: 2
Enter user ID: 1
Date (MM-DD-YYYY) or leave blank for today:
Plastic used (grams): 120
Plastic recycled/avoided (grams): 30
Entry added.

Amazing work! You're making a real difference!



Menu:
1. Create user
2. Add daily entry
3. Show user reports
4. System summary
5. Delete user
0. Exit

Choose: 3

User 1 — Ana (Student)

Entries:
  2025-11-27 — used: 120g, recycled/avoided: 30g

Total reduced (grams): 30



## Author and Acknowledgement
Author:
Lance Caringal

Ashley Quinones

Gwen Mendoza

We would like to express our sincere gratitude to **Sir Emmanuel Charlie Enriquez**, our instructor, for teaching us the principles of Java OOP, guiding and support us throughout the development of this project. His advice and encouragement have added to our growth in understanding, and we are grateful for the knowledge. Our group, for the cooperation, ideas, and constructive feedback that helped improve the final output.

