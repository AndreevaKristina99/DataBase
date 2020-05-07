package model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;

public class BarCode {

    public void barCode(AnchorPane ap, ImageView images) {
        Code128Bean bean = new Code128Bean();
        final int dpi = 50;
        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar
        bean.doQuietZone(false);
        BitmapCanvasProvider canvas =
                new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY,
                        false, 0);
        bean.generateBarcode(canvas, "123456789");
        BufferedImage bufferedImage = canvas.getBufferedImage();
       images = new ImageView();
       images.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
       ap.getChildren().add(images);

    }
}
