# POSca<br>
Built by: Christian Manibusan and Alex Nelson
## Description:
This program handles the order and payment functionalities of a point of sale system while using a external realtional database to store its data. The
interface of the system is also on a fully functioning GUI that the user operates.

## Tools Used:
Java, JavaSwing, JDBM, WindowBuilder, MySQL

## Functionality:
- Validation of username and password of an employee logging in through sql queries.<br>
<img src = "https://user-images.githubusercontent.com/84474876/216430057-0da04d01-d3ef-4824-a46b-cbf926baa549.png" width=50% height=50%><br>
- Order creation that allows user to input items into an order, includes price counter, void button and order display table, once an order is sent the information is then sent to the database with the data of *(OrderId, Subtotal, Total, Time, Server, Paid, PayType, Change, NumItems, Items)* more inforamtation regarding these attributes can be located in the Database design section.<br>
<img src="https://user-images.githubusercontent.com/84474876/216428089-e093261a-01c4-425a-a458-14ee8ae0cb19.png" width=50% height=50%><br>
- View all orders, displays all orders from the database onto a JTable for the user to see all  information on the order.<br>
<image src = "https://user-images.githubusercontent.com/84474876/216437949-ee5ddadb-6bab-4bc3-a782-4ab8ce45e538.png" width=50% height=50%><br>
- Pay functionality, this functionality allows users to select from two payment options *CASH or CARD* and when payed the program updates the order in the database with *Pay = TRUE*, *PayType = [payment option]*, *Change = [whether or not the cutomer needed change]*<br>
<img src = "https://user-images.githubusercontent.com/84474876/216436770-c35413bf-5102-4f1b-a116-b611eb6029e9.png" width=50% height=50%><br>
- Void a selected order, can only be used by user admins, by doing this it deletes the order locally and on the database.<br>

## Database Design:
- The current data base design includes the following entities and their attributes shown in the diagram below:<br>
<img src = "https://user-images.githubusercontent.com/84474876/217354131-c543a5ac-5580-4167-9211-df550680a9d7.png" width=50% height=50%><br>
*Create using https://app.quickdatabasediagrams.com/*<br>
- Current functionalities for the program to database include:
  - Selecting queries for drawing order data to local JTable of pos GUI.
  - Selecting queries for user login validation to compare against inputed credentials.
  - Selecting queries for validation of food prices.
  - Order table insertion (*sending order*)
  - Order table updating (*paying for order*)
  - Order table deletion (*voiding order*)
  - Order table *'OrderId'* validation (*to ensure unique id for every order no matter what instance of this program sent an order first*)
  
