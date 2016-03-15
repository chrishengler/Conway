/*
 * Copyright (c) 2016, Chris Hengler
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package io.github.chrishengler.conway;

/**
 * @author Chris Hengler
 *
 */
public class Game{

	private int m_x, m_y;
	private CellBoard m_board;
	
	/**
	 * no-arg constructor, default size from CellBoard
	 */
	public Game(){
		m_x = 100;
		m_y = 100;
		m_board = new CellBoard();
	}
	
	/**
	 * constructor with x, y dimensions specified
	 * 
	 * @param x
	 * @param y
	 */
	public Game(int x, int y){
		m_x = x;
		m_y = y;
		m_board = new CellBoard(x,y);
	}
	
	/**
	 * constructor with x,y dimensions specified and board populated
	 * @param x
	 * @param y
	 * @param g
	 */
	public Game(int x, int y, Game g){
		m_x = x;
		m_y = y;
		m_board = new CellBoard(x,y,g.m_board);
	}
	
	/**
	 * resize board to have size (in cells) x,y
	 * 
	 * @param x
	 * @param y
	 */
	public void resizeBoard(int x, int y){
		m_x = x;
		m_y = y;
		m_board = new CellBoard(x,y,m_board);
	}
	
	/**
	 * fill board randomly
	 * 
	 * Fill the board randomly. 
	 * Each cell has probability p of being set to alive 
	 * p is fractional probability, so should be in range 0 ≤ p ≤ 1
	 * 
	 * @param p fraction of board to fill
	 */
	public void fillRandom(double p){
		m_board.fillRandom(p);
	}
	
	private CellBoard getNextBoard(){
		CellBoard newboard = new CellBoard(m_x,m_y);
		for(int ii=0;ii<m_x;++ii){
			for(int jj=0;jj<m_y;++jj){
				int n = m_board.getLiveNeighbours(ii,jj);
				if(m_board.isAlive(ii,jj)){
					if(n==2 || n==3){
						newboard.setAlive(ii,jj,true);
					}
				}
				else if(n==3){
					newboard.setAlive(ii,jj,true);
				}
			}
		}
		return newboard;
	}

	/**
	 * update game status to next step
	 */
	public void nextStep(){
		m_board = getNextBoard();
	}
	
	/**
	 * return game's CellBoard (m_board)
	 * 
	 * @return CellBoard m_board
	 */
	public CellBoard getBoard(){
		return m_board;
	}
	
	/**
	 * calls CellBoard.setAlive(x,y,alive);
	 * 
	 * @param x
	 * @param y
	 * @param alive
	 */
	public void setAlive(int x, int y, boolean alive){
		m_board.setAlive(x,y,alive);
	}
	
	/**
	 * calls CellBoard.toggleCell(x,y);
	 * 
	 * @param x
	 * @param y
	 */
	public void toggleCell(int x, int y){
		m_board.toggleCell(x,y);
	}
	
	/**
	 * calls CellBoard.isAlive(x,y);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isAlive(int x, int y){
		return m_board.isAlive(x,y);
	}
	
	/**
	 * get number of columns in board
	 * 
	 * @return number of columns
	 */
	public int getX(){
		return m_x;
	}
	
	/**
	 * get number of rows in board
	 * 
	 * @return number of rows
	 */
	public int getY(){
		return m_y;
	}
	

}
