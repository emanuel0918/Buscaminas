import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Buscaminas extends JFrame implements Runnable{
	private int juego[][];
	private int descubiertas[][];
	private int indices[][];
	private int size_x;
	private int size_y;
	private int size;
	private int minas;
	private int banderas;
	private int dificultad;
	private int time;
	private boolean lose;
	private boolean win;
	private String nombre;
    private ArrayList<JButton> botones;
	private ArrayList<JLabel> casillas;
	private ArrayList<Panel> paneles;
	private int[] banderas_list;
	private Point[] coordenadas;
	private JLabel time_lbl,flags_lbl,guy_lbl;
	private Panel encabezado,contenido; 
	private JMenu juego_menu;
	private JMenu record_menu;
	private JMenuItem nuevoJuego_mi;
	private JMenuItem record_mi;
	private JMenuBar menuBar;
	//Hilo
	Thread t;
	int minutos,segundos;
	String time_txt,flags_txt;
	int mp, banderas_negativo;
	int coordenada_x,coordenada_y;
	boolean winning;
	Point casillaVacia;
	Queue<Point> casillasVacias;
	ArrayList<Point> casillasNoVacias;
	ArrayList<Point> casillasSiVacias;
	int indiceCasilla;
    
    public Buscaminas(){
		this.size_x=9;
		this.size_y=9;
		this.minas=10;
		init();
    }
	
	public Buscaminas(int dificultad){
		switch(dificultad){
			case 1:
				this.size_x=16;
				this.size_y=16;
				this.minas=40;
				break;
			case 2:
				this.size_x=16;
				this.size_y=30;
				this.minas=99;
				break;
			default:
				this.size_x=9;
				this.size_y=9;
				this.minas=10;
				break;
			
		}
		init();
	}
	
	private void init(){
		
		//init components
        setTitle("Buscaminas");
        setBounds(300,10,900,700);
        setResizable(false);
		//Quitar esta línea de código para la ejecución
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Menu
		menuBar=new JMenuBar();
		juego_menu=new JMenu();
		nuevoJuego_mi=new JMenuItem();
		juego_menu.setText("Juego");
		nuevoJuego_mi.setText("Nuevo Juego");
		nuevoJuego_mi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //juego();
				System.out.println("Nuevo Juego");
            }
        });
		juego_menu.add(nuevoJuego_mi);
		menuBar.add(juego_menu);
		record_menu=new JMenu();
		record_mi=new JMenuItem();
		record_menu.setText("Puntuaciones");
		record_mi.setText("Mejores Puntuaciones");
		record_mi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //juego();
				System.out.println("Mejores Puntuaciones");
            }
        });
		record_menu.add(record_mi);
		menuBar.add(record_menu);
		setJMenuBar(menuBar);
		
		t = new Thread(this);
        t.start();
		
		juego();
		
	}
	
	public void juego(){
		//juego
		Random random =new Random();
		int r1,r2,minas2=minas;
		size=size_x*size_y;
		banderas_list=new int[size];
		coordenadas=new Point[size];
		banderas=minas;
		juego=new int[size_x][size_y];
		descubiertas=new int[size_x][size_y];
		indices=new int[size_x][size_y];
		lose=false;
		win=false;
		time=0;
		for(int i=0;i<size;i++){
			banderas_list[i]=0;
		}
		for(int i=0,n=0;i<size_x;i++){
			for(int j=0;j<size_y;j++,n++){
				coordenadas[n]=new Point(i,j);
				indices[i][j]=n;
			}
		}
		for(int i=0;i<size_x;i++){
			for(int j=0;j<size_y;j++){
				juego[i][j]=0;
				descubiertas[i][j]=0;
			}
		}
		while(minas2>0){
			r1=random.nextInt(size_x);
			r2=random.nextInt(size_y);
			if(juego[r1][r2]!=-1){
				juego[r1][r2]=-1;
				minas2--;
			}
		}
		for(int i=0,n_bombs=0;i<size_x;i++){
			for(int j=0;j<size_y;j++){
				n_bombs=0;
				if(juego[i][j]!=-1){
					try{
						if(juego[i-1][j-1]==-1){
							n_bombs++;
						}
					}catch(Exception e1){
						
					}
					try{
						if(juego[i][j-1]==-1){
							n_bombs++;
						}
					}catch(Exception e2){
						
					}
					try{
						if(juego[i+1][j-1]==-1){
							n_bombs++;
						}
					}catch(Exception e3){
						
					}
					try{
						if(juego[i-1][j]==-1){
							n_bombs++;
						}
					}catch(Exception e4){
						
					}
					try{
						if(juego[i+1][j]==-1){
							n_bombs++;
						}
					}catch(Exception e5){
						
					}
					try{
						if(juego[i-1][j+1]==-1){
							n_bombs++;
						}
					}catch(Exception e6){
						
					}
					try{
						if(juego[i][j+1]==-1){
							n_bombs++;
						}
					}catch(Exception e7){
						
					}
					try{
						if(juego[i+1][j+1]==-1){
							n_bombs++;
						}
					}catch(Exception e8){
						
					}
					juego[i][j]=n_bombs;
				}
			}
		}
		for(int i=0;i<size_x;i++){
			for(int j=0;j<size_y;j++){
				System.out.print(juego[i][j]+" ");
			}
			System.out.println();
		}
		
        //Componentes
		botones=new ArrayList<>();
		JButton boton;
		casillas=new ArrayList<>();
		JLabel casilla;
		paneles=new ArrayList<>();
		Panel panel;
		encabezado=new Panel();
		add("North",encabezado);
		encabezado.setLayout(new GridLayout(1,3));
		flags_txt=String.format("%02d", banderas);
		flags_lbl=new JLabel(flags_txt,SwingConstants.CENTER);
		flags_lbl.setOpaque(true);
		flags_lbl.setBackground(Color.BLACK);
		flags_lbl.setForeground(Color.RED);
		flags_lbl.setFont(new Font("Serif", Font.PLAIN, 25));
		time_lbl=new JLabel("00:00",SwingConstants.CENTER);
		time_lbl.setOpaque(true);
		time_lbl.setBackground(Color.BLACK);
		time_lbl.setForeground(Color.RED);
		time_lbl.setFont(new Font("Serif", Font.PLAIN, 25));
		guy_lbl=new JLabel();
		guy_lbl.setIcon(new Happy(55,55));
		guy_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		encabezado.add(flags_lbl);
		encabezado.add(guy_lbl);
		encabezado.add(time_lbl);
		contenido =new Panel();
		contenido.setLayout(new GridLayout(size_x,size_y));
		for(int i=0, n=0;i<size_x;i++){
			for(int j=0;j<size_y;j++,n++){
				panel=new Panel();
				boton=new JButton();
				casilla=new JLabel();
				boton.setMnemonic(n);
				boton.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
					}
					public void mouseEntered(MouseEvent e){
						
					}
					public void mouseExited(MouseEvent e){
						
					}
					public void mousePressed(MouseEvent e){
						aberTuActionPerformed(e);
						
					}
					public void mouseReleased(MouseEvent e){
						
					}
				});
				switch(juego[i][j]){
					case -1:
					case 0:
						break;
					case 1:
						casilla.setForeground(Color.BLUE);
						break;
					case 2:
						casilla.setForeground(Color.GREEN);
						break;
					default:
						casilla.setForeground(Color.RED);
						break;
				}
				casilla.setOpaque(true);
				casilla.setHorizontalAlignment(SwingConstants.CENTER);
				casilla.setFont(new Font("Serif", Font.PLAIN, 25));
				if(juego[i][j]>0){
					casilla.setText(Integer.toString(juego[i][j]));
				}else if(juego[i][j]==-1){
					casilla.setIcon(new Bomb(55,55));
				}
				botones.add(boton);
				casillas.add(casilla);
				panel.setLayout(new GridLayout(1,1));
				paneles.add(panel);
				contenido.add(paneles.get(n));
				paneles.get(n).add("Center",botones.get(n));
				//contenido.add(botones.get(n));
				//contenido.add(casillas.get(n));
			}
			
		}
		add("Center",contenido);
		setVisible(true);
		//Ingresar el nombre del jugador
		nombre = JOptionPane.showInputDialog("Ingresa tu nombre:");
	}
	
	public void aberTuActionPerformed(MouseEvent e){
		if(!lose){
			JButton btn =(JButton)e.getSource();
			mp=btn.getMnemonic();
			if (e.getButton() == MouseEvent.BUTTON1){
				//System.out.println("Left button clicked");
				coordenada_x=coordenadas[mp].getX();
				coordenada_y=coordenadas[mp].getY();
				if(juego[coordenada_x][coordenada_y]==-1){
					guy_lbl.setIcon(new Failed(55,55));
					paneles.get(mp).remove(botones.get(mp));
					paneles.get(mp).add(casillas.get(mp));
					casillas.get(mp).setOpaque(true);
					casillas.get(mp).setBackground(Color.RED);
					casillas.get(mp).setIcon(new Bomb(35,35));
					for(int i=0,n=0;i<size_x;i++){
						for(int j=0;j<size_y;j++,n++){
							if(n!=mp){
								if(juego[i][j]==-1){
									paneles.get(n).remove(botones.get(n));
									paneles.get(n).add(casillas.get(n));
									casillas.get(n).setIcon(new Bomb(35,35));
								}
							}
						}
					}
					lose=true;
					//t.destroy();
				}else{
					guy_lbl.setIcon(new Surprised(55,55));
					paneles.get(mp).remove(botones.get(mp));
					paneles.get(mp).add(casillas.get(mp));
					descubiertas[coordenada_x][coordenada_y]=1;
					//Descubrir casillas vacias
					if(juego[coordenada_x][coordenada_y]==0){
						casillasVacias=new LinkedList<>();
						casillasNoVacias=new ArrayList<>();
						casillasSiVacias=new ArrayList<>();
						casillaVacia=new Point(coordenada_x,coordenada_y);
						casillasVacias.add(casillaVacia);
						casillasSiVacias.add(casillaVacia);
						while(!casillasVacias.isEmpty()){
							coordenada_x=casillasVacias.peek().getX();
							coordenada_y=casillasVacias.peek().getY();
							//System.out.println("casilla "+coordenada_x+","+coordenada_y);
							try{
								casillaVacia=new Point(coordenada_x-1,coordenada_y-1);
								if(juego[casillaVacia.getX()][casillaVacia.getY()]==0){
									if(!arrayListHavePoint(casillasSiVacias,casillaVacia)){
										casillasSiVacias.add(casillaVacia);
										casillasVacias.add(casillaVacia);
									}
								}else if(juego[casillaVacia.getX()][casillaVacia.getY()]>0){
									if(!arrayListHavePoint(casillasNoVacias,casillaVacia)){
										casillasNoVacias.add(casillaVacia);
									}
								}
							}catch(Exception ex1){
								
							}
							try{
								casillaVacia=new Point(coordenada_x,coordenada_y-1);
								if(juego[casillaVacia.getX()][casillaVacia.getY()]==0){
									if(!arrayListHavePoint(casillasSiVacias,casillaVacia)){
										casillasSiVacias.add(casillaVacia);
										casillasVacias.add(casillaVacia);
									}
								}else if(juego[casillaVacia.getX()][casillaVacia.getY()]>0){
									if(!arrayListHavePoint(casillasNoVacias,casillaVacia)){
										casillasNoVacias.add(casillaVacia);
									}
								}
							}catch(Exception ex2){
								
							}
							try{
								casillaVacia=new Point(coordenada_x+1,coordenada_y-1);
								if(juego[casillaVacia.getX()][casillaVacia.getY()]==0){
									if(!arrayListHavePoint(casillasSiVacias,casillaVacia)){
										casillasSiVacias.add(casillaVacia);
										casillasVacias.add(casillaVacia);
									}
								}else if(juego[casillaVacia.getX()][casillaVacia.getY()]>0){
									if(!arrayListHavePoint(casillasNoVacias,casillaVacia)){
										casillasNoVacias.add(casillaVacia);
									}
								}
							}catch(Exception ex3){
								
							}
							try{
								casillaVacia=new Point(coordenada_x-1,coordenada_y);
								if(juego[casillaVacia.getX()][casillaVacia.getY()]==0){
									if(!arrayListHavePoint(casillasSiVacias,casillaVacia)){
										casillasSiVacias.add(casillaVacia);
										casillasVacias.add(casillaVacia);
									}
								}else if(juego[casillaVacia.getX()][casillaVacia.getY()]>0){
									if(!arrayListHavePoint(casillasNoVacias,casillaVacia)){
										casillasNoVacias.add(casillaVacia);
									}
								}
							}catch(Exception ex4){
								
							}
							try{
								casillaVacia=new Point(coordenada_x+1,coordenada_y);
								if(juego[casillaVacia.getX()][casillaVacia.getY()]==0){
									if(!arrayListHavePoint(casillasSiVacias,casillaVacia)){
										casillasSiVacias.add(casillaVacia);
										casillasVacias.add(casillaVacia);
									}
								}else if(juego[casillaVacia.getX()][casillaVacia.getY()]>0){
									if(!arrayListHavePoint(casillasNoVacias,casillaVacia)){
										casillasNoVacias.add(casillaVacia);
									}
								}
							}catch(Exception ex5){
								
							}
							try{
								casillaVacia=new Point(coordenada_x-1,coordenada_y+1);
								if(juego[casillaVacia.getX()][casillaVacia.getY()]==0){
									if(!arrayListHavePoint(casillasSiVacias,casillaVacia)){
										casillasSiVacias.add(casillaVacia);
										casillasVacias.add(casillaVacia);
									}
								}else if(juego[casillaVacia.getX()][casillaVacia.getY()]>0){
									if(!arrayListHavePoint(casillasNoVacias,casillaVacia)){
										casillasNoVacias.add(casillaVacia);
									}
								}
							}catch(Exception ex6){
								
							}
							try{
								casillaVacia=new Point(coordenada_x,coordenada_y+1);
								if(juego[casillaVacia.getX()][casillaVacia.getY()]==0){
									if(!arrayListHavePoint(casillasSiVacias,casillaVacia)){
										casillasSiVacias.add(casillaVacia);
										casillasVacias.add(casillaVacia);
									}
								}else if(juego[casillaVacia.getX()][casillaVacia.getY()]>0){
									if(!arrayListHavePoint(casillasNoVacias,casillaVacia)){
										casillasNoVacias.add(casillaVacia);
									}
								}
							}catch(Exception ex7){
								
							}
							try{
								casillaVacia=new Point(coordenada_x+1,coordenada_y+1);
								if(juego[casillaVacia.getX()][casillaVacia.getY()]==0){
									if(!arrayListHavePoint(casillasSiVacias,casillaVacia)){
										casillasSiVacias.add(casillaVacia);
										casillasVacias.add(casillaVacia);
									}
								}else if(juego[casillaVacia.getX()][casillaVacia.getY()]>0){
									if(!arrayListHavePoint(casillasNoVacias,casillaVacia)){
										casillasNoVacias.add(casillaVacia);
									}
								}
							}catch(Exception ex8){
								
							}
							casillasVacias.poll();
						}
						for(Point pointCasillaVacia : casillasSiVacias){
							coordenada_x=pointCasillaVacia.getX();
							coordenada_y=pointCasillaVacia.getY();
							indiceCasilla=indices[coordenada_x][coordenada_y];
							descubiertas[coordenada_x][coordenada_y]=1;
							paneles.get(indiceCasilla).remove(botones.get(indiceCasilla));
							paneles.get(indiceCasilla).add(casillas.get(indiceCasilla));

						}
						for(Point pointCasillaNoVacia : casillasNoVacias){
							coordenada_x=pointCasillaNoVacia.getX();
							coordenada_y=pointCasillaNoVacia.getY();
							indiceCasilla=indices[coordenada_x][coordenada_y];
							descubiertas[coordenada_x][coordenada_y]=1;
							paneles.get(indiceCasilla).remove(botones.get(indiceCasilla));
							paneles.get(indiceCasilla).add(casillas.get(indiceCasilla));

						}
					}
					//Algoritmo para saber si gana
					winning=true;
					for(int i=0;i<size_x;i++){
						for(int j=0;j<size_y;j++){
							if(juego[i][j]!=-1 && descubiertas[i][j]==0){
								winning=false;
								break;
							}
						}
						if(!winning){
							break;
						}
					}
					win=winning;
					if(win){
						win();
					}
				}
				
			} else if (e.getButton() == MouseEvent.BUTTON2){
				//System.out.println("Middle button clicked");
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				//System.out.println("Right button clicked");
				if(banderas_list[mp]==1){
					banderas++;
					banderas_list[mp]=0;
					botones.get(mp).setIcon(null);
				}else{
					banderas--;
					banderas_list[mp]=1;
					botones.get(mp).setIcon(new Flag(35,35));
				}
				if((banderas/10)!=0){
					flags_txt=Integer.toString(banderas);
				}else{
					if(banderas>=0){
						flags_txt="0"+Integer.toString(banderas);
					}else{
						banderas_negativo=banderas*-1;
						flags_txt="-0"+Integer.toString(banderas_negativo);
					}
				}
				flags_lbl.setText(flags_txt);
			}
		}
	}
	
	private boolean arrayListHavePoint(ArrayList<Point> array, Point point){
		boolean having=false;
		for(Point p : array){
			if(p.getX()==point.getX() && p.getY()==point.getY()){
				having =true;
				break;
			}
		}
		return having;
	}
	
	private void win(){
		guy_lbl.setIcon(new Success(55,55));
		JOptionPane.showMessageDialog(null, "¡¡¡ Ganaste !!!");
	}
	
	public void run(){
		while(true){
			try{
				minutos=time/60;
				segundos=time%60;
				time_txt=String.format("%02d", minutos)
				+":"+String.format("%02d", segundos);
				
				time_lbl.setText(time_txt);
				//
				t.sleep(1000);
				if(!lose && !win){
					time++;
				}
			}catch(Exception e){
			}
		}
	}
	
    public static void main(String[] args) {
        Buscaminas b = new Buscaminas();
    }
}
