public class Main
{
    public static void main(String[] args)
    {
        DataBuilder dataBuilder = new DataBuilder();
        dataBuilder.run();

        Salter salter = new Salter();
        salter.run();
        
        Smoother smoother = new Smoother();
        smoother.run();
    }
}