package vote.Utils;

import vote.model.Restaurant;
import vote.repository.CrudRestaurantRepository;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

import static vote.Utils.ValidationUtil.checkNotFoundWithId;

public class RestaurantUtil {

    public static Restaurant getVoted(List<Restaurant> restaurants){
        Comparator<Restaurant> comparator = new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                return o1.getVotes().get()-o2.getVotes().get();
            }
        };
        return restaurants.stream()
                .max(comparator).get();
    }

    public static boolean voteChangeEnabled(){
        LocalDateTime date = LocalDateTime.now();
        return date.toLocalTime().isBefore(LocalTime.of(11,0));
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
        if(message.equals("DataClear")) response.getOutputStream().print("\"Voting data was cleared\"");
        else if(message.equals("VoteHistoryClear")) response.getOutputStream().print("\"Voting history was cleared\"");
        else response.getOutputStream().print("{\"Restaurant voted\":\"" + message + "\"}");
        response.flushBuffer();
    }

    public static Restaurant incrementVotes(CrudRestaurantRepository repository, int id){
       Restaurant restaurant = checkNotFoundWithId(repository.findById(id).orElse(null),id);
       restaurant.getVotes().getAndIncrement();
       repository.save(restaurant);
       return restaurant;
    }

    public static Restaurant decrementVotes(CrudRestaurantRepository repository, int id){
        Restaurant restaurant = checkNotFoundWithId(repository.findById(id).orElse(null),id);
        restaurant.getVotes().getAndDecrement();
        repository.save(restaurant);
        return restaurant;
    }
}
