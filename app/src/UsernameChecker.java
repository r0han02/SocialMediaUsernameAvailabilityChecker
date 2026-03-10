import java.util.*;

public class UsernameChecker {

    private HashMap<String, Integer> users = new HashMap<>();
    private HashMap<String, Integer> attempts = new HashMap<>();

    public boolean checkAvailability(String username) {

        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        if (users.containsKey(username)) {
            return false;
        }
        return true;
    }

    public void registerUser(String username, int userId) {
        users.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;

            if (!users.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        String modified = username.replace("_", ".");
        if (!users.containsKey(modified)) {
            suggestions.add(modified);
        }

        return suggestions;
    }

    public String getMostAttempted() {

        String maxUser = "";
        int maxCount = 0;

        for (String key : attempts.keySet()) {
            int count = attempts.get(key);

            if (count > maxCount) {
                maxCount = count;
                maxUser = key;
            }
        }

        return maxUser + " (" + maxCount + " attempts)";
    }

    public static void main(String[] args) {

        UsernameChecker system = new UsernameChecker();

        system.registerUser("john_doe", 1);
        system.registerUser("admin", 2);

        System.out.println("Check john_doe: " + system.checkAvailability("john_doe"));
        System.out.println("Check jane_smith: " + system.checkAvailability("jane_smith"));

        System.out.println("Suggestions for john_doe: " + system.suggestAlternatives("john_doe"));

        system.checkAvailability("admin");
        system.checkAvailability("admin");
        system.checkAvailability("admin");

        System.out.println("Most attempted: " + system.getMostAttempted());
    }
}