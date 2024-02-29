import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class BMICalculator {
    static String name; static int ft; static int inches; static int cm; static double weight; static double weightMetric; static double lowWeight; static double highWeight; static double bmiCurrent; static String statusCurrent;
    static Scanner input = new Scanner(System.in);
    static final String highlight = "\u001B[43m"; static final String reset = "\u001B[0m";

    public static void main(String[] args) {
        menu();
    }

    static void menu() {
        while(true) {
            System.out.print("My CSC 215 BMI Calculator Projects:\n   1. BMI, English\n   2. BMI, Metric\n\n[ USER MANUAL ] Enter an exclamation mark ! to end.\nPlease enter the version you want to try: ");
            String version = input.next();
            if(version.equals("!")) {
                System.exit(1);
            }
            System.out.println();
            if(version.toLowerCase().startsWith("e")) {
                input.nextLine();
                englishBMICalculator();
            } else {
                input.nextLine();
                metricBMICalculator();
            }
        }
    }

    static void englishBMICalculator() {
        welcome("English");
        getInputs();
        bmiCurrent = calculateBMI(ft, inches, weight);
        statusCurrent = calculateStatus(bmiCurrent);
        displayResults(bmiCurrent, statusCurrent);
        getRange("English");
        displayTable(lowWeight, highWeight);
        sayBye();
    }

    static void metricBMICalculator() {
        welcome("Metric");
        getInputsMetric();
        bmiCurrent = calculateBMIMetric(cm, weightMetric);
        statusCurrent = calculateStatus(bmiCurrent);
        displayResults(bmiCurrent, statusCurrent);
        getRange("Metric");
        displayTableMetric(lowWeight, highWeight);
        sayBye();
    }

    static void welcome(String version) {
        if(version.toLowerCase().startsWith("e")) {
            version = "English Version";
        } else {
            version = "Metric Version";
        }
        System.out.printf("-".repeat(90) + "\n-- Welcome to:\n-- %55s%s\n-- %175s", "BODY MASS INDEX (BMI) Computation, CSC 215, ", version, "by Allie Young\n" + "-".repeat(90) + "\n\n");
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

    static void getInputsMetric() {
        System.out.print("Please enter your full name: ");
        name = input.nextLine();
        System.out.printf("Please enter height in centimeters for %s: ", name);
        cm = input.nextInt();
        System.out.printf("Please enter weight in kilograms for %s: ", name);
        weightMetric = input.nextDouble();
    }

    static double calculateBMI(int ft, int inches, double weight) {
        return (weight/Math.pow(((ft*12)+inches), 2)) * 703;
    }

    static double calculateBMIMetric(int cm, double weight) {
        return (weight/Math.pow((cm/100.), 2));
    }

    static String calculateStatus(double bmi) {
        if(bmi < 18.5) {
            return "Underweight";
        } else if(bmi < 25) {
            return "Healthy Weight";
        } else if(bmi < 30) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    static void displayResults(double bmi, String status) {
        String date = new SimpleDateFormat("MMMM dd, yyyy").format(Calendar.getInstance().getTime());
        String time = new SimpleDateFormat("HH:mm:ss a").format(Calendar.getInstance().getTime());
        System.out.printf("\n-- SUMMARY REPORT for %s\n-- Date and Time: %21s at %s\n-- BMI: %23f (or %.1f if rounded)\n-- Weight Status:     %s\n\n", name, date, time, bmi, bmi, status);
    }

    static void getRange(String version) {
        String units;
        if(version.equals("English")) {
            units = "pounds";
        } else {
            units = "kilograms";
        }
        System.out.printf("Please enter a LOW weight in %s for %s: ", units, name);
        lowWeight = input.nextDouble();
        System.out.printf("Please enter a HIGH weight in %s for %s: ", units, name);
        highWeight = input.nextDouble();
    }

    static void displayTable(double low, double high) {
        System.out.printf("\n" + "-".repeat(55) + "\n|  %-10s|  %-10s|  %-25s|\n" + "-".repeat(55) + "\n", "WEIGHT", "BMI", "WEIGHT STATUS");
        double bmiLow = calculateBMI(ft, inches, low);
        String statusLow = calculateStatus(bmiLow);
        System.out.printf("|  %-10.2f|  %-10.2f|  %-18s" + highlight + "(LOW)" + reset + "  |\n", low, bmiLow, statusLow);
        if(low <= weight && weight <= low+5.5) {
            System.out.printf("|  %-10.2f|  %-10.2f|  %-17s(this)  |\n", weight, bmiCurrent, statusCurrent);
        }
        for(double i = low+5.5; i < high; i += 5.5) {
            if(weight == low+5.5) {
                continue;
            }
            double bmi = calculateBMI(ft, inches, i);
            String status = calculateStatus(bmi);
            System.out.printf("|  %-10.2f|  %-10.2f|  %-25s|\n", i, bmi, status);
            if(i <= weight && weight < i+5.5) {
                System.out.printf("|  %-10.2f|  %-10.2f|  %-17s(this)  |\n", weight, bmiCurrent, statusCurrent);
            }
        }
        double bmiHigh = calculateBMI(ft, inches, high);
        String statusHigh = calculateStatus(bmiHigh);
        System.out.printf("|  %-10.2f|  %-10.2f|  %-17s" + highlight + "(HIGH)" + reset + "  |\n" + "-".repeat(55), high, bmiHigh, statusHigh);
    }

    static void displayTableMetric(double low, double high) {
        System.out.printf("\n" + "-".repeat(55) + "\n|  %-10s|  %-10s|  %-25s|\n" + "-".repeat(55) + "\n", "WEIGHT", "BMI", "WEIGHT STATUS");
        double bmiLow = calculateBMIMetric(cm, low);
        String statusLow = calculateStatus(bmiLow);
        System.out.printf("|  %-10.2f|  %-10.2f|  %-18s" + highlight + "(LOW)" + reset + "  |\n", low, bmiLow, statusLow);
        if(low <= weightMetric && weightMetric <= low+2.5) {
            System.out.printf("|  %-10.2f|  %-10.2f|  %-17s(this)  |\n", weightMetric, bmiCurrent, statusCurrent);
        }
        for(double i = low+2.5; i < high; i += 2.5) {
            if(weightMetric == low+2.5) {
                continue;
            }
            double bmi = calculateBMIMetric(cm, i);
            String status = calculateStatus(bmi);
            System.out.printf("|  %-10.2f|  %-10.2f|  %-25s|\n", i, bmi, status);
            if(i <= weightMetric && weightMetric < i+2.5) {
                System.out.printf("|  %-10.2f|  %-10.2f|  %-17s(this)  |\n", weightMetric, bmiCurrent, statusCurrent);
            }
        }
        double bmiHigh = calculateBMIMetric(cm, high);
        String statusHigh = calculateStatus(bmiHigh);
        System.out.printf("|  %-10.2f|  %-10.2f|  %-17s" + highlight + "(HIGH)" + reset + "  |\n" + "-".repeat(55), high, bmiHigh, statusHigh);
    }

    static void sayBye() {
        String goodbye = new String[] {"Goodbye", "Cheers", "Poopaye", "Adios", "Ciao", "Sayonara", "Au revoir"}[(int)(Math.random()*7)];
        System.out.printf("\n\nThe SFSU Mashouf Wellness Center is at 755 Font Blvd.\n\n" + "-".repeat(90) + "\n-- Thank you for using my program, %s!\n-- %s!!!\n" + "-".repeat(90) + "\n\n", name, goodbye);
    }
}