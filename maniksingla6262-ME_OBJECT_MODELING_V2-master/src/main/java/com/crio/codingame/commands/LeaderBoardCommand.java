package com.crio.codingame.commands;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.codingame.entities.ScoreOrder;
import com.crio.codingame.entities.User;
import com.crio.codingame.services.IUserService;

public class LeaderBoardCommand implements ICommand{

    private final IUserService userService;
    
    public LeaderBoardCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllUserScoreOrderWise method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LEADERBOARD","ASC"]
    // or
    // ["LEADERBOARD","DESC"]

    @Override
    public void execute(List<String> tokens) {
        try {
            String order = tokens.get(1);
            ScoreOrder scoreOrder = ScoreOrder.valueOf(order.toUpperCase());
            List<User> users = userService.getAllUserScoreOrderWise(scoreOrder);
           // users.forEach(user -> System.out.println(user));

           String result = users.stream()
           .map(User::toString)  // Convert each user to a string
           .collect(Collectors.joining(", ", "[", "]"));  // Join with commas and add brackets

System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid score order provided. Please use 'ASC' or 'DESC'.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    
}
