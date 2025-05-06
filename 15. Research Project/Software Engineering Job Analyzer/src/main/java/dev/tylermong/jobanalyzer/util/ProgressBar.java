package dev.tylermong.jobanalyzer.util;

public class ProgressBar
{
    private final int width;

    public ProgressBar(int width)
    {
        this.width = width;
    }

    public void update(int processed, int total)
    {
        double percent = (double) processed / total;
        int done = (int) (width * percent), left = width - done;
        StringBuilder b = new StringBuilder("\r");
        
        for (int i = 0; i < done; i++)
        {
            b.append('█');
        }
        for (int i = 0; i < left; i++)
        {
            b.append('▒');
        }
        b.append(String.format(" %.1f%% (%d/%d)", percent * 100, processed, total));
        
        System.out.print(b);
    }
}