package simulator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fr.tp.inf112.projects.canvas.model.Canvas;
import fr.tp.inf112.projects.canvas.model.CanvasChooser;
import fr.tp.inf112.projects.canvas.model.impl.AbstractCanvasPersistenceManager;

public class Manager extends AbstractCanvasPersistenceManager {
	
	public Manager(CanvasChooser canvasChooser) {
		super(canvasChooser);
		
	}

	@Override
	public Canvas read(String canvasId) throws IOException {
		FileInputStream fileInStr = null;
		BufferedInputStream bufInStr = null;
		ObjectInputStream objInStream = null;
		Canvas canvasModel = null;
		try {
			fileInStr = new FileInputStream(canvasId);
			bufInStr = new BufferedInputStream(fileInStr);
			objInStream = new ObjectInputStream(bufInStr);
			canvasModel = (Canvas) objInStream.readObject();
		}
		catch (Exception ex) {
			System.out.println("Error while reading");
		}
		finally {
			try {fileInStr.close(); } catch (Exception ex) {};
			try {bufInStr.close(); } catch (Exception ex) {};
			try {objInStream.close(); } catch (Exception ex) {};
		}
		return canvasModel;
	}

	@Override
	public void persist(Canvas canvasModel) throws IOException {
		FileOutputStream fileOutStr = null;
		BufferedOutputStream bufOutStr = null;
		ObjectOutputStream objOutStream = null;
		try {
			fileOutStr = new FileOutputStream(canvasModel.getId());
			bufOutStr = new BufferedOutputStream(fileOutStr);
			objOutStream = new ObjectOutputStream(bufOutStr);
			objOutStream.writeObject(canvasModel);
		}
		catch (Exception ex) {
			System.out.println("Error while persisting");
		}
		finally {
			try {fileOutStr.close(); } catch (Exception ex) {};
			try {bufOutStr.close(); } catch (Exception ex) {};
			try {objOutStream.close(); } catch (Exception ex) {};
		}
	}

	@Override
	public boolean delete(Canvas canvasModel) throws IOException {
		File canvasModelFile = new File(canvasModel.getId());
		return canvasModelFile.delete();
	}
	
}
