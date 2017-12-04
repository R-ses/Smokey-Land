package smokey.land;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javax.swing.Timer;
import javafx.embed.swing.JFXPanel;

class Clock implements Runnable
{
    JLabel display;
    int Time;

public Clock(int tiempo)
{
    this.Time = tiempo;
    this.display = new JLabel();
    this.display.setLocation(1250, 0);//Esquina Superior derecha
    this.display.setVisible(true);
}

    @Override
    public void run() {
    while(Time>0)
    {
        System.out.println("Time:"+Time);
        display.setText("Time:"+Time);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
        }
        Time--;
    }   
        System.out.println("TIME UP!");
        System.exit(0);
    }
    
}

class Music implements Runnable
{
    String pista = ".\\src\\smokey\\land\\Assets\\DRI.mp3";
    Media hit = new Media(new File(pista).toURI().toString());
    MediaPlayer mp = new MediaPlayer(hit);
    
    public Music()
    {
        
    }

    @Override
    public void run() {
    mp.play();
    }
}


class Death extends Entity 
{
    public Death(int x, int y, int width, int height)
    {
    pos = new Point(x,y);
    body = new Rectangle(pos, new Dimension(width, height));
    }
	
    public void setPosition(int x, int y)
    {	
    this.setLocation(x,y);
    }

    public Point getPosition()
    {
    Point p =  this.getLocation();
    return new Point(p);
    } 
}

class Foe extends Entity 
{

    int vida;
    int poder;
    
    public Foe(int x, int y, int width, int height,int life,int power)
    {
    pos = new Point(x,y);
    body = new Rectangle(pos, new Dimension(width, height));
    this.vida = life;
    this.poder = power;
    }
	
    public void setPosition(int x, int y)
    {	
    this.setLocation(x,y);
    }

    public Point getPosition()
    {
    Point p =  this.getLocation();
    return new Point(p);
    } 
    
    //IMPLEMENT FOE MOVILITY
    
}



class Token extends Entity
{
    BufferedImage d;  
    
	public Token(int x,int y,int width,int height)
	{
        
        try
        {
        this.d = ImageIO.read(new File(".\\src\\smokey\\land\\Assets\\225.png"));
        }
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        pos = new Point(x,y);
        body = new Rectangle(pos, new Dimension(width, height));
	
        }	
        
    public BufferedImage getActualImage()
    {
        BufferedImage subSprite;
        subSprite = this.d;
        return subSprite;
    }

}


class Background extends Entity 
{
    BufferedImage fondo;

    public Background(int x, int y, int width, int height,String Path)
    {
    try {
        this.fondo = ImageIO.read(new File(Path));
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    
    pos = new Point(x,y);
    body = new Rectangle(pos, new Dimension(width, height));

    }

    public BufferedImage getActualImage()
    {
        BufferedImage subSprite;
        subSprite = this.fondo;
        return subSprite;
    
    }
}

//-----------

class BoxMovilityH implements Runnable
{
    private Entity box;
    private int width;
    private int time;
    int m;
    int lim;
    
    public BoxMovilityH(Entity box, int width, int lim, int time, int m)
    {
        this.box = box;
        this.width = width; // movimiento horizontal
        this.time = time;
        this.m = m;
        this.lim = lim;
    }

