package com.flipkart.controllers;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;
import com.flipkart.business.GymCentreFlipfitImplService;
import com.flipkart.business.GymOwnerFlipfitImplService;
import com.flipkart.business.SlotFlipfitImplService;
import com.flipkart.business.SlotFlipfitServiceInterface;
import com.flipkart.utils.addSlotDTO;
import com.flipkart.utils.util;
import com.flipkart.validator.Validators;
import com.flipkart.bean.editProfile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.flipkart.validator.Validators;
import java.util.List;

import static com.flipkart.constants.Constants.GREEN_COLOR;
import static com.flipkart.constants.Constants.RED_COLOR;

@Path("/gym-owner")
@Produces(MediaType.APPLICATION_JSON)
public class GymOwnerController {

    GymOwnerFlipfitImplService gymOwnerService = new GymOwnerFlipfitImplService();
    GymCentreFlipfitImplService gymCentreService  = new GymCentreFlipfitImplService();

    SlotFlipfitServiceInterface slotService = new SlotFlipfitImplService();


    @GET
    @Path("/view-all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCentresByOwnerId() {
        return Response.ok(gymOwnerService.viewAllGymOwners()).build();
    }

    @Path("/gym-centres")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCentresByOwnerId(@QueryParam("gymOwnerId") String gymOwnerId) {
        GymCentreFlipfitImplService gymCentreService = new GymCentreFlipfitImplService();
        return Response.ok(gymCentreService.getAllCentresByOwmerId(gymOwnerId)).build();
    }

    @GET
    @Path("/login")
    public Response GymOwnerLogin(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        GymOwner gymOwner = gymOwnerService.loginGymOwnerWithObject(userName, password);
        if(gymOwner==null)
            return Response.notModified().build();
        return Response.ok(gymOwner).build();
    }
    @POST
    @Path("/register")
//    @Consumes(MediaType.APPLICATION_JSON)
    public Response GymOwnerRegister(GymOwner gymOwner) {
        String gymOwnerId = util.generateNewId();
        GymOwner registerdGymOwner =  gymOwnerService.registerGymOwnerWithObject(gymOwnerId,gymOwner.getUserName(),gymOwner.getPassword(),gymOwner.getEmail(),gymOwner.getPanNumber(),gymOwner.getCardDetails());
        if(registerdGymOwner==null)
            return Response.notModified().build();
        return Response.ok(registerdGymOwner).build();
    }
    @POST
    @Path("/add-centre")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGymCentre(GymCentre gymCentre) {
        GymCentre newGymCentre = gymCentreService.addCenterWithObject(gymCentre);
        if(newGymCentre==null)
            return Response.notModified().build();
        return Response.ok(newGymCentre).build();
    }

    @Path("/get-approval-owner")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestGymOwnerApproval(@QueryParam("gymOwnerId") String gymOwnerId) {
        gymOwnerService.requestGymOwnerApproval(gymOwnerId);
        return Response.ok("Sent approval request to Admin").build();
    }

    @Path("/get-approval-centre")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestGymCentreApproval(@QueryParam("gymCentreId") String gymCentreId) {
        gymCentreService.requestGymCentreApproval(gymCentreId);
        return Response.ok("Sent approval request to Admin").build();
    }
    @Path("/get-gym-centre")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestGymCentreById(@QueryParam("gymCentreId") String gymCentreId) {

        return Response.ok(gymCentreService.getGymCentreById(gymCentreId)).build();
    }

    @Path("/view-gym-owner-profile")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGymOwnerProfile(@QueryParam("gymOwnerId") String gymOwnerId) {
        GymOwner owner = gymOwnerService.viewGymOwnerProfile(gymOwnerId);
        System.out.println(owner);
        return Response.ok(owner).build();
    }


    @Path("/get-available-slots")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAvailableSlots(@QueryParam("centreId") String centreId,@QueryParam("Date") String strdate) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        java.util.Date date;
        Date sqlDate = null;
        try {
            date = sdf.parse(strdate);
            sqlDate = new Date(date.getTime());
        } catch (ParseException e) {
            System.out.println("\n\n\n\n\n\n UNABLE TO PARSE");
        }
        return Response.ok(gymCentreService.getAvailableSlotsByCentreAndDate(centreId,sqlDate)).build();
    }


    @Path("/get-centres-by-city")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCentreByCity(@QueryParam("cityName") String cityName) {

        return Response.ok(gymCentreService.getCentresByCity(cityName)).build();
    }

    @Path("/edit-profile/username")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserName(editProfile userUpdateRequest){

        GymOwner gymOwner = gymOwnerService.viewGymOwnerProfile(userUpdateRequest.getUserId());
        boolean status = gymOwnerService.editProfile(gymOwner.getUserID(), userUpdateRequest.getValueToUpdate(), gymOwner.getEmail(), gymOwner.getCardDetails());

        if(status){
            return Response.ok("Successfully updated").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to update username")
                    .build();
        }
    }

    @Path("/edit-profile/email")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmail(editProfile userUpdateRequest){

        GymOwner gymOwner = gymOwnerService.viewGymOwnerProfile(userUpdateRequest.getUserId());
        Validators validate = new Validators();
        if (!validate.isEmailValid(userUpdateRequest.getValueToUpdate())){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Enter valid email")
                    .build();
        }
        boolean status = gymOwnerService.editProfile(gymOwner.getUserID(), gymOwner.getUserName(), userUpdateRequest.getValueToUpdate(), gymOwner.getCardDetails());

        if(status){
            return Response.ok("Successfully updated email address").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to update email")
                    .build();
        }
    }

    @Path("/edit-profile/card-number")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCardNumber(editProfile userUpdateRequest){

        GymOwner gymOwner = gymOwnerService.viewGymOwnerProfile(userUpdateRequest.getUserId());
        Validators validate = new Validators();
        if (!validate.isCardValid(userUpdateRequest.getValueToUpdate())){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Enter valid card number")
                    .build();
        }
        boolean status = gymOwnerService.editProfile(gymOwner.getUserID(), gymOwner.getUserName(), gymOwner.getEmail(), userUpdateRequest.getValueToUpdate());

        if(status){
            return Response.ok("Successfully updated card number").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to update card number")
                    .build();
        }
    }




}
