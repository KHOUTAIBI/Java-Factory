package simulator;

import java.io.*;

import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasChooser;
import fr.tp.inf112.projects.canvas.model.impl.AbstractCanvasPersistenceManager;

/**
 * A class for managing the persistence of Canvas objects.
 */
public class Manager extends AbstractCanvasPersistenceManager {

    /**
     * Constructs a new Manager object with the given CanvasChooser.
     *
     * @param canvasChooser the CanvasChooser to use
     */
    public Manager(CanvasChooser canvasChooser) {
        super(canvasChooser);
    }

    /**
     * Reads a Canvas object from a file with the given ID.
     *
     * @param canvasId the ID of the file to read from
     * @return the Canvas object that was read
     * @throws IOException if there is an error reading the file
     */
    @Override
    public Canvas read(String canvasId) throws IOException {
        FileInputStream fileInStr = null;
        //BufferedInputStream bufInStr = null;
        ObjectInputStream objInStream = null;
        Canvas canvasModel = null;
        try {
            fileInStr = new FileInputStream(canvasId);
            //bufInStr = new BufferedInputStream(fileInStr);
            objInStream = new ObjectInputStream(fileInStr);
            canvasModel = (Canvas) objInStream.readObject();
        }
        catch (Exception ex) {
            System.out.println("Error while reading");
        }
        finally {
            try {fileInStr.close(); } catch (Exception ex) {};
            //try {bufInStr.close(); } catch (Exception ex) {};
            try {objInStream.close(); } catch (Exception ex) {};
        }
        return canvasModel;
    }

    /**
     * Persists a Canvas object to a file with the given ID.
     *
     * @param canvasModel the Canvas object to persist
     * @throws IOException if there is an error writing to the file
     */
    @Override
    public void persist(Canvas canvasModel) throws IOException {
        FileOutputStream fileOutStr = null;
        //BufferedOutputStream bufOutStr = null;
        ObjectOutputStream objOutStream = null;
        try {
            fileOutStr = new FileOutputStream(canvasModel.getId());
            //bufOutStr = new BufferedOutputStream(fileOutStr);
            objOutStream = new ObjectOutputStream(fileOutStr);
            objOutStream.writeObject(canvasModel);
        }
        catch (Exception ex) {
            System.out.println("Error while persisting");
        }
        finally {
            try {fileOutStr.close(); } catch (Exception ex) {};
            //try {bufOutStr.close(); } catch (Exception ex) {};
            try {objOutStream.close(); } catch (Exception ex) {};
        }
    }

    /**
     * Deletes a Canvas object from a file with the given ID.
     *
     * @param canvasModel the Canvas object to delete
     * @return true if the deletion was successful, false otherwise
     * @throws IOException if there is an error deleting the file
     */
    @Override
    public boolean delete(Canvas canvasModel) throws IOException {
        File canvasModelFile = new File(canvasModel.getId());
        return canvasModelFile.delete();
    }

}