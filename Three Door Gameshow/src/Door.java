/**
 * This class builds a door object with a contents field, used to represent a door in the Monty Hall problem.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Door
{
    public String contents;

    // TODO: Remove this constructor
    public Door()
    {
        contents = "placeholder";
    }

    /**
     * Constructs a door object with the contents set to the input.
     * 
     * @param userContents  The contents of the door.
     */
    public Door(String userContents)
    {
        contents = userContents;
    }
}
