/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.sql.SQLException;
import javafx.embed.swing.SwingNode;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import service.GestionReclamation;
/**
 * FXML Controller class
 *
 * @author gaalo
 */
public class StatistiqueController implements Initializable {
	@FXML
	private SwingNode staticSwigNode;
	@FXML
	private AnchorPane view;
	@FXML
	private Button screen;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		PieDataset dataset = null;
		try {
			dataset = createDataset();
		} catch (SQLException ex) {
			Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
		}
		JFreeChart chart = createChart(dataset);
		staticSwigNode.setContent(
      new ChartPanel(
			  createChart(dataset)
      )      
    );

	}

	@FXML
	private void doScreen(ActionEvent event) {
		try {
			Robot robot = new Robot();

			Rectangle rectange = new Rectangle(100, 80, 900, 700);

			BufferedImage image = robot.createScreenCapture(rectange);
			Image myImage = SwingFXUtils.toFXImage(image, null);
			SecureRandom o = new SecureRandom();
			o.nextLong();
			System.out.println(o);
			ImageIO.write(image, "jpg", new File(o + ".jpg"));

		} catch (Exception ex) {
			Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);

		}
	}

	private static PieDataset createDataset() throws SQLException {
		DefaultPieDataset dataset = new DefaultPieDataset();
		GestionReclamation m=new GestionReclamation();
		int x = m.returnEtat();
				int y = m.returnEtat1();
		int z = m.returnEtat2();

		
		dataset.setValue("en cours", new Integer(x));
		dataset.setValue("Valider", new Integer(y));
		dataset.setValue("non valider", new Integer(z));
		return dataset;
	}

	private static JFreeChart createChart(PieDataset dataset) {

		JFreeChart chart = ChartFactory.createPieChart(
				"Statistique", dataset);

		// set a custom background for the chart
		chart.setBackgroundPaint(new GradientPaint(new Point(0, 0),
				new Color(20, 20, 20), new Point(400, 200), Color.DARK_GRAY));

		// customise the title position and font
		TextTitle t = chart.getTitle();
		t.setPaint(new Color(240, 240, 240));
		t.setFont(new Font("Arial", Font.BOLD, 26));

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setBackgroundPaint(null);
		plot.setInteriorGap(0.04);
		plot.setOutlineVisible(false);

		// use gradients and white borders for the section colours
		plot.setSectionPaint("en cours", createGradientPaint(new Color(255, 200, 200), Color.BLUE));
		plot.setSectionPaint("Valider", createGradientPaint(new Color(200, 255, 200), Color.GREEN));
		plot.setSectionPaint("Non Valider", createGradientPaint(new Color(200, 255, 200), Color.YELLOW));
		plot.setDefaultSectionOutlinePaint(Color.WHITE);
		plot.setSectionOutlinesVisible(true);
		plot.setDefaultSectionOutlineStroke(new BasicStroke(2.0f));

		// customise the section label appearance
		plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
		plot.setLabelLinkPaint(Color.WHITE);
		plot.setLabelLinkStroke(new BasicStroke(2.0f));
		plot.setLabelOutlineStroke(null);
		plot.setLabelPaint(Color.WHITE);
		plot.setLabelBackgroundPaint(null);

		// add a subtitle giving the data source
		TextTitle source = new TextTitle("Des Reclamtions");
		source.setPaint(Color.WHITE);
//        source.setPosition(RectangleEdge.BOTTOM);
		//source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		chart.addSubtitle(source);
		return chart;

	}

	private static RadialGradientPaint createGradientPaint(Color c1, Color c2) {
		Point2D center = new Point2D.Float(0, 0);
		float radius = 200;
		float[] dist = {0.0f, 1.0f};
		return new RadialGradientPaint(center, radius, dist,
				new Color[]{c1, c2});
	}
	
	


}

	

