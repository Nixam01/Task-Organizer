package aproproject.Tasks;

import java.util.Date;

public enum DeadlinePriority {
    HIGH(9), MEDIUM_HIGH(6), MEDIUM(3), LOW(0);
    private int value;
    DeadlinePriority(int value){
        this.value = value;
    }

    /**
     * zwraca wartość priorytetu
     * @return value
     */
    public int getPriorityValue(){
        return value;
    }
}
