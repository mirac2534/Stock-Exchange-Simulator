

# Project Name: Stock Market Simulator

Date: 2024.09.02

The purpose of this application is to create a simulator version of a stock market application. This project is written in the java programming language. File operations will be applied to make the data persistent. The file name in the project is set according to the extension of my computer. Don't forget to change the file extension.  


## Planning

### 1. Menu() Function

### 2. Investor Profile

- There will be three options within this.

- If there is no profile, add a profile. Name, surname, ID, and portfolio number information will be taken. These will be saved to the profile.txt file.

- If there is a profile, it will show that profile.

- If the user wants to update the profile, the update process will be performed.

### 3. Stock Purchase Screen

- Take the name of the stock to be purchased, the stock price, the number of shares, and the date of the purchase. The total value will be automatically calculated. If such a stock has been purchased before:

- It updates the new cost, the new quantity, the new total value, and the new date. After the purchase process is completed, this transaction is recorded in the transaction history file.

- If there is no such stock in the records, it writes the name, quantity, price, total value, and date information to the file. After the purchase process is completed, this transaction is recorded in the transaction history file.

- Finally, it returns the message "Stock successfully purchased."

### 4. Stock Sale Screen

- First, take the stock name. If correct, take the quantity to be sold, the sale price, and the sale date information.

- If the entered stock quantity is less than or equal to 0, it returns an error message and exits the system.

- If the entered stock quantity is less than the recorded stock quantity, it updates the new stock cost, new stock quantity, new total stock value, and the date in the recorded file.

- If there is a profit, it returns the message "You are in profit, your profit is ...". Then this sale transaction is recorded in the transaction history file.

- If there is a loss, it returns the message "You are at a loss, your loss is ...". Then this sale transaction is recorded in the transaction history file.

- If the entered stock quantity is equal to the recorded stock quantity, it is deleted from the recorded file.

- If there is a profit, it returns the message "You are in profit, your profit is ...". Then this sale transaction is recorded in the transaction history file.

- If there is a loss, it returns the message "You are at a loss, your loss is ...". Then this sale transaction is recorded in the transaction history file.

- If the entered stock quantity is greater than the recorded stock quantity, it returns an error message and exits the system.

- If the entered stock name is incorrect, it returns an error message and exits the system.

- Finally, it returns the message "Stock successfully sold."

### 5. Portfolio Screen

Displays the stock name, stock cost, stock quantity, total stock value, and total value of all stocks.

### 6. Transaction History Screen

After buying or selling a stock, displays the stock name, purchase/sale price, quantity bought/sold, total value of the bought/sold stock, and the date.

### 7. Exit

To exit the simulator.

### 8. Main() Function

First, a password will be requested. If the password is correct, it will proceed to the menu function. If not, the system will exit.
