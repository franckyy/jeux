package game;



import game.*;
import gui.GameCanvas;
import gui.GameFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Game implements KeyListener {
	private Scanner scan;
	private int numberOfPlayers;
	private String[] tokens = new String[7];
	private ArrayList<Player> players = new ArrayList<Player>();
	private Board board;
	private GameFrame display;
	private Player nextPlayer;
	private int nextPlayerIdx;
	private boolean inPlay;
	private GameCanvas canvas;


	public Game(){

		setUp();
		startGame();
		
	}

	private void startGame() {
		inPlay = true;
		int notBankruptPlayers = players.size();
		int nextPlayerIdx = 0;
		nextPlayer = players.get(nextPlayerIdx);
		while(inPlay){
		
		}
		}

	/**
	 * Set up method
	 */
	private void setUp() {
		fillTokens();
		board = new Board(players);
		canvas = new GameCanvas(board);
		board.setUp();
		numberOfPlayers = JOptionPane.showOptionDialog(null, "How many players?", "How many players?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, new Integer[]{2,3,4,5,6,7}, 2) + 2;
		setUpPlayers();
		display = new GameFrame("Monopoly",canvas,this);
		board.setDrawables();
	}

	/**
	 * Set up method, adds the tokens players can choose from.
	 */
	private void fillTokens() {
		int i = 0;
		tokens[i++]=("Hat");
		tokens[i++]=("Car");
		tokens[i++]=("Wheelbarrow");
		tokens[i++]=("Dog");
		tokens[i++]=("Thimble");
		tokens[i++]=("Boot");
		tokens[i++]=("Iron");
	}

	/**
	 * Records how many players, and all of the players individual details.
	 */
	private void setUpPlayers() {
		if(numberOfPlayers == 1) numberOfPlayers++;//resort to a default of two players if the user doesn't make a selection
		 	
			 for(int i = 0;i<numberOfPlayers; i++){   
			 int choice = JOptionPane.showOptionDialog(null, "Which piece would you like to be?", "Player "+(i+1), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, tokens, tokens);
			 String name = JOptionPane.showInputDialog(null, "Enter a name", "Player "+(i+1), JOptionPane.DEFAULT_OPTION);  
			 players.add(new Player(name, tokens[choice]));
			 shrinkTokens(choice);
			 } 
		
		 
		}
	
	private void shrinkTokens(int removeIdx) {
		if(tokens.length == 1) return;
		String[] temp = new String[tokens.length -1];
		int tokenIdx = 0, tempIdx = 0;
		for(; tokenIdx<tokens.length; tokenIdx++){
			if(tokenIdx == removeIdx) continue;
			temp[tempIdx] = tokens[tokenIdx];
			tempIdx++;
		}
		tokens = temp;
	}

	public static void main(String []args){
		new Game();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		

		canvas.repaint();
		
		try{
		if(inPlay){
			int keyCode = e.getKeyCode();
			if(keyCode==KeyEvent.VK_SPACE){
				board.turn(nextPlayer);
			}
			}
		char keyCode = (char) e.getKeyCode();
		board.keyPressed(e);
		canvas.repaint();
		}
		catch (Exception q) {
			// invalid keystroke
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		canvas.repaint();
		if(inPlay){
			int keyCode = e.getKeyCode();
			if(keyCode==KeyEvent.VK_SPACE){
			nextPlayerIdx++;
		if(nextPlayerIdx==players.size()) nextPlayerIdx = 0;
		nextPlayer= players.get(nextPlayerIdx);
			}
		}
		canvas.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char keyCode = (char) e.getKeyCode();
		board.keyTyped(e);
	
	}


}
