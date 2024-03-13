
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

enum MONTHS {

    Jan, Feb, March, Apr, May, June, July, Aug, Sep, Oct, Nov, Dec;
}

enum DAYS {

    Sun, Mon, Tue, Wed, Thur, Fri, Sat;
}

public class MyCalendar {

    static String title, date, startingTime, endingTime;

    public static void main(String[] args) {
        GregorianCalendar cal = new GregorianCalendar(); // capture today
        Scanner scanner = new Scanner(System.in);
        char sel;

        printCalendar(cal);

        while (true) {
            System.out.println("\nSelect one of the following options:");
            System.out.println("\t[L]oad");
            System.out.println("\t[V]iew by");
            System.out.println("\t[C]reate");
            System.out.println("\t[G]o to");
            System.out.println("\t[E]vent list");
            System.out.println("\t[D]elete");
            System.out.println("\t[Q]uit");
            System.out.print("Select : ");
            sel = scanner.next().toUpperCase().charAt(0);

            if (sel == 'L') {
                // load
                // opening input file
                StringBuilder readFile = null;
                try {
                    readFile = readFile("events.txt");
                } catch (Exception ex) {
                    System.out.println("This is the first run. Add events first.");
                }
            } else if (sel == 'V') {
                // view by
                System.out.print("[D]ay view or [M]onth view? : ");
                char view = scanner.next().toUpperCase().charAt(0);

                if (view == 'D') {
                    // day view
                    printTodayEvents(cal);
                } else if (view == 'M') {
                    // month view
                } else {
                    // error
                    System.out.println("invalid input.");
                }
            } else if (sel == 'C') {
                // create
                scanner = new Scanner(System.in);
                System.out.print("Title : ");
                title = scanner.nextLine();
                scanner = new Scanner(System.in);
                System.out.print("Date (MM/DD/YYYY) : ");
                date = scanner.nextLine();
                scanner = new Scanner(System.in);
                System.out.print("Starting time : ");
                startingTime = scanner.nextLine();
                scanner = new Scanner(System.in);
                System.out.print("Ending time : ");
                endingTime = scanner.nextLine();
                scanner = new Scanner(System.in);
                System.out.println("Done");
            } else if (sel == 'G') {
                // go to
            } else if (sel == 'E') {
                // events list
            } else if (sel == 'D') {
                // delete
            } else if (sel == 'Q') {
                // quit
                System.exit(0);
            } else {
                // error
                System.out.println("invalid input.");
            }
        }
    }

    public static void printCalendar(Calendar c) {
        MONTHS[] arrayOfMonths = MONTHS.values();
        DAYS[] arrayOfDays = DAYS.values();
        String month;
        int index = 0;

        // printing month and year
        month = arrayOfMonths[c.get(Calendar.MONTH)].toString();
        System.out.println(month + " " + c.get(Calendar.YEAR) + "\n");

        // printing days of week
        for (DAYS day : arrayOfDays) {
            System.out.print(day + "\t");
        }
        System.out.println("");

        // printing spaces before days
        for (int i = 0; i < (Calendar.DAY_OF_WEEK - 1); i++) {
            System.out.print(" \t");
            index++;
        }
        // printing days
        int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= max; i++) {
            // highlighting current day
            if (i == c.get(Calendar.DAY_OF_MONTH)) {
                System.out.print("[" + i + "]\t");
            } else {
                System.out.print(i + "\t");
            }
            index++;
            if (index % 7 == 0) {
                System.out.println("");
            }
        }
        System.out.println("");
    }

    // this method reads the text of a file with given file name
    public static StringBuilder readFile(String fileName) throws Exception {
        try {
            StringBuilder file = new StringBuilder();
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null) {
                file.append(line);
                file.append(System.lineSeparator());
                line = br.readLine();
            }
            return file;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void printTodayEvents(Calendar c) {
        MONTHS[] arrayOfMonths = MONTHS.values();
        DAYS[] arrayOfDays = DAYS.values();

        System.out.print("Today : ");
        System.out.print(arrayOfDays[c.get(Calendar.DAY_OF_WEEK) - 1]);
        System.out.print(" ");
        System.out.print(arrayOfMonths[c.get(Calendar.MONTH)]);
        System.out.print(" ");
        System.out.print(c.get(Calendar.DAY_OF_MONTH));
        System.out.print(" ");
        System.out.print(c.get(Calendar.YEAR));
    }
}
