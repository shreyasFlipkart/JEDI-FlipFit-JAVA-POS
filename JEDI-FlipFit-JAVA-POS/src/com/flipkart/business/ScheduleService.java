
public class ScheduleService implements ScheduleServiceInterface {

    public Schedule createSchedule(Date date, String slotId){
    }

    public Schedule getScheduleByDateAndSlotId(String SlotId, Date date){
    }

    public boolean modifySchedule(String scheduleId,int action){
    }

    public List<Schedule> checkAvailability(String centreID, Date date){

    }

    public List<Slot> getAllAvailableSlotsByDate(String centreID, Date date) {
    }

    public Schedule getSchedule(String scheduleID){
    }

    public Schedule getOrCreateSchedule(String slotId, Date date) {
    }
}