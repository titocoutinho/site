package desenv.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class ThumbnailFactory implements Serializable {

	private static final long serialVersionUID = 1L;

	public ThumbnailFactory() {
	}

	public void run(String folder) {
		File dir = new File(folder);
		for (File file : dir.listFiles()) {
			createThumbnail(file);
		}
	}

	private void createThumbnail(File file) {
		try {
			BufferedImage img = ImageIO.read(file);
			BufferedImage thumb = createEmptyThumbnail();
			Graphics2D g2d = (Graphics2D) thumb.getGraphics();
			g2d.drawImage(img, 0, 0, thumb.getWidth() - 1,thumb.getHeight() - 1, 0, 0, img.getWidth() - 1,
					img.getHeight() - 1, null);
			g2d.dispose();
			File gravar = createOutputFile(file);
			
			if(gravar.exists()){
				return;
			}else{
				if(gravar.getAbsolutePath().contains("thumb.thumb.png"))
					return;
				ImageIO.write(thumb, "PNG", gravar);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private File createOutputFile(File inputFile) {
		return new File(inputFile.getAbsolutePath().replace(".jpg","")
				.replace(".png","").replace(".gif","")+".thumb.png");
	}

	private BufferedImage createEmptyThumbnail() {
		return new BufferedImage(75, 75, BufferedImage.TYPE_INT_RGB);
	}
}
