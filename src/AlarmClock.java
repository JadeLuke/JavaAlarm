import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock implements Runnable{

    private final LocalTime alarmTime;
    private final String file;
    private final Scanner input;

    AlarmClock(LocalTime alarmTime, String file, Scanner input){
        this.alarmTime = alarmTime;
        this.file = file;
        this.input = input;
    }
    @Override
    public void run(){

        while (LocalTime.now().isBefore(alarmTime)){
           try {
               Thread.sleep(1000);

               LocalTime now = LocalTime.now();

               System.out.printf("\r%02d:%02d:%02d",
                       now.getHour(), now.getMinute(), now.getSecond());

            }
           catch (InterruptedException e){
               System.out.println("Thread was interrupted");
           }
        }
        System.out.println("\nAlarm noises");
        playSound(file);
     }

     private void playSound(String file){

        File audio = new File(file);


         try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(audio)) {
             Clip clip = AudioSystem.getClip();
             clip.open(audioStream);
             clip.start();

             System.out.println("Enter any character to stop alarm");
             input.next();
             clip.stop();
             input.close();
         }
         catch (UnsupportedAudioFileException e){
             System.out.println("Wrong format");
         }
         catch (LineUnavailableException e){
             System.out.println("Audio unavailable");
         }
         catch (IOException e){
             System.out.println("error reading file");
         }
     }
}
