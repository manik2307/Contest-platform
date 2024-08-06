package com.crio.codingame.commands;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class ListContestCommand implements ICommand{

    private final IContestService contestService;
    
    public ListContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllContestLevelWise method of IContestService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_CONTEST","HIGH"]
    // or
    // ["LIST_CONTEST"]

    @Override
    public void execute(List<String> tokens) {
        try {
            List<Contest> contests;
            if (tokens.size() > 1) {
                Level level = Level.valueOf(tokens.get(1).toUpperCase());
                contests = contestService.getAllContestLevelWise(level);
            } else {
                contests = contestService.getAllContestLevelWise(null);
            }
            String result = contests.stream()
                                .map(Contest::toString)  // Convert each contest to a string
                                .collect(Collectors.joining(", ", "[", "]"));  // Join with commas and add brackets
        
        System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid level provided.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    
}
