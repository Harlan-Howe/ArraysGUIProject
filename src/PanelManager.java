/**
 * An "Interface" is a list of promises -- the methods that a class "implementing" the interface says it will
 * write. In this case, these are methods that a ListSubPanel needs to call so that the GUI commands of the user
 * can be managed by the class that is maintaining the array of items it is showing. (And it can request the latest
 * version of that list.)
 * @param <ContentType> - the type of thing (probably Category or Item) that the list view will request.
 */

public interface PanelManager<ContentType>
{
    /**
     * the user has asked to move the selected item up (towards the start of the array).
     * @param index - the selected index in the list, or -1 if nothing is selected.
     */
    public void handleShiftUp(int index);

    /**
     * the user has asked to move the selected item down (towards the end of the array).
     * @param index - the selected index in the list, or -1 if nothing is selected.
     */
    public void handleShiftDown(int index);

    /**
     * the user has pressed the + button, requesting that you add another thing to the array.
     */
    public void handleAdd();

    /**
     * the user has pressed the - button, requesting that you remove the selected item.
     * @param index - the index of the item to remove, or -1 if there is no selection.
     */
    public void handleRemove(int index);

    /**
     * gets the array of things that should show up in the list.
     * @return the array to put in this list.
     */
    public ContentType[] getListData();


}
