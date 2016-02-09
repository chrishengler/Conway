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

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author chris
 *
 */
public class CellBoardTest {

	/**
	 * test setting up board
	 */
	@Test
	public void testCellBoard(){
		CellBoard instance = new CellBoard();
		instance.getCell(0,0);
		instance.getCell(99,99);
	}
	
	/**
	 * test getting cells from board
	 * 
	 */
	@Test
	public void testSetAlive(){
		CellBoard instance = new CellBoard();
		assertEquals(false,instance.getCell(0,0).isAlive());
		instance.setAlive(0,0,true);
		assertEquals(true,instance.getCell(0,0).isAlive());
	}
	
	/**
	 * test count of live neighbours
	 */
	@Test
	public void testGetLiveNeighbours(){
		CellBoard instance = new CellBoard();
		assertEquals(instance.getLiveNeighbours(1,1),0);
		instance.setAlive(0,2,true);
		instance.setAlive(2,0,true);
		instance.setAlive(0,0,true);
		assertEquals(3,instance.getLiveNeighbours(1,1));
		assertEquals(1,instance.getLiveNeighbours(2,1));
	}
	
	/**
	 * test wrap-around of out-of-bounds cell locations
	 */
	@Test
	public void testGetCell(){
		CellBoard instance = new CellBoard(50,50);
		instance.setAlive(0,0,true);
		assertEquals(instance.getCell(50,50).isAlive(),true);
		instance.setAlive(0,49,true);
		instance.setAlive(0,1,true);
		assertEquals(2,instance.getLiveNeighbours(1,49));
		assertEquals(3,instance.getLiveNeighbours(49,0));
	}
}
