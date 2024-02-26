import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class BMIenglish {
    static String name; static int ft; static int inches; static double weight; static double lowWeight; static double highWeight;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        welcome();
        getInputs();
        double BMI = calculateBMI(ft, inches, weight);
        displayResults(BMI);
        getRange();
        displayTable();
    }

    static void welcome() {
        System.out.printf("-".repeat(90) + "\n-- Welcome to:\n-- %70s\n-- %175s", "BODY MASS INDEX (BMI) Computation, CSC 215, English Version", "by Allie Young\n" + "-".repeat(90) + "\n\n");
    }

    static void getInputs() {
        System.out.print("Please enter your full name: ");
        name = input.nextLine();
        System.out.printf("Please enter height in feet and inches for %s: ", name);
        ft = input.nextInt();
        inches = input.nextInt();
        System.out.printf("Please enter weight in pounds for %s: ", name);
        weight = input.nextDouble();
    }

    static double calculateBMI(int ft, int inches, double weight) {
        return (weight/Math.pow(((ft*12)+inches), 2)) * 703;
    }

    static void displayResults(double bmi) {
        String date = new SimpleDateFormat("MMMM dd, yyyy").format(Calendar.getInstance().getTime());
        String time = new SimpleDateFormat("HH:mm:ss a").format(Calendar.getInstance().getTime());
        String status;
        if(bmi < 18.5) {
            status = "Underweight";
        } else if(bmi < 25) {
            status = "Healthy Weight";
        } else if(bmi < 30) {
            status = "Overweight";
        } else {
            status = "Obesity";
        }
        System.out.printf("\n-- SUMMARY REPORT for %s\n-- Date and Time: %21s at %s\n-- BMI: %23f (or %.1f if rounded)\n-- Weight Status: %18s\n\n", name, date, time, bmi, bmi, status);
    }

    static void getRange() {
        System.out.printf("Please enter a LOW weight in pounds for %s: ", name);
        lowWeight = input.nextDouble();
        System.out.printf("Please enter a HIGH weight in pounds for %s: ", name);
        highWeight = input.nextDouble();
    }

    static void displayTable() {
        System.out.printf("\n" + "-".repeat(55) + "\n|  %-10s|  %-10s|  %-25s|\n" + "-".repeat(55), "WEIGHT", "BMI", "WEIGHT STATUS");

    }
}