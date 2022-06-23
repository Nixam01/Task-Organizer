package aproproject_application;

import aproproject_application.gui.StartClientView;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimeManagement {
    public void executeEveryFullHour(LocalTime start, LocalTime end){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Controller controller = Controller.getInstance();
                if (LocalTime.now().isAfter(start) && LocalTime.now().isBefore(end)) controller.getDeadlineWarningNotifications();
            }
        };
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,1);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        timer.schedule(timerTask,calendar.getTime(),3600000);
    }
    public void executeEveryDay(LocalTime time){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Controller controller = Controller.getInstance();
                controller.getDailyNotifications();
                StartClientView.refresh();
            }
        };
        Calendar preferred = Calendar.getInstance();
        if (LocalTime.now().isAfter(time)) preferred.add(Calendar.DAY_OF_MONTH,1);
        preferred.set(Calendar.HOUR_OF_DAY,time.getHour());
        preferred.set(Calendar.MINUTE,time.getMinute());
        preferred.set(Calendar.SECOND,0);
        preferred.set(Calendar.MILLISECOND,0);
        timer.schedule(timerTask,preferred.getTime(),24*3600000);
    }

}
