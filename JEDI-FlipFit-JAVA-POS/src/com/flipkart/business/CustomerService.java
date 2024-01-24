public class CustomerService implements CustomerServiceInterface {



    public List<GymCentre> getAllGymCenterDetailsByCity(String city){
    }

    public List<Slot> getAvailableSlots(String centreID, Date date){

    }

    public List<Booking> getCustomerBookings(String customerId){

    }

    public List<UserPlan> getCustomerPlan(String customerId){
    }

    public boolean bookSlot(String userName,Date date, String slotId,String centreId){

    }



    public void cancelBookingbyID(String bookingID){

    }

    public void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) {


    }

    public Customer viewMyProfile(String userName) {
    }

    public boolean isUserValid(String userName, String password) {

    }
}