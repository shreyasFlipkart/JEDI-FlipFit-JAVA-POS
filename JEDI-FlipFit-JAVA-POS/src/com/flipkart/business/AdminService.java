public class AdminService implements AdminServiceInterface{


    private List<GymOwner> pendingGymOwnerList = new ArrayList<>();
    private List<GymCentre> pendingGymCentreList = new ArrayList<>();

    public void approveGymCenter(String gymCentreId,int isApproved){

    }

    public void approveGymOwner(String gymOwnerId,int isApprove){
    }

    public List<GymCentre> viewPendingGymCentres(){

    }

    public List<GymOwner> viewPendingGymOwners(){

    }

}