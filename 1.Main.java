import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

class MissedCall {
    private String phoneNumber;
    private String callerName;
    private Date callTime;

    public MissedCall(String phoneNumber, String callerName, Date callTime) {
        this.phoneNumber = phoneNumber;
        this.callerName = callerName.equals("") ? "private caller" : callerName;
        this.callTime = callTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCallerName() {
        return callerName;
    }

    public Date getCallTime() {
        return callTime;
    }

    @Override
    public String toString() {
        return "Call from: " + phoneNumber + ", Name: " + callerName + ", Time: " + callTime;
    }
}

class Phone {
    private LinkedList<MissedCall> missedCalls;

    public Phone() {
        missedCalls = new LinkedList<>();
    }

    public void addMissedCall(String phoneNumber, String callerName) {
        missedCalls.add(new MissedCall(phoneNumber, callerName, new Date()));
    }

    public void displayMissedCalls() {
        Scanner scanner = new Scanner(System.in);

        while (!missedCalls.isEmpty()) {
            MissedCall call = missedCalls.getFirst();
            System.out.println("Missed Call from: " + call.getPhoneNumber());

            System.out.println("Select an option: ");
            System.out.println("1. Delete the call");
            System.out.println("2. Display call details");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    missedCalls.removeFirst();
                    System.out.println("Call deleted.");
                    break;
                case 2:
                    System.out.println(call);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("No more missed calls.");
    }

    public void printAllCalls() {
        if (missedCalls.isEmpty()) {
            System.out.println("No missed calls.");
        } else {
            for (MissedCall call : missedCalls) {
                System.out.println(call);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Phone phone = new Phone();

        phone.addMissedCall("1234567890", "John Doe");
        phone.addMissedCall("0987654321", "");
        phone.addMissedCall("5555555555", "Alice");
        phone.addMissedCall("4444444444", "Bob");
        phone.addMissedCall("3333333333", "");
        phone.addMissedCall("2222222222", "Charlie");
        phone.addMissedCall("1111111111", "");
        phone.addMissedCall("6666666666", "Dave");
        phone.addMissedCall("7777777777", "");
        phone.addMissedCall("8888888888", "Eve");
        phone.addMissedCall("9999999999", "");

        phone.displayMissedCalls();

        System.out.println("All remaining missed calls:");
        phone.printAllCalls();
    }
}
