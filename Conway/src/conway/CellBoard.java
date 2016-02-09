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
package conway;

import java.util.List;
import java.util.ArrayList;


/**
 * @author chris
 *
 */
public class CellBoard {

	private List<ArrayList<Cell>> m_board;
	
	/**
	 * no arg constructor, default to 100x100 board
	 */
	public CellBoard(){
		initialiseBoard(100,100);
	}
	
	/**
	 * constructor specifying x, y size of board
	 * 
	 * @param x
	 * @param y
	 */
	public CellBoard(int x, int y){	
		initialiseBoard(x,y);
	}
	
	/**
	 * initialise the board with x, y specified
	 * 
	 * @param x
	 * @param y
	 */
	private void initialiseBoard(int x, int y){
		m_board = new ArrayList<ArrayList<Cell>>(x);
		for(int ii=0;ii<x;++ii){
			m_board.add(new ArrayList<Cell>());
			for(int jj=0;jj<y;++jj){
				m_board.get(ii).add(new Cell());
			}
		}
	}
	
	/**
	 * return cell at x,y of board
	 * 
	 * @param x
	 * @param y
	 * @return cell x,y
	 */
	public Cell getCell(int x, int y){
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
	
}
