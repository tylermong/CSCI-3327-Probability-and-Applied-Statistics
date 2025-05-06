package dev.tylermong.jobanalyzer.util;

/**
 * Utility class for displaying a progress bar in the console.
 */
public class ProgressBar
{
    private final int width;

    /**
     * Constructor for ProgressBar object.
     * 
     * @param width the width of the progress bar
     */
    public ProgressBar(int width)
    {
        this.width = width;
    }

    /**
     * Updates the progress bar with the current progress and total.
     * 
     * @param processed the number of processed items
     * @param total     the total number of items
     */
    public void update(int processed, int total)
    {
        double percent = (double) processed / total;
        int done = (int) (width * percent), left = width - done;
        StringBuilder builder = new StringBuilder("\r");

        for (int i = 0; i < done; i++)
        {
            builder.append('█');
        }
        for (int i = 0; i < left; i++)
        {
            builder.append('▒');
        }
        builder.append(String.format(" %.1f%% (%d/%d)", percent * 100, processed, total));

        System.out.print(builder);
    }
}