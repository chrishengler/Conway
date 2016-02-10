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

import org.junit.Before;
import org.junit.Test;

/**
 * @author chris
 *
 */
public class GameTest{

	private Game instance;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception{
		instance = new Game(50,50);
		instance.setAlive(0,0,true);
		instance.setAlive(1,0,true);
		instance.setAlive(2,0,true);
	}

	/**
	 * test setting cells alive via Game object
	 */
	@Test
	public void testSetAlive(){
		assertEquals(false,instance.isAlive(10,10));
		instance.setAlive(10,10,true);
		assertEquals(true,instance.isAlive(10,10));
	}
	
	/**
	 * test generating next step
	 */
	@Test
	public void testStep(){
		instance.nextStep();
		//test new cell birth
		assertEquals(true,instance.isAlive(1,1));
		//test cell staying alive
		assertEquals(true,instance.isAlive(1,0));
		//test loneliness
		assertEquals(false,instance.isAlive(0,0));
		assertEquals(false,instance.isAlive(2,0));
		//test wrap-around
		assertEquals(true,instance.isAlive(1,49));
	}

}
