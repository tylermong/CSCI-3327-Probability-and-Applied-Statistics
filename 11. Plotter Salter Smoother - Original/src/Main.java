public class Main
{
    public static void main(String[] args)
    {
        DataBuilder dataBuilder = new DataBuilder();
        dataBuilder.run();

        Salter salter = new Salter(10);
        salter.run();
        
        Smoother smoother = new Smoother(5);
        smoother.run();
    }
}