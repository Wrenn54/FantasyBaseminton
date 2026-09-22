
import java.awt.Dimension;
import javax.swing.JButton;

public class GameButton extends JButton {

    public void setDimension(){
        this.setSize(new Dimension((int)(this.getFont().getSize())*(int)(this.getText().length()*2),(int)(this.getFont().getSize()*1.5)));
    }

    public void setLocationCenteredOn(int x, int y){
        this.setLocation(x - this.getWidth()/2, y - this.getHeight()/2);
    }
}
