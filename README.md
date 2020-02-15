# PokeAccount
Android App for PokeAccount

## Summary
We are going to use Android Studio to Create a Mobile-based application called Poke Accounting, and this application has great features that can record your daily cost situation. Account function makes it easier to follow your account balance. It also provides different charts(Pie Chart, Line Chart, Timeline) to help you visualize your record.

Android - Platform

SQLite- Database

## Login Screen
4 pin password login system with a numerical keypad.

4 Steps:  
* Login  
* Enter old password  
* Enter new password  
* Re-enter password    

Digital Signature Verification: SHA256withRSA  

![Login Screen](/screenshot/login.PNG)

## Bottom Bar
Three clickable items represent three fragments  

Main functions:  
* onPageSelected  
* OnNavigationItemSelectedListener  

![Bottom Bar](/screenshot/bottom_bar.PNG)

## Transaction Screen 
It’s composed with TimeLine, a Button and the Bottom Bar  

Implementation:
* All the stuffs are hosted by MainActivity.  
* TimeLine uses Recycler View widget.  
* Each widget read the data from Event Model(Bean).  

Click Newbutton will start a new activity using explicit 
function.  

![Transaction Screen](/screenshot/transaction.PNG)

## Expense and Income Screen
Image button and a simple calculator compose this page.  
* Expense and Income are hosted by same Transaction 
Activity.
* Change between Expense and Income are smooth.
* Button could give user a touch feedback response. Submit button will go back and update MainActivity.

![Transaction Screen](/screenshot/expense.PNG)

## Account Screen
Account list screen:
* Display total balance
* List Account Info: company, type, balance
* Add new account
Account manage screen:
* Edit account info
* Delete account

![Account Screen](/screenshot/account.PNG)

## Chart Screen
Pie Charts:
* Income Pie Chart
* Expense Pie Chart
Categorized transactions in different types
Line Chart
* Monthly Balance Chart


![Chart Screen](/screenshot/chart.PNG)




