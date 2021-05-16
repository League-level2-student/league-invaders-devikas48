

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{  
	
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font title2Font;
	Font title3Font;
	Font title4Font;
	Font title5Font;
	Font title6Font;
	Timer frameDraw;
	Timer alienSpawn;


int x = 250;
int y = 550;
int width = 50;
int height = 50;

Rocketship rocket = new Rocketship(250, 550, width, height);
ObjectManager objectmanager = new ObjectManager(rocket);
	

public static BufferedImage image;
public static boolean needImage = true;
public static boolean gotImage = false;	





public GamePanel() {
titleFont = new Font("Arial", Font.PLAIN, 48);
title2Font = new Font("Arial", Font.PLAIN, 30);
title3Font = new Font("Arial", Font.PLAIN, 30);
title4Font = new Font("Arial", Font.PLAIN, 30);
title4Font = new Font("Arial", Font.PLAIN, 30);
title6Font = new Font("Arial", Font.PLAIN, 30);
frameDraw = new Timer(1000/60,this);
frameDraw.start();

if (needImage) {
    loadImage ("space.png");
}

}

	
	@Override
public void paintComponent(Graphics g){

if(currentState == MENU){
    drawMenuState(g);
}else if(currentState == GAME){
    drawGameState(g);
}else if(currentState == END){
    drawEndState(g);
}

}
	


public void  updateMenuState() {  
		  
		  
		  
}
public void updateGameState() { 
	objectmanager.update();
		  
	  
}
public void updateEndState()  {  
		  
		  
}

public void drawMenuState(Graphics g) { 
	g.setColor(Color.BLUE);
	g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
	g.setFont(titleFont);
	g.setColor(Color.YELLOW);
	g.drawString("LEAGUE INVADERS", 15, 100 );
	g.setFont(title2Font);
	g.setColor(Color.YELLOW);
	g.drawString("Press ENTER to start", 80, 300);
	g.setFont(title3Font);
	g.setColor(Color.YELLOW);
	g.drawString("Press SPACE for instructions", 45, 500);
	
}
public void drawGameState(Graphics g) {
	if (gotImage) {
		g.drawImage(image, 0, 0, LeagueInvaders.width, LeagueInvaders.height, null);
	} else {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);		
}
	
	objectmanager.draw(g);

	
	
}
public void drawEndState(Graphics g)  { 
	g.setColor(Color.RED);
	g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
	g.setFont(title4Font);
	g.setColor(Color.YELLOW);
	g.drawString("GAMEOVER", 15, 100);
	g.setFont(title5Font);
	g.setColor(Color.YELLOW);
	g.drawString("You killed enemies", 80, 300);
	g.setFont(title6Font);
	g.setColor(Color.YELLOW);
	g.drawString("Press ENTER to restart", 45, 500);
}


@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	if(currentState == MENU){
	    updateMenuState();
	}else if(currentState == GAME){
	    updateGameState();
	}else if(currentState == END){
	    updateEndState();
	}
	
	System.out.println("action");
	repaint();
	
	
}


@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	
	
	if (e.getKeyCode()==KeyEvent.VK_ENTER) {
	    if (currentState == END) {
	        currentState = MENU;
	        alienSpawn.stop();
	    } else {
	        currentState++;
	        startGame();
	    }
		 
	} 
	
	if (e.getKeyCode()==KeyEvent.VK_SPACE) {
		if (currentState == GAME) {
		objectmanager.addProjectile(rocket.getProjectile());
		}
	}
	
	
	


if (e.getKeyCode()==KeyEvent.VK_UP) {
    if (rocket.y < 0) {
    	rocket.y = 0;
    } else {
    	rocket.up();
    }
} else if (e.getKeyCode()==KeyEvent.VK_DOWN){
	rocket.down();
} else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
	rocket.right();
} else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
	rocket.left();
}

}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}


 


void loadImage(String imageFile) {
    if (needImage) {
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
	    gotImage = true;
        } catch (Exception e) {
            
        }
        needImage = false;
    }
}

public void startGame( ) {
	alienSpawn = new Timer(1000 , objectmanager);
	alienSpawn.start();

}

}
