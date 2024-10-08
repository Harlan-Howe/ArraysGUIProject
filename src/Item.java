public class Item
{
    private String name;

    public Item()
    {
        name = STR."Item #\{(int)(1000*Math.random())}";
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
