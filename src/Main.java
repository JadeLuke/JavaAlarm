import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

Scanner input = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmtime = null;
        String file = "Tinashe - Nasty (Official Video).wav";
   while (alarmtime == null) {


    try {


        System.out.println("Enter Alarm Time");
        String inputTime = input.next();

        alarmtime = LocalTime.parse(inputTime, formatter);
        System.out.println("Alarm set for " + alarmtime);
    } catch (DateTimeException e) {
        System.out.println("Please enter valid time");

    }

}
AlarmClock alarmClock = new AlarmClock(alarmtime, file, input);
   Thread alarmThread = new Thread(alarmClock);
   alarmThread.start();
    }
}