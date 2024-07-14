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
    private List<MissedCall> missedCalls;

    public Phone() {
        missedCalls = new LinkedList<>();
    }

    public void addMissedCall(String phoneNumber, String callerName) {
        if (missedCalls.size() == 10) {
            missedCalls.remove(0); // Remove the oldest call
        }
        missedCalls.add(new MissedCall(phoneNumber, callerName, new Date()));
    }

    public void displayMissedCalls() {
        if (missedCalls.isEmpty()) {
            System.out.println("No missed calls.");
            return;
        }

        int index = 1;
        for (MissedCall call : missedCalls) {
            System.out.println(index + ". " + call);
            index++;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the call to view details or delete (0 to exit): ");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= missedCalls.size()) {
            MissedCall selectedCall = missedCalls.get(choice - 1);
            System.out.println(selectedCall);
            System.out.print("Do you want to delete this call? (yes/no): ");
            String deleteChoice = scanner.next();
            if (deleteChoice.equalsIgnoreCase("yes")) {
                missedCalls.remove(choice - 1);
                System.out.println("Call deleted.");
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
    }
}