    @Override
    public void run(){
        do{	

        while(box.pos.x+box.body.width <= width)
        {
            box.pos.x += m;  
            
            try
            {
                Thread.sleep(this.time);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }

        }
        while(box.pos.x >=lim)
        {
            box.pos.x -= m;
            
            try
            {
                Thread.sleep(this.time);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        }while(true);
    }

}
//-----------

class BoxMovilityV implements Runnable
{
    private Box box;
    private int width;
    private int height;
    private int time;

    int m=0;

    public BoxMovilityV(Box box, int width, int height, int time, int m)
    {
        this.box = box;
        this.width = width; // movimiento horizontal
        this.time = time;
        this.m = m;
        this.height = height;
    }
    @Override
    public void run(){
        do{	

        while(box.pos.y+box.body.height <= height)
        {
            box.pos.y += m;  

            try
            {
                Thread.sleep(this.time);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }

        }
        while(box.pos.y >=0)
        {
            box.pos.y -= m;
            
            try
            {
                Thread.sleep(this.time);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        }while(true);
    }

}

class Goal extends Entity
{
    BufferedImage d;  
    
	public Goal(int x,int y,int width,int height)
	{
        
        try
        {
        this.d = ImageIO.read(new File(".\\src\\smokey\\land\\Assets\\226.jpg"));
        }
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        pos = new Point(x,y);
        body = new Rectangle(pos, new Dimension(width, height));
	
        }	
        
    public BufferedImage getActualImage()
    {
        BufferedImage subSprite;
        subSprite = this.d;
        return subSprite;
    }

}

class Box extends Entity 
{
    public Box(int x, int y, int width, int height)
    {
    pos = new Point(x,y);
    body = new Rectangle(pos, new Dimension(width, height));
    }
	
    public void setPosition(int x, int y)
    {	
    this.setLocation(x,y);
    }

    public Point getPosition()
    {
    Point p =  this.getLocation();
    return new Point(p);
    } 
}

class Entity extends Rectangle implements Drawable
{
    Point pos;
    Rectangle body;
    JLabel img_gen = new JLabel();
//    int life=1000;
    
    public Entity()
    {
        pos = new Point(0,0);
        body = new Rectangle(pos, new Dimension(0,0));
    }
	
    public Entity(Point p, Dimension d)
    {
        pos = new Point(p);
        body = new Rectangle(pos, d);
    }
	
    public Entity(int x, int y, int width, int height)
    {
        pos = new Point(x,y);
        body = new Rectangle(pos, new Dimension(width, height));
    }
	
    public BufferedImage getActualImage()
    {
        Dimension d = body.getSize();
        return new BufferedImage(d.width, d.height, BufferedImage.TYPE_3BYTE_BGR);
    }
}

interface  Drawable
{
    public BufferedImage getActualImage();
}


class Player extends Entity implements Movible
{
    
    BufferedImage img_rrun;
    BufferedImage img_lrun;
    BufferedImage img_jump;
    BufferedImage img_actual;
    BufferedImage img_idle;
    
    int ac;
    private Player self;
    
    float vel=7;//run
    float weight=5;//fall speed
    float energy=15;//jump
    int sprite=0;
    boolean attacking = false;
    boolean falling = false;
    boolean jumping = false;
    boolean state = true;      //LEFT 1 RIGHT 0
    
    //  3,21,66,55 <1st image>
    //  80,21,66,55 <2nd image>
    //  157,21,66,55<3rd image>
    
    // [x][y][width][height]
    
    int[][][] spriteImgCoords = 
    //[ac][sprite(0-3)][0,1,2,3]
    {
        //[X][Y][Px][Py]
        { {000,00,70,70}, {070,00,70,70},{140,00,70,70}},//LIDLE
        { {000,00,70,70}, {070,00,70,70},{140,00,70,70}},//LRUN
        { {420,00,70,70}, {490,00,70,70},{560,00,70,70}},//LJMP 
        { {210,00,70,70}, {280,00,70,70},{350,00,70,70}},//LATK
        { {420,00,70,70}, {490,00,70,70},{560,00,70,70}},//LFALL
        
        { {570,00,60,70}, {500,00,60,70},{430,00,60,70}},//RIDLE
        { {570,00,60,70}, {500,00,60,70},{430,00,60,70}},//RRUN
        { {140,00,70,70}, {070,00,70,70},{000,00,70,70}},//RJMP
        { {350,00,70,70}, {280,00,70,70},{210,00,70,70}},//RATK
        { {350,70,70,70}, {280,70,70,70},{210,70,70,70}}//RFALL

    };
	
    private ActionListener actionListener = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            sprite=sprite+1;
           // System.out.println("Length:"+spriteImgCoords.length);
            if (sprite == 3)
            {
                sprite = 0;
            }
//            System.out.println("Sprite:"+sprite);
//            System.out.println("["+spriteImgCoords[ac][sprite][0]+"],["+spriteImgCoords[ac][sprite][1]+"],["+spriteImgCoords[ac][sprite][2]+"],["+spriteImgCoords[ac][sprite][3]+"]");
            
               if(self.pos.y >= 550)
            {
               System.out.println("Estas Muerto");
               System.exit(0);
            }
               for(Entity other: Game.objects)
               if(self.intersects(other) && other instanceof Foe)
               {
                 Game.hearts.remove(Game.VID--);
               }
        }
    };

    private void changeState(int a){
        
    //img_actual = img_rrun;
       
    
    if(a<=4)
    {
        state = false;
        img_actual = img_lrun;
        ac = a;
    }
    else
    {
        state = true;
        img_actual = img_rrun;
        ac = a;
    }
    
      
    
       /*          <a>
       LEFT                RIGHT
        LIDLE   0           RIDLE  5
        LRUN    1           RRUN   6
        LJMP    2           RJMP   7
        LATK    3           RATK   8 
        LFALL   4           RFALL  9
       */
    }

    public Player()
    {
        Timer tick = new Timer(100, actionListener);
        tick.setInitialDelay(0);
        tick.start();
        self = this;
        fall();
     
         try {
            img_rrun = ImageIO.read(new File(".\\src\\smokey\\land\\Assets\\link50r.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
         try {
            img_lrun = ImageIO.read(new File(".\\src\\smokey\\land\\Assets\\link50.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    
  		
    }

    void down() {
    if(state)
        {
            changeState(0);
        }
    else
        {
            changeState(5);
        }
    }

    public class Falling implements Runnable{
        public void run(){
			while(true){
                            
            // while(pos.y<100){
            while(Game.detectCollisionBottom(self) != CollisionState.BOTTOM ){
				
			
                try{
		
                    if(!jumping){
                        
                        pos.y+=weight;
                        
                    }
                   Thread.sleep(25);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            falling = !falling;
	    }
        }
		
    }

    public class Jumping implements Runnable{
        @Override
        public void run(){
            int i=0;
            while(i<5){
                i++;
                try
                {
                    pos.y-=energy;
                    Thread.sleep(50);
                }
                catch(Exception e){
                }
            }
            jumping = false;
            if(state)
            changeState(5);
            else
            changeState(0);    
        }
    }

    public class attacking implements Runnable{//UNDER CONSTRUCTION

        @Override
        public void run() {
            System.out.println("ATK");
            Entity hero = self;
            
            for(Entity other:Game.objects)
            {
                if(hero.intersects(other) && other instanceof Foe)
                {
                    System.out.println("Atacaste");
                    Game.objects.remove(other);
                }
            }
            try{  Thread.sleep(500); }
            catch(Exception e)
            {System.out.println("Error:"+e);
            }
            
            //System.out.println("IDLE");
               
        if(state)
        {
            changeState(5);
        }
        else
        {
            changeState(0);
        }
            
        attacking = false;
        
        }
        
    }
    
    
    @Override
    public BufferedImage getActualImage(){
        BufferedImage subSprite;
        subSprite = this.img_actual.getSubimage(this.spriteImgCoords[ac][sprite][0], this.spriteImgCoords[ac][sprite][1], this.spriteImgCoords[ac][sprite][2], this.spriteImgCoords[ac][sprite][3]);
        return subSprite;
    }

    @Override
    public void r_run(){
    
       if(Game.detectCollisionRight(self)!=CollisionState.RIGHT)
       {
        pos.x+=vel;
        changeState(5);
       }
    }
    
    public void r_attack()
    {
        attacking = true;
        
        changeState(8);
        
        new Thread(new attacking()).start();
    
    }

    public void l_attack()
    {
        attacking = true;
        
        changeState(3);
        
        new Thread(new attacking()).start();
    
    }
    
    
    @Override
    public void r_walk(){
        pos.x+=vel*.7;
    }

    @Override
    public void jump(){
      //  if(!falling){
	    if(Game.detectCollisionBottom(self) == CollisionState.BOTTOM){
            jumping=true;
            //falling=true;
            new Thread(new Jumping()).start();
            
            if(state)
            {
                changeState(7);
            }else
            {
                changeState(2);
            }		//fall();
            //new Thread(new Falling()).start();
        }
        
    }
	
    public void fall()
    {
      new Thread(new Falling()).start();
    }

    
    @Override
    public void l_run(){
        if(Game.detectCollisionLeft(self)!=CollisionState.LEFT)
        {
        pos.x-=vel;
        changeState(1);
        }
        
    }

    @Override
    public void l_walk(){
        pos.x+=vel*.7;
    }

    public void setImage(int n){
       
        changeState(n);
    }

    public void check()
    {
        System.out.println("POS X = "+pos.x);
        System.out.println("POS Y = "+pos.y);
        System.out.println("");
    }
}


class PlayerBehaviour implements KeyListener
{
protected String debug = "%s | action: %s";
public Player player=null;

boolean k_left = false;
boolean k_right = false;
boolean k_up = false;
boolean k_down = false;
boolean k_attack = false;
boolean k_check = false;

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        update();
        }
    };

    
    private void setTimer(){
        //System.out.println("Set timer");
        new Timer(20, actionListener).start();
    }

    public void update(){
        
        if (k_left)
        {
            player.l_run();       
        }

        if (k_right)
        {
            player.r_run();
        }

        if (k_up)
        {
            player.jump();
        }
        
        if (k_down){ 
        
            player.down();
        }
        
        if(k_attack)
        {
            if(player.state)
            {
                player.r_attack();
            }
            else
            {
                player.l_attack();
            }
        }
        
        if(k_check)
        {
            player.check();
        }
            

        
    }

    public PlayerBehaviour(Player p){
        setPlayer(p);
        setTimer();
    }

    public void setPlayer(Player p){
        player = p;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        
        int keyCode = event.getKeyCode();

        if (player==null){
            System.out.println(String.format(debug,this.getClass().toString(), "Player is NULL" ));
            return;            
        } 

        switch(keyCode){
            case KeyEvent.VK_LEFT:
                k_left = true;
                break;
            case KeyEvent.VK_RIGHT:
                k_right = true;
                break;
            case KeyEvent.VK_UP:
                k_up = true;
                break;
            case KeyEvent.VK_DOWN:
                k_down = true;
                break;
            case KeyEvent.VK_F:
                k_attack = true;
                break;
            case KeyEvent.VK_G:
                k_check = true;
                break;
        }  
    }

    @Override
    public void keyReleased(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_LEFT:
                k_left = false;
                player.setImage(0);
                break;
            case KeyEvent.VK_RIGHT:
                k_right = false;
                 player.setImage(5);
                break;
            case KeyEvent.VK_UP:
                k_up = false;
                break;
            case KeyEvent.VK_DOWN:
                k_down = false;
                break;
            case KeyEvent.VK_F:
                k_attack = false;
                 
                break;
            case KeyEvent.VK_G:
                k_check = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent event)
    {
    
    }
    
}

interface Movible
{
    abstract void r_run();
    abstract void r_walk();
    abstract void jump();
    abstract void l_run();
    abstract void l_walk();
}

interface Collisionable
{
    abstract Object isCollided();
}

enum CollisionState{
    NONE, UP, BOTTOM, RIGHT, LEFT;
}

class Game {
   
    static int VID=2;
    static ArrayList<Entity> objects  = new ArrayList<Entity>();
    static ArrayList<Background> hearts = new ArrayList<Background>();
    static ArrayList<Box> obstacles = new ArrayList<Box>();//UNDER CONSTRUCTION
    static ArrayList<Foe> enemies =  new ArrayList<Foe>();//UNDER CONSTRUCTION
    static ArrayList<Object> MISC = new ArrayList<Object>();//Elementos Varios
    
    public static CollisionState detectCollisionBottom(Entity entity){
	for(Entity other: Game.objects)
        {
                 
        if(entity!=other && entity.pos.y == other.pos.y+other.body.height)
        {
            if(entity.pos.x + entity.body.width > other.pos.x && entity.pos.x < other.pos.x+other.body.width )
            { 
                if(other instanceof Death)
                {
                    while(!Game.hearts.isEmpty())
                    {
                        Game.hearts.remove(VID--);
                    }
                }
                
                if(other instanceof Foe)
                {
                System.out.println("Collision State FOE");
                Game.hearts.remove(VID--);
                System.out.println("Vidas:"+(VID+1));
                    if(VID==-1)
                    {
                    System.out.println("YOU ARE DEAD"); System.exit(0);
                    }
                }
                
                if(other instanceof Goal)
                {
                System.exit(0);
                }
            }
        }
            //Collision con GOAL/FOE/DOOR 
        
            if(entity!=other && entity.pos.y+entity.body.height == other.pos.y)
        {
                if(entity.pos.x + entity.body.width > other.pos.x && entity.pos.x < other.pos.x+other.body.width )
                        {
                                if(other instanceof Goal){
                                System.exit(0);
                                }
                        }
        }
        
            //Collision con BOX .BOTTOM;
        if( entity!=other && entity.pos.y+entity.body.height == other.pos.y )
        {

            if(entity.pos.x + entity.body.width > other.pos.x && entity.pos.x < other.pos.x+other.body.width)
            {
            
            return CollisionState.BOTTOM;
            }
        }
            //Collision con BOX .UP; //non-used
        if(entity.pos.y == other.pos.y-other.body.height)
        {
        return CollisionState.UP;
        }
                
        }
       // System.out.println("Collision NONE");
        return CollisionState.NONE;
    }
    
    public static CollisionState detectCollisionLeft(Entity entity){
	for(Entity other: Game.objects)
        {
         
           if(entity!=other && entity.pos.x == other.pos.x+other.body.width)
        {
            
             if(other instanceof Foe)
                {
                    Game.hearts.remove(VID--);
                }
            return CollisionState.LEFT;
        }
        }
    return CollisionState.NONE;
    }
    
    public static CollisionState detectCollisionRight(Entity entity){
	for(Entity other: Game.objects){

            if(entity.pos.x + entity.body.width == other.pos.x && entity.pos.x+entity.body.width <= other.pos.x && entity.pos.y+entity.body.height >= other.pos.y && entity.pos.y <= other.pos.y)
            {
                if(entity.pos.y > other.pos.y)
                {
                    if(other instanceof Foe)
                    {
                        Game.hearts.remove(VID--);
                    }
                    
                    if(other instanceof Death)
                    {
                        while(!Game.hearts.isEmpty())
                        Game.hearts.remove(VID--);
                    }    
                       
                         
                         return CollisionState.RIGHT; 
                }   
                            
               
            }

    }
    return CollisionState.NONE;
    }

    public Game(){
        //players.add(new Hero());
    }
}
 
class Field extends JPanel{
    Player p1;
    
    int i=0;
    static ArrayList<Entity> objects  = new ArrayList<Entity>();

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            
	revalidate();
        repaint();
        
        }
    };

    public Field(){

        //Implementar patron de diseño
        //            [1280x720]
        p1 = setupPlayer();//player inicia en 200,150
        
        Background bg = new Background(0,0,0,0,".\\src\\smokey\\land\\Assets\\Map_level1.png");
        Background[] hearts =  new Background[3];
        
        
        //VIDAS 
        hearts[0] =  new Background(10,10,0,0,".\\src\\smokey\\land\\Assets\\heart 32x32.png");
        hearts[1] =  new Background(45,10,0,0,".\\src\\smokey\\land\\Assets\\heart 32x32.png");
        hearts[2] =  new Background(79,10,0,0,".\\src\\smokey\\land\\Assets\\heart 32x32.png");
        
                

//Ingresar los Token 
        
        Token Coin = new Token(150,50,25,25);//Token
        Goal Door = new Goal(1000,530,50,25);//Salida
        Death base = new Death(0,650,1280,50);//base del juego
        
        //FOE  [X][Y][ancho][alto][vida][daño]
        
        Foe foe1 = new Foe(760,450,50,70,3,1); //Collision 

//      < Box name = new Box ([X],[Y],[ancho],[alto]); >
 
        Box box1 = new Box(30,515,190,25);//Plat: 1
        Box box2 = new Box(60,450,65,25);// Arbol
        Box box3 = new Box(130,385,30,25);// Plat: 1.1
        Box box4 = new Box(355,515,225,25); //Plat: 2
        Box box5 = new Box(390,420,190,25);//plat: 2.1 flotante
        Box box6 = new Box(740,515,250,25);//plat: 3
//        Box box7 = new Box();
//        Box box8 = new Box();
//        Box box9 = new Box();


       // Game.objects.add(bg); //fondo
        
        Game.objects.add(base);
        Game.objects.add(box1);
        Game.objects.add(box2);
        Game.objects.add(box3);
        Game.objects.add(box4);
        Game.objects.add(box5);
        Game.objects.add(box6);
//        Game.objects.add(box7);
//        Game.objects.add(box8);
//        Game.objects.add(box9);

        Game.objects.add(bg);
        Game.objects.add(foe1);
        Game.objects.add(p1);
//        Game.objects.add(Coin);

for(i=0;i<3;i++)//Vidas
{
        Game.hearts.add(hearts[i]);
}

        
//        Game.objects.add(heart2);
//        Game.objects.add(heart3);
        
        //--- Movimiento Boxes/Entity 
//        BoxMovilityH(Entity box, int width, int lim, int time, int m)

//        BoxMovilityV bm1 = new BoxMovilityV(box1,800,400,500,50);
//        BoxMovilityH bm2 = new BoxMovilityH(box2,400,400,500,50);
//        BoxMovilityH bm3 = new BoxMovilityH(box3,400,400,500,50);
          BoxMovilityH m_foe1 = new BoxMovilityH(foe1,720,400,500,50);
          Music ms =  new Music();
          Clock clk = new Clock(100); //Tiempo .
          Game.MISC.add(clk);

        //--- Implementar Movimiento Foes
        
         Thread tms = new Thread(ms);
         Thread f1 = new Thread(m_foe1);
         Thread t_clk = new Thread(clk);
//        Thread t1 = new Thread(bm1); //Movimiento de plataforma
//        Thread t2 = new Thread(bm2); //Movimiento de plataforma
//        Thread t3 = new Thread(bm3); //Movimiento de plataforma
          
          tms.start();//AUDIO FALLA EN EQUIPOS CON POCOS HILOS DE PROCESAMIENTO 
          f1.start();
          t_clk.start();
//        t1.start();
//        t2.start();
//        t3.start();

		
        PlayerBehaviour pb = new PlayerBehaviour(p1);
        this.addKeyListener(pb);
        Timer tick = new Timer(20, actionListener);
        tick.setInitialDelay(0);
        tick.start();
     		
    }


    private Player setupPlayer(){
        
        Player p1 = new Player();
        
        p1.body.width = 40;
        p1.body.height = 70;
        p1.pos.x = 200;
        p1.pos.y = 150;
    //Hero ------------------------------
    // p1.setImageRightRun("C:\\Users\\edwar\\Downloads\\Demo\\hero\\run.png");
        p1.setImage(0);
        return p1;
    }
	
	

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
	
        for(Entity ent: Game.objects){
			Image subSprite;
            subSprite = ent.getActualImage();
            g.drawImage(subSprite, ent.pos.x, ent.pos.y, null);   
            
        }
        for(Entity ent: Game.hearts){
			Image subSprite;
            subSprite = ent.getActualImage();
            g.drawImage(subSprite, ent.pos.x, ent.pos.y, null);   
            
        }
        for(Object ent: Game.MISC)
        {
            
             ((Clock) ent).display.repaint();
        
        }
        
        
        
    }
}

public class Main extends JFrame{
   
    public Main(int width, int height)
	{
                final JFXPanel fxPanel = new JFXPanel();//Base para reproductor de mp3
                
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setSize(width, height);//JFrame Size
                Field field = new Field();//JPanel con componentes 
		this.add(field);
                field.setFocusable(true);
		this.setVisible(true);
                //field.setVisible(false);
        }
    public static void main(String[] args){
       new Main(1200,680);
   }
}
