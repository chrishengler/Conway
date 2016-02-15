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

import java.util.ArrayList;
import java.util.List;


/**
 * @author chris
 *
 */
public class CellBoard {

	private int m_x, m_y;
	private List<ArrayList<Cell>> m_board;
	
	/**
	 * no arg constructor, default to 100x100 board
	 */
	public CellBoard(){
		m_x=100;
		m_y=100;
		initialiseBoard();
	}
	
	/**
	 * constructor specifying x, y size of board
	 * 
	 * @param x
	 * @param y
	 */
	public CellBoard(int x, int y){	
		m_x=x;
		m_y=y;
		initialiseBoard();
	}
	
	/**
	 * construct from existing board
	 * 
	 * constructor taking existing board to clone within given x,y bounds
	 * if larger than existing board, new board will fill additional space with empty cells
	 * if smaller, only the area within dimensions of new board is copied
	 * if same dimensions, no effect
	 * 
	 * @param c clone existing board
	 */
	public CellBoard(int x, int y, CellBoard c){
		m_x = x;
		m_y = y;
		if(c.getX()==m_x && c.getY()==m_y){
			m_board = c.m_board;
			return;
		}
		initialiseBoard();
		for(int ii=0;ii<x;++ii){
			for(int jj=0;jj<y;++jj){
				if(jj>=c.getY()) break;
				if(c.isAlive(ii,jj)){
					setAlive(ii,jj,true);
				}
			}
			if(ii>=c.getX()) break;
		}
	}
	
	/**
	 * get x-size of board
	 * 
	 * @return x size of board
	 */
	public int getX(){
		return m_x;
	}
	
	/**
	 * get y-size of board
	 * 
	 * @return y size of board
	 */
	public int getY(){
		return m_y;
	}
	
	/**
	 * initialise the board with x, y specified
	 * 
	 * @param x
	 * @param y
	 */
	private void initialiseBoard(){
		m_board = new ArrayList<ArrayList<Cell>>(m_x);
		for(int ii=0;ii<m_x;++ii){
			m_board.add(new ArrayList<Cell>());
			for(int jj=0;jj<m_y;++jj){
				m_board.get(ii).add(new Cell());
			}
		}
	}
	
	/**
	 * return cell at x,y of board
	 * 
	 * return cell at x,y of board, cells outside board boundary wrap
	 * around both left-to-right and top-to-bottom (torus topology)
	 * 
	 * @param x
	 * @param y
	 * @return cell x,y
	 */
	public Cell getCell(int x, int y){
		if(x<0){
			int offset=(-1*x)%m_x;
			x=m_x-offset;
		}
		if(x>=m_x){ 
			x=x%m_x;
		}
		if(y<0){
			int offset=(-1*y)%m_y;
			y=m_y-offset;
		}
		if(y>=m_y){
			y=y%m_y;
		}
		return m_board.get(x).get(y);
	}
	
	/**
	 * set Cell x,y alive status
	 * 
	 * @param x
	 * @param y
	 * @param alive
	 */
	public void setAlive(int x, int y, boolean alive){
		getCell(x,y).setAlive(alive);
	}
	
	/**
	 * set Cell x,y alive, assume true
	 * 
	 * @param x
	 * @param y
	 */
	public void setAlive(int x, int y){
		setAlive(x,y,true);
	}
	
	public void toggleCell(int x, int y){
		getCell(x,y).toggle();
	}
	
	public boolean isAlive(int x, int y){
		return getCell(x,y).isAlive();
	}
	
	public int getLiveNeighbours(int x,int y){
		int count=0;
		for(int ii=-1;ii<=1;++ii){
			for(int jj=-1;jj<=1;++jj){
				if(ii==0 && jj==0) continue;
				if(getCell(x+ii,y+jj).isAlive()) count++;
			}
		}
		return count;
	}
	
	public void fillRandom(double p){
		for(int ii=0;ii<m_x;++ii){
			for(int jj=0;jj<m_y;++jj){
					setAlive(ii,jj,(Math.random()<=p));
			}
		}
	}
	
}
