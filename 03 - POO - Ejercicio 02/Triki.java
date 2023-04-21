import java.util.Scanner;


public class Triki{

	// Atributos
	Scanner entrada;
	String usuario_1;
	String usuario_2;
	int turno;
	char tablero [][];
	int partidas;
	String historial [];

	// Metodos
	public Triki(String usuario_1, String usuario_2){
		entrada = new Scanner(System.in);
		this.usuario_1 = usuario_1;
		this.usuario_2 = usuario_2;
		this.turno = 1; 	// 1. usuario1   2. usuario2
		this.partidas = 0;
		this.tablero = new char [3][3];   // '-'  'X'  'O'
		this.historial = new String [100];
		this.limpiarTablero();
	}

	public void limpiarTablero(){
		this.turno = 1;
		for (int i=0; i<3; i++ ) {
			for (int j=0; j<3; j++ ) {
				this.tablero[i][j] = '-';
			}
		}
	}

	public void start(){
		int opcion = 0;
		do{
			System.out.println("|--------------------------|");
			System.out.println("|- Partidas Jugadas:  "+this.partidas+"   -|");
			System.out.println("|- 1. Iniciar     2.Salir -|");
			System.out.println("|--------------------------|");
			System.out.print("|-> Ingrese una opcion: ");
			opcion = this.entrada.nextInt();
			if( opcion==1 ){
				System.out.println("|--------------------------------------|");
				System.out.println("|-      Quien inicia la partida?      -|");
				System.out.println("|-   1. "+usuario_1+"     2. "+usuario_2+"  -|");
				System.out.println("|--------------------------------------|");
				this.turno = this.entrada.nextInt();
				this.play();
				this.limpiarTablero();
				this.partidas++;
			}else if( opcion==2 ){
				System.out.println("|---- SALIENDO ----|");
			}else{
				System.out.println("|---- OPCION INVALIDA ----|");
			}
		}while( opcion!=2 );
	}

	public void play(){
		while( this.validarCasillasVacias() ){
			this.imprimirTablero();
			System.out.print("Jugador "+((this.turno==1)? usuario_1 : usuario_2 )+" ingrese posicion (1 al 9): ");
			int posicion = this.entrada.nextInt();

			if( this.verificarCasilla(posicion) ){
				this.marcarCasilla(posicion);
				// Almacenar en el historial
				if( this.verificarJugadaGanadora() ){
					System.out.println("\n");
					this.imprimirTablero();
					System.out.println("| ------ GANO el Jugador "+((this.turno==1)? usuario_1 : usuario_2 )+" ---- \n\n");
					break;
				}else{
					this.turno = (this.turno==1)? 2 : 1;
				}
			}else{
				System.out.println("Posicion invalida.");
			}
		} 

		if( !this.verificarJugadaGanadora() ){
			this.imprimirTablero();
			System.out.println(" --- TABLERO TERMINADO EN EMPATE ----\n\n");
		}
	}

	public boolean verificarCasilla(int pos){
		if(pos==1){
			return (this.tablero[0][0]=='-');
		}else if(pos==2){
			return (this.tablero[0][1]=='-');
		}else if(pos==3){
			return (this.tablero[0][2]=='-');
		}else if(pos==4){
			return (this.tablero[1][0]=='-');
		}else if(pos==5){
			return (this.tablero[1][1]=='-');
		}else if(pos==6){
			return (this.tablero[1][2]=='-');
		}else if(pos==7){
			return (this.tablero[2][0]=='-');
		}else if(pos==8){
			return (this.tablero[2][1]=='-');
		}else if(pos==9){
			return (this.tablero[2][2]=='-');
		}else{
			return false;
		}
	}

	public void marcarCasilla(int pos){
		char ficha = (this.turno==1)? 'X' : 'O';
		if(pos==1){
			this.tablero[0][0] = ficha;
		}else if(pos==2){
			this.tablero[0][1] = ficha;
		}else if(pos==3){
			this.tablero[0][2] = ficha;
		}else if(pos==4){
			this.tablero[1][0] = ficha;
		}else if(pos==5){
			this.tablero[1][1] = ficha;
		}else if(pos==6){
			this.tablero[1][2] = ficha;
		}else if(pos==7){
			this.tablero[2][0] = ficha;
		}else if(pos==8){
			this.tablero[2][1] = ficha;
		}else if(pos==9){
			this.tablero[2][2] = ficha;
		}
	}

	public boolean validarCasillasVacias(){
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if(this.tablero[i][j]=='-'){
					return true;
				}
			}
		}
		return false;
	}

	public void imprimirTablero(){
		for (int i=0; i<3; i++) {
			System.out.print("      ");
			for (int j=0; j<3; j++) {
				System.out.print("["+this.tablero[i][j]+"]");
			}
			System.out.println();
		}
	}

	public boolean verificarJugadaGanadora(){
		if( (this.tablero[0][0]==this.tablero[0][1] && this.tablero[0][0]==this.tablero[0][2] && this.tablero[0][0]!='-') || 
			(this.tablero[1][0]==this.tablero[1][1] && this.tablero[1][0]==this.tablero[1][2] && this.tablero[1][0]!='-') ||
			(this.tablero[2][0]==this.tablero[2][1] && this.tablero[2][0]==this.tablero[2][2] && this.tablero[2][0]!='-') ||

			(this.tablero[0][0]==this.tablero[1][0] && this.tablero[0][0]==this.tablero[2][0] && this.tablero[0][0]!='-') || 
			(this.tablero[0][1]==this.tablero[1][1] && this.tablero[0][1]==this.tablero[2][1] && this.tablero[0][1]!='-') || 
			(this.tablero[0][2]==this.tablero[1][2] && this.tablero[0][2]==this.tablero[2][2] && this.tablero[0][2]!='-') || 

			(this.tablero[0][0]==this.tablero[1][1] && this.tablero[0][0]==this.tablero[2][2] && this.tablero[0][0]!='-') || 
			(this.tablero[2][0]==this.tablero[1][1] && this.tablero[2][0]==this.tablero[0][2] && this.tablero[2][0]!='-')
		  ){ return true; }
		return false;
	}

	// Almacenar Tablero en Historial

}