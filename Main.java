import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int val1 = 0, val2 = 0;
        char operation;
        boolean correctInput;

        do {
            System.out.println(
                    "Welcome to simple bit calculator.\n" +
                            "Select a reference from the menu (by specifying its sign) and then two integers.\n" +
                            "To end the program, select any operation and enter the value \"0\"" + "in both numbers. \n\n" +
                            "======== Menu ========\n" +
                            "-> Add - \"+\" \n" +
                            "-> Subtract - \"-\" \n" +
                            "-> Multiplication - \"*\" \n" +
                            "-> Division - \"/\" \n" +
                            "======================\n"
            );

            System.out.print("Enter the first number: ");
            do{
                try {
                    val1 = sc.nextInt();
                    correctInput = true;
                } catch (Exception e){
                    System.out.print("Wrong input! Please try again: ");
                    correctInput = false;
                    sc.next();
                }
            } while (!correctInput);



            System.out.print("Enter the second number: ");
            do{
                try {
                    val2 = sc.nextInt();
                    correctInput = true;
                } catch (Exception e){
                    System.out.print("Wrong input! Please ry again: ");
                    correctInput = false;
                    sc.next();
                }
            } while (!correctInput);

            if (val1 != 0 && val2 != 0){



                do {
                    System.out.print("Enter the operation character from the menu: ");
                    operation = sc.next().charAt(0);

                    if (operation != '+' && operation != '-' && operation != '*' && operation != '/')
                        System.out.println("Incorrect operation character entered! \n" + "Please try again. ");

                } while (operation != '+' && operation != '-' && operation != '*' && operation != '/');

                String bitsOfVal1 = toBits(val1);
                String bitsOfVal2 = toBits(val2);
                System.out.printf("%11d - %32s\n", val1, bitsOfVal1);
                System.out.printf("%11d - %32s\n", val2, bitsOfVal2);

                int result;

                switch (operation) {
                    case '+':
                        result = add(val1,val2);
                        System.out.printf("%11d - %32s\n", result, toBits(result));
                        break;
                    case '-':
                        result = subtract(val1,val2);
                        System.out.printf("%11d - %32s\n", result, toBits(result));
                        break;
                    case '*':
                        result = multiply(val1,val2);
                        System.out.printf("%11d - %32s\n", result, toBits(result));
                        break;
                    case '/':
                        try {
                            result = divide(val1,val2);
                            System.out.printf("%11d - %32s\n", result, toBits(result));
                        } catch (Exception e){
                            System.out.println("We don't divide by 0!");
                        }
                        break;
                }


                System.out.println("Enter any character and press ENTER to continue.");
                sc.next();
            }
        } while (val1 != 0 || val2 != 0);
    }
    public static String toBits(int val){
        StringBuilder bitsOfVal = new StringBuilder();
        for (int i = 31; i >= 0; i--){
            int bit = (val >> i) & 1;
            bitsOfVal.append(bit);
        }
        return bitsOfVal.toString();
    }
    static int add(int x, int y) {
        while (y != 0) {
            int carry = x & y;
            x = x ^ y;
            y = carry << 1;
        }
        return x;
    }
    public static int subtract(int x, int y) {
        while (y != 0)
        {
            int borrow = (~x) & y;
            x = x ^ y;
            y = borrow << 1;
        }
        return x;
    }
    public static int multiply(int x, int y) {
        int product = 0;
        while (y != 0) {
            if ((y & 1) != 0) {
                product = add(product,x);
            }
            x <<= 1;
            y >>= 1;
        }
        return product;
    }
    public static int divide(int x, int y) throws ArithmeticException{
        if(y == 0) throw new ArithmeticException();
        int quotient = 0;
        while (x >= y) {
            x = subtract(x,y);
            quotient = add(quotient,1);
        }
        return quotient;
    }

}