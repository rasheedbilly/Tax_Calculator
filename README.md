# Tax_Calculator
Coding assignment

Brief explanation of design and assumptions.

Design:
My tax calculator takes a text file input, reads each item line by line, 
then transforms that data into a receipt. The receipt displays the items in the 
required output and includes the prices of items along with the total tax and 
total sum of all items.

Assumptions:
Input document must have on item per line
Input must be uniform in formating (ex. Quantity, item, price)

**NOTICE**
I was unclear on the following instruction:

"When calculating prices plus tax, round the total up to the nearest 5 cents."

When studying the output for "Imported bottle of perfume," two different rounding
methods were observed.

In OUTPUT 2, the item was rounded to the nearest 5 cents, however in OUTPUT 3,
the item was rounded to the nearest cent.

In response, I went with rounding every price plus tax to the nearest 5 cents.

Due to this, my output for INPUT 3 is slightly off.
