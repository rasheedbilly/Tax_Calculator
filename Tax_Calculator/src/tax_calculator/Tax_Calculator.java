package tax_calculator;

import java.util.ArrayList;

/**
 * @author Rasheed
 */
public class Tax_Calculator {

    private Reader r;
    private double Total = 0, Sales_Taxes = 0;
    private ArrayList<String> receipt;

    public Tax_Calculator() {
        //init
        r = new Reader();
        receipt = new ArrayList<>();

        //read text file and add lines to ArrayList
        r.read("input3.txt");
        loadReceipt(receipt);
        printReceipt(receipt);

    }

    /**
     * Load receipt into an ArrayList
     *
     * @param ar
     */
    private void loadReceipt(ArrayList ar) {
        String good;
        String originalPrice;
        double tax;
        double unroundedPrice;
        double finalPrice;
        String line = "";

        for (int i = 0; i < r.lines.size(); i++) {
            //Name of good
            good = combineString(r.lines.get(i).split(" "), 1, r.lines.get(i).split(" ").length - 2);

            //Price before taxes
            originalPrice = r.lines.get(i).split(" ")[r.lines.get(i).split(" ").length - 1];

            //Corresponding tax
            tax = returnTaxes(combineString(r.lines.get(i).split(" "), 1, r.lines.get(i).split(" ").length - 2));
            tax = roundUpCent(tax);

            //Price with tax
            unroundedPrice = Double.valueOf(originalPrice) + Double.valueOf(originalPrice) * tax;

            //If price + tax, round up
            if (tax > 0.0) {
                //Round up to nearest 0.05 if tax present
                finalPrice = roundUpDime(unroundedPrice);
            } else {
                //If no tax round to nearest 0.01
                finalPrice = Math.round(unroundedPrice * 100.0) / 100.0;
            }

            //line of good and price on receipt
            line = good + ": " + String.valueOf(finalPrice);

            //Add total of goods
            Total += Double.valueOf(r.lines.get(i).split(" ")[r.lines.get(i).split(" ").length - 1]);

            //Add total of sales taxes
            Sales_Taxes += (Double.valueOf(r.lines.get(i).split(" ")[r.lines.get(i).split(" ").length - 1]))
                    * returnTaxes(combineString(r.lines.get(i).split(" "), 1, r.lines.get(i).split(" ").length - 2));

            //Add line of receipt to receipt
            if (checkDuplicates(ar, line)) {  //if line matches line found in receipt removes old line
                ar.add(updateList(ar, line));   //Update line and delete old match
            } else {
                ar.add(line);
            }

        }
        //Round total sale taxes
        Sales_Taxes = roundUpDime(Sales_Taxes);

        //Add 'Sales Taxes' and 'Total' at bottom of receipt
        ar.add("Sales Taxes: " + Sales_Taxes);
        ar.add("Total: " + Math.round(Double.valueOf(Total + Sales_Taxes) * 100.0) / 100.0);
    }

    /**
     * Updates item to include duplicate transaction
     *
     * @param line
     * @return
     */
    private String updateList(ArrayList<String> ar, String line) {
        String str = "";
        double price;
        String[] strArr = line.split(": ");

        //Remove old item
        for (int i = 0; i < ar.size(); i++) {
            if (ar.get(i).contains(line.split(":")[0])) {
                //Previous match found @ i

                if (line.endsWith(")")) {  //If item has more than two duplicates
                    //Update line
                    //Remove parenthesis
                    strArr[1] = strArr[1].replace("(", "");
                    strArr[1] = strArr[1].replace(")", "");

                    //split string into array
                    String[] strArr2 = strArr[1].split(" ");

                    //Update item count
                    strArr2[1] = String.valueOf(Double.valueOf(strArr2[1]) + 1);
                    strArr2[0] = String.valueOf(Double.valueOf(strArr2[1]) * Double.valueOf(strArr2[3]));

                    //Build return string
                    str += strArr[0] + ": (" + combineString(strArr2, 0, strArr2.length) + ")";
                } else {
                    price = Double.valueOf(strArr[1]);
                    str += strArr[0] + ": " + String.valueOf(price * 2) + " (2 @ " + String.valueOf(price) + ")";
                }
                ar.remove(i);
            }
        }

        return str;
    }

    /**
     * Checks the ArrayList (receipt) for duplicate items
     *
     * @param ar
     * @param line
     * @return
     */
    private boolean checkDuplicates(ArrayList<String> ar, String line) {

        //Compare items for duplicates
        for (int i = 0; i < ar.size(); i++) {
            if (ar.get(i).contains(line.split(":")[0])) {
                //ar.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Rounds to the nearest cent
     *
     * @param d
     * @return
     */
    private double roundUpCent(double d) {
        return Math.round(d * 100.0) / 100.0;
    }

    /**
     * Rounds up to the nearest dime
     *
     * @param d
     * @return
     */
    private double roundUpDime(double d) {
        //return ((double) (long) (d * 20 + 0.5)) / 20;
        return Math.ceil(d * 20.0) / 20.0;
    }

    /**
     * Combine a sub array into one string
     *
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    private String combineString(String[] arr, int begin, int end) {
        String t = "";
        for (int i = begin; i < end; i++) {
            if (i < end - 1) {
                t += arr[i] + " ";
            } else {
                t += arr[i];
            }
        }
        return t;
    }

    /**
     * Returns corresponding tax based on goods
     *
     * @param s
     * @return
     */
    private double returnTaxes(String s) {
        double tax = 0;
        if (s.contains("Book") | s.contains("Chocolate") | s.contains("pills")) {
            tax += 0;
        }
        if (s.contains("CD") | s.contains("perfume")) {
            tax += 0.10;
        }
        if (s.contains("Imported")) {
            tax += 0.05;
        }
        return tax;
    }

    /**
     * Prints an ArrayList, which would be the receipt
     *
     * @param ar
     */
    private void printReceipt(ArrayList ar) {
        for (int i = 0; i < ar.size(); i++) {
            System.out.println(ar.get(i));
        }
    }
}
