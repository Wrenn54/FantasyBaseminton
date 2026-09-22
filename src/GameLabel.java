
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GameLabel extends JLabel {

    public GameLabel(String text){
        super(text, SwingConstants.CENTER);
    }

    public GameLabel(){
        super("", SwingConstants.CENTER);
    }

    public void setDimension(){
        this.setSize(new Dimension((int)(this.getFont().getSize()/1.5)*this.getText().length(),this.getFont().getSize()));
    }

    public void setLocationCenteredOn(int x, int y){
        this.setLocation(x - this.getWidth()/2, y - this.getHeight()/2);
    }
}
