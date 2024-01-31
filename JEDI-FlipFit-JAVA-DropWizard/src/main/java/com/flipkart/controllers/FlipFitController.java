package com.flipkart.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.ApprovalRequestUpdatePassword;
import com.flipkart.bean.Customer;
import com.flipkart.bean.GymOwner;
import com.flipkart.business.CustomerFlipfitImplService;
import com.flipkart.business.CustomerFlipfitServiceInterface;
import com.flipkart.business.GymOwnerFlipfitImplService;
import com.flipkart.business.GymOwnerFlipfitServiceInterface;
import com.flipkart.client.CustomerFlipfitClient;
import com.flipkart.client.GymOwnerFlipfitClient;

import static com.flipkart.constants.Constants.*;
import static javax.ws.rs.core.Response.status;

@Path("/update-password")
public class FlipFitController {
    private static CustomerFlipfitClient customerFlipfitClient = new CustomerFlipfitClient();
    CustomerFlipfitServiceInterface CustomerFlipfitImplService = new CustomerFlipfitImplService();
    private static GymOwnerFlipfitClient gymOwnerFlipfitClient = new GymOwnerFlipfitClient();

    @GET
    public String sayHello() {
        return "Hello, World!";
    }
    @PUT
    @Path("/customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePasswordCustomer(ApprovalRequestUpdatePassword approvalRequest) {
        String oldPassword = approvalRequest.getOldPassword();
        String newPassword = approvalRequest.getNewPassword();
        String userName = approvalRequest.getUserName();
        try {
            if (customerFlipfitClient.validateCredentials(userName,oldPassword)) {
                customerFlipfitClient.updatePassword(userName,newPassword);
                Customer customerProfile = CustomerFlipfitImplService.viewMyProfile(userName);
                return Response.status(Response.Status.OK) // You can use Response.Status.OK for a successful operation
                        .entity("Password updated successfully") // Message to be returned
                        .build();


            } else {
                return Response.notModified().entity("Password update failed").build();

            }

        } catch (Exception exception) {
            return status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }
    @PUT
    @Path("/gymowner")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePasswordGymOwner(ApprovalRequestUpdatePassword approvalRequest) {
        GymOwnerFlipfitServiceInterface gymOwnerService = GymOwnerFlipfitImplService.getInstance();
        String oldPassword = approvalRequest.getOldPassword();
        String newPassword = approvalRequest.getNewPassword();
        String userName = approvalRequest.getUserName();
        try {
            if (gymOwnerFlipfitClient.validateCredentials(userName, oldPassword)) {
                gymOwnerFlipfitClient.updatePassword(userName, newPassword);
                GymOwner g = gymOwnerService.viewGymOwnerProfile(userName);
                String curId = g.getUserID();
                GymOwner owner = gymOwnerService.viewGymOwnerProfile(curId);
                return Response.status(Response.Status.OK) // You can use Response.Status.OK for a successful operation
                        .entity("Password updated successfully") // Message to be returned
                        .build();


            } else {
                return Response.notModified().entity("Password update failed").build();

            }

        } catch (Exception exception) {
            return status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }


}
