import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{

	
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	
	public ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
	}
	
	public void addProjectile() {
		
	}
	
	public void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.width),0,50,50));
	
	}
	
	
	public void update() {
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
		
			
			if(aliens.get(i).y > LeagueInvaders.height) {
				aliens.get(i).isActive = false;
			}
		}
	
		
	}
	
	
	public void draw(Graphics g) {
		rocket.draw(g);
	for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);	
	}
	
	for (int i = 0; i < projectiles.size(); i++) {
		projectiles.get(i).draw(g);	
}
	
	}
	
	
	
	public void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if(aliens.get(i).isActive == false) {
				aliens.remove(i);
			}
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).isActive == false) {
				projectiles.remove(i);
			}
		}	
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
}

