public class Item
{
    private String name;

    public Item()
    {
        name = STR."Item #\{(int)(1000*Math.random())}";
    }

    public Item(String lineToDecode)
    {
        //TODO: parse the data in lineToDecode to fill in the variables for this class.
    }

    public String getName()
    {
        return name;
    }

    public void setName(String s)
    {
        name = s;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public String getSaveString()
    {
        return STR."\{name}\n";
    }
}
