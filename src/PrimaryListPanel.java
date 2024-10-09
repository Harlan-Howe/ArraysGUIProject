import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
/**
 * this class represents the leftmost part of the window. It has one interactive list that holds all the categories.
 * This class is also responsible for opening and saving the files, based on exchanges with other classes.
 */
public class PrimaryListPanel extends JPanel implements ListSelectionListener, ActionListener
{
    private ListSubPanel<Category> listGUI; // the GUI item that the user sees in this panel, showing the list of categories.
    private CategoryCollection theCategoryCollection; // the datasource for the listGUI - this is where the categories are actually stored.
    private CategoryPanel theCategoryPanel; // a pointer to the middle panel.

    private File latestFile; // the location on the computer of where the last file that was saved or opened.


    public PrimaryListPanel()
    {
        super();
        theCategoryCollection = new CategoryCollection();
        listGUI = new ListSubPanel<Category>(theCategoryCollection, this);

        latestFile = null;
        theCategoryPanel = null;

        buildGUI();
        listGUI.update();
        repaint();
    }

    /**
     * add all the graphical elements to this panel. There is only one item in this panel, so it is really short.
     */
    public void buildGUI()
    {
        this.add(listGUI);
    }

    /**
     * updates the pointer to the middle panel, so that this class can notify it when the user selects a different category
     * @param cp - the category panel
     */
    public void setCategoryPanel(CategoryPanel cp)
    {
        theCategoryPanel = cp;
    }

    /**
     * this does the process of opening a file, and it is called when the user selects "Open" from the File Menu.
     */
    public void handleOpenRequest()
    {
        // ask the user to pick a file... if it comes back as false, the user cancelled.
        if (userSelectAFileToOpen() == false)
            return;

        try // we're about to try something that could go wrong (opening and reading a file). So we mark this and tell
            // the computer what to do if it all goes poorly.
        {
            BufferedReader reader = new BufferedReader((new FileReader(latestFile))); // open the file.
            // read the first line, the number of categories in this file.
            String latestLine = reader.readLine(); // it comes in as a string
            int numCategories = Integer.parseInt(latestLine); // convert to a number.
            theCategoryCollection = new CategoryCollection(numCategories); // create a new CategoryCollection with space for the categories.

            // loop through the file to load each category
            for (int catIndex = 0; catIndex < numCategories; catIndex++)
            {
                String categoryDescription = reader.readLine(); // get the tab-delimited list of properties of the category
                String numItemsInCategoryString = reader.readLine(); // get a string for the number of items...
                int numItemsInCategory = Integer.parseInt(numItemsInCategoryString);  // ... convert to an int, and...
                Category cat = new Category(categoryDescription, numItemsInCategory); // create a new category with the list of properties and enough space for the items.

                // now let's load up those items.
                for (int itemIndex = 0; itemIndex < numItemsInCategory; itemIndex++)
                {
                    String itemDescription = reader.readLine();
                    Item nextItem = new Item(itemDescription);
                    cat.setItem(nextItem, itemIndex);
                }
                // we just created a new Category, "cat," so we'll add it to the list of cetegories.
                theCategoryCollection.setCategory(cat, catIndex);
            }
            reader.close();

            System.out.println("File opened.");
            listGUI.setMyManager(theCategoryCollection);
            theCategoryPanel.setCurrentCategory(null);

            // tell everything on screen that it needs to update.
            repaint();
        } catch (FileNotFoundException fnfe) // if there are problems opening and reading the file, these "catches" will deal with it.
        {
            throw new RuntimeException("Could not open the file.");
        } catch (IOException e)
        {
            throw new RuntimeException("Problem reading the file once open.");
        }
    }

    /**
     * Show the user a dialog box and request that they pick a file.
     * @return - whether the user clicked "OK" at the end.
     */
    private boolean userSelectAFileToOpen()
    {
        JFileChooser openDialog = new JFileChooser();
        openDialog.setSelectedFile(latestFile);
        int response = openDialog.showOpenDialog(this);
        if (response == JFileChooser.CANCEL_OPTION)
            return false;
        latestFile = openDialog.getSelectedFile();
        return true;
    }

    /**
     * the user just asked us to save the data in memory to the hard drive.
     */
    public void handleSaveRequest()
    {
        System.out.println("Saving.");
        // if there isn't already a file associated with this data (from opening or a previous save), treat this as a "save as."
        if (latestFile == null)
        {
            handleSaveAsRequest();
            return;
        }

        String stringToSave = theCategoryCollection.getSaveString(); // get the data in the form of a single, multiline string.

//        System.out.println(stringToSave);
        // a fancy version of the try we did earlier... the process of opening the file is potentially going to fail, so
        // we indicate what to do if it does.
        try (PrintWriter fileOut = new PrintWriter(latestFile)) {
            fileOut.println(stringToSave);
            System.out.println(STR."Saved to \{latestFile.getPath()}");
        } catch (FileNotFoundException e) // if the file won't open, the computer goes here.
        {
            System.out.println(STR."Error opening file: \{latestFile.getPath()}");
        }

    }

    /**
     * shows a dialog box to let the user select a file name and location to save, then routes the execution to handleSaveRequest.
     */
    public void handleSaveAsRequest()
    {
        JFileChooser saveDialog = new JFileChooser();
        saveDialog.setCurrentDirectory(latestFile); // preload the location of the last file we saved/opened before showing dialog.
        int confirmed = saveDialog.showSaveDialog(this);
        File selectedFile = saveDialog.getSelectedFile();
        if (JFileChooser.CANCEL_OPTION == confirmed || selectedFile == null)
            return;
        latestFile = selectedFile;
        handleSaveRequest();
    }

    /**
     * erases all items in this file and replaces them with a new set, equivalent to restarting the program.
     */
    public void handleNewRequest()
    {
        theCategoryCollection = new CategoryCollection();
        listGUI.setMyManager(theCategoryCollection);
        theCategoryPanel.setCurrentCategory(null);

        // tell everything on screen that it needs to update.
        repaint();
    }

    /**
     * this gets called if the user changes which item in the list of categories is selected, in which case we'll need to
     * update the middle panel with the newly selected category.
     * @param e the event that characterizes the change. - contains information about which row was selected.
     */
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        System.out.println(STR."The user just changed the selection in the list to #\{e.getFirstIndex()}. I should probably do something about that.");
        theCategoryPanel.setCurrentCategory(theCategoryCollection.getCategoryAtIndex(e.getFirstIndex()));
    }

    /**
     * The user just picked a menu item... based on which one, call the appropriate method.
     * @param e the event to be processed - contains information about which item was selected.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("Save"))
            handleSaveRequest();
        else if (e.getActionCommand().equals("Save As..."))
            handleSaveAsRequest();
        else if (e.getActionCommand().equals("Open..."))
            handleOpenRequest();
        else if (e.getActionCommand().equals("New"))
            handleNewRequest();
    }
}
