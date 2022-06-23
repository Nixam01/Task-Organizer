package aproproject_application;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Settings {
    private Properties settings;
    private String fileName;
    private static Settings instance;

    public static Settings getInstance(String fileName) {
        if (instance == null){
            instance = new Settings(fileName);
        }
        return instance;
    }

    private Settings(Properties settings, String fileName) {
        this.settings = settings;
        this.fileName = fileName;
    }
    private Settings(String fileName) {
        this.fileName = fileName;
        settings = new Properties();
    }

    public void saveSettings() throws IOException {
        OutputStream fos = new FileOutputStream(fileName);
        settings.store(fos,null);
        fos.close();
    }
    public void loadSettings() throws IOException {
        InputStream fis = new FileInputStream(fileName);
        settings.load(fis);
        fis.close();
    }

//use LocalTime.of(hh,mm)
    public void setProperties(LocalTime PreferredDailyNotificationTime, LocalTime StartOfWorkingTime, LocalTime EndOfWorkingTime, int NumberOfDailyNotifications, int DEADLINENotificationBeforeDeadline){
        settings.setProperty("PreferredDailyNotificationTime", PreferredDailyNotificationTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        settings.setProperty("StartOfWorkingTime", StartOfWorkingTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        settings.setProperty("EndOfWorkingTime", EndOfWorkingTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        settings.setProperty("NumberOfDailyNotifications", String.valueOf(NumberOfDailyNotifications));
        settings.setProperty("DEADLINENotificationBeforeDeadline", String.valueOf(DEADLINENotificationBeforeDeadline));
    }

    public LocalTime getPreferredDailyNotificationTime(){
        return LocalTime.parse(settings.getProperty("PreferredDailyNotificationTime"));
    }

    public LocalTime getStartOfWorkingTime(){
        return LocalTime.parse(settings.getProperty("StartOfWorkingTime"));
    }

    public LocalTime getEndOfWorkingTime(){
        return LocalTime.parse(settings.getProperty("EndOfWorkingTime"));
    }

    public int getNumberOfDailyNotifications(){
        return Integer.parseInt(settings.getProperty("NumberOfDailyNotifications"));
    }

    public int getDEADLINENotificationBeforeDeadline(){
        return Integer.parseInt(settings.getProperty("DEADLINENotificationBeforeDeadline"));
    }

}
