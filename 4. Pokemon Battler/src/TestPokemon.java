public class TestPokemon
{
    public static void main(String[] args)
    {
        Charmander charmander1 = new Charmander();
        Pikachu pikachu1 = new Pikachu();

        Stadium tester = new Stadium();
        tester.battle(charmander1, pikachu1);
    }
}
