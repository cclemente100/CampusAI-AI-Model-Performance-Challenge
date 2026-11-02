
public class CampusAI {

    public static void main(String[] args) {

        String[] models = {
            "ChatGPT", "Gemini", "Claude",
            "Copilot", "Perplexity"
        };

        String[] tasks = {
            "Writing", "Research", "Coding",
            "Math", "Creativity"
        };

        int[][] scores = {
            {92, 88, 95, 90, 86},
            {89, 94, 87, 85, 91},
            {95, 90, 89, 92, 84},
            {85, 82, 96, 88, 90},
            {91, 97, 80, 86, 93}
        };

        System.out.println("=== CAMPUSAI PERFORMANCE REPORT ===");

        // PART 1: Display performance matrix
        displayScores(models, tasks, scores);

        // PART 2: Display model averages
        System.out.println("\nMODEL AVERAGES");

        for (int i = 0; i < models.length; i++) {
            System.out.printf("%s: %.2f%n",
                    models[i], averageModel(scores, i));
        }

        // Display category averages
        System.out.println("\nCATEGORY AVERAGES");

        for (int i = 0; i < tasks.length; i++) {
            System.out.printf("%s: %.2f%n",
                    tasks[i], averageTask(scores, i));
        }

        // PART 3: Searching
        System.out.println("\nSEARCH RESULTS");

        int found = searchModel(models, "Claude");

        if (found != -1) {
            System.out.println("Claude found at index " + found);
        } else {
            System.out.println("Claude not found");
        }

        int notFound = searchModel(models, "UnknownAI");

        if (notFound != -1) {
            System.out.println("UnknownAI found at index " + notFound);
        } else {
            System.out.println("UnknownAI not found");
        }

        // Find the best model for each category
        System.out.println("\nBEST MODEL BY CATEGORY");

        for (int i = 0; i < tasks.length; i++) {

            int bestIndex = findBestModel(scores, i);

            System.out.printf("%s: %s (%d)%n",
                    tasks[i],
                    models[bestIndex],
                    scores[bestIndex][i]);
        }

        // PART 4: Leaderboard
        System.out.println("\nFINAL LEADERBOARD");

        // Create separate arrays for leaderboard
        String[] leaderboardModels = new String[models.length];
        double[] leaderboardAverages = new double[models.length];

        // Calculate and copy model averages
        for (int i = 0; i < models.length; i++) {
            leaderboardModels[i] = models[i];
            leaderboardAverages[i] = averageModel(scores, i);
        }

        // Sort leaderboard
        sortLeaderboard(leaderboardModels, leaderboardAverages);

        // Display sorted leaderboard
        for (int i = 0; i < leaderboardModels.length; i++) {
            System.out.printf("%d. %s %.2f%n",
                    i + 1,
                    leaderboardModels[i],
                    leaderboardAverages[i]);
        }
    }

    // PART 1: Display performance matrix
    public static void displayScores(
            String[] models,
            String[] tasks,
            int[][] scores) {

        System.out.println("\nPERFORMANCE MATRIX");

        System.out.printf("%-15s", "AI Model");

        // Print category headings
        for (int i = 0; i < tasks.length; i++) {
            System.out.printf("%-12s", tasks[i]);
        }

        System.out.println();

        // Outer loop traverses models
        for (int i = 0; i < scores.length; i++) {

            System.out.printf("%-15s", models[i]);

            // Inner loop traverses scores
            for (int j = 0; j < scores[i].length; j++) {
                System.out.printf("%-12d", scores[i][j]);
            }

            System.out.println();
        }
    }

    // PART 2: Calculate average for one model
    public static double averageModel(
            int[][] scores, int row) {

        int total = 0;

        for (int i = 0; i < scores[row].length; i++) {
            total += scores[row][i];
        }

        return (double) total / scores[row].length;
    }

    // PART 2: Calculate average for one category
    public static double averageTask(
            int[][] scores, int column) {

        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i][column];
        }

        return (double) total / scores.length;
    }

    // PART 3: Search for an AI model
    public static int searchModel(
            String[] models, String target) {

        for (int i = 0; i < models.length; i++) {

            if (models[i].equalsIgnoreCase(target)) {
                return i;
            }
        }

        return -1;
    }

    // PART 3: Find the best model for a category
    public static int findBestModel(
            int[][] scores, int taskIndex) {

        int bestIndex = 0;

        for (int i = 1; i < scores.length; i++) {

            if (scores[i][taskIndex] >
                    scores[bestIndex][taskIndex]) {

                bestIndex = i;
            }
        }

        return bestIndex;
    }

    // PART 4: Selection sort leaderboard
    public static void sortLeaderboard(
            String[] models, double[] averages) {

        for (int i = 0; i < averages.length - 1; i++) {

            int highestIndex = i;

            // Find highest remaining average
            for (int j = i + 1; j < averages.length; j++) {

                if (averages[j] > averages[highestIndex]) {
                    highestIndex = j;
                }
            }

            // Swap averages
            double tempAverage = averages[i];
            averages[i] = averages[highestIndex];
            averages[highestIndex] = tempAverage;

            // Swap corresponding model names
            String tempModel = models[i];
            models[i] = models[highestIndex];
            models[highestIndex] = tempModel;
        }
    }
}
