package vote.Utils;

import vote.model.Restaurant;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

public class RestaurantUtil {

    public static Restaurant getVoted(List<Restaurant> restaurants){
        Comparator<Restaurant> comparator = Comparator.comparing(Restaurant::getVotes);
        return restaurants.stream()
                .max(comparator).get();
    }

    public static boolean timeForVoting(){
        LocalDateTime date = LocalDateTime.now();
        return date.toLocalTime().isBefore(LocalTime.of(11,0));
        //return true;
    }

    public static void setServletResponseErrorMessage(HttpServletResponse response, String message) throws IOException {
        response.resetBuffer();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("Content-Type", "application/json");
        response.getOutputStream().print("{\"errorMessage\":\"" + message + "\"}");
        response.flushBuffer();
    }

    public static void setServletResponseSuccessMessage(HttpServletResponse response, String message) throws IOException {
        response.resetBuffer();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Content-Type", "application/json");
        response.getOutputStream().print("{\"Restaurant voted\":\"" + message + "\"}");
        response.flushBuffer();
    }


    public static void incrementVotes(Restaurant restaurant){
        restaurant.setVotes(restaurant.getVotes()+1);
    }

    public static void decrementVotes(Restaurant restaurant){
        restaurant.setVotes(restaurant.getVotes()-1);
    }
}
