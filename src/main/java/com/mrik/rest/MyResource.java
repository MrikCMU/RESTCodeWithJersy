
package com.mrik.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.PathParam;

import com.mrik.dao.DishDAO;
import com.mrik.model.Dish;

/** Example resource class hosted at the URI path "/myresource"
 */
@Component
@Path("/services")
public class MyResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    
    @Autowired
    FileUpload fileUpload;
    @Autowired
    DishDAO dishDAO;
    
    @GET
	@Path("/searchDish/{name}/{restaurant}")
    @Produces(MediaType.APPLICATION_JSON)

	public Dish getDish(@PathParam("name") String name, @PathParam("restaurant") int restaurant) {
		Dish dish = dishDAO.findByDishName(name, restaurant);
		return dish;
 
	}
    
    @DELETE
	@Path("/deleteDish/{name}/{restaurant}")
	public Response deleteDish(@PathParam("name") String name, @PathParam("restaurant") int restaurant) {
    	Response result = Response.ok().build();
    	try {
    		Dish dish = dishDAO.findByDishName(name, restaurant);
    		if (dish == null) {
    			result = Response.status(Response.Status.NO_CONTENT)
    					.entity("Dish Not present").build();
    		} else {
    			dishDAO.delete(name, restaurant);
    		} }
    	catch (Exception ex) {
    		result = Response.status(Response.Status.BAD_REQUEST)
	                .entity(ex.getMessage()).build();
    	}
    	return result;

	}
    
    @POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createDish( Dish dish ) {
		
		Response result = Response.ok().build();
		try {
			System.out.println("Data from client :" + dish.toString());
			dishDAO.insert(dish);
		} catch (Exception ex) {
			result = Response.status(Response.Status.BAD_REQUEST)
	                .entity(ex.getMessage()).build();
		}
		return result;
	}   
    
    @POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateDish( Dish dish ) {
		
		Response result = Response.ok().build();
		try {
			Dish dishDearch = dishDAO.findByDishName(dish.getName(), dish.getRestaurant());
			if (dishDearch == null) {
				result = Response.status(Response.Status.NO_CONTENT)
                .entity("Dish Not present").build();
			} else {
				dishDAO.update(dish);
			}
		} catch (Exception ex) {
			result = Response.status(Response.Status.BAD_REQUEST)
	                .entity(ex.getMessage()).build();
		}
		return result;
	}
    
}
