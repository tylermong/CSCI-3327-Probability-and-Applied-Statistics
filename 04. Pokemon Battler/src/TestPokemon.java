public class TestPokemon
{
    public static void main(String[] args)
    {
        // Create two Pokemon objects to test the battle method
        Charmander charmander1 = new Charmander();
        Pikachu pikachu1 = new Pikachu();

        // Battle the two Pokemon
        Stadium tester = new Stadium();
        tester.battle(charmander1, pikachu1);
    }
}
