# Expenses - Simple back end App prototype to manage your expenses

#### Overview
>Goal of this project is to offer a service layer to save and view your incomes and expenses.

In the every day life we need to handle several costs and collect some incomes. Make sense to have a smart tool to save
and keep under controls our expenses.

As a coding demo, idea of this project is to offer some basic services in order to be used from a mobile App application.

The back end solution is composed of two different architectural components:
* this Spring Boot REST Apis project
* one MySql database

#### UX/UI sketch
To keep it simple this demo, a sketch of the app that solve the problem is as below:

![App Sketch](./src/main/resources/images/app_sketch.png "App interface")

The screens are:
* "Main window": set based on the current year and month, it will display the saved expenses's list. The main window display also the total balance of the current month.
* "Add expense/income window": allow the user to create and store a new expense specifying the amount, category and a text note.

#### Typical solved Use cases
The features offered from the back end services are:
* display the expenses list for the current month
* retrieve the expenses list for a specific year and month 
* create a new expense by pre-defined category
* delete a given expense
* retrieve the expenses's categories (used during the expense creation for the drop down control).

The project contains the necessary setup to navigate the REST APIs using Swagger.

#### Author
This project has been created in February 2021 by Andrea Giassi.

Andrea he's an italian Agile professional and Software Engineer actives in web systems and services.
Since 2002, Andrea is working in the IT market for several different companies and start ups and it has contributed
to the success of several solutions and products.

About me:
https://www.linkedin.com/in/andreagiassi/


Please support this open source with a small donation here:

<a href="https://www.buymeacoffee.com/andreag" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>


