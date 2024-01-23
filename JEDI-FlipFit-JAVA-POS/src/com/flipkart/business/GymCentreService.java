
public class GymCentreService implements GymCentreServiceInterface{

    private static GymCentreInterfaceDAO gymCentreDAO = new GymCentreDAO();
    private static ScheduleServiceInterface scheduleService = new ScheduleService();

    public List<GymCentre> getAllCentresByOwnerId(String gymOwnerId) {
    }

    public List<GymCentre> getCentresByCity(String city){
    }

    public List<Slot> getAvailableSlotsByCentreAndDate(String centreID, Date date){
    }

    public void addCenter(GymCentre gymCentre) {

    }

    public void requestGymCentreApproval(String gymCentreId){
    }

    public GymCentre getGymCentreById(String centreID) {

    }
}