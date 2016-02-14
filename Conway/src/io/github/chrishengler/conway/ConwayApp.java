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

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Component;
import javax.swing.Box;

/**
 * @author chris
 *
 */
public class ConwayApp{

	private JFrame m_frame;
	private ConwayCanvas m_conwayCanvas;
	private Game m_game;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run(){
				try{
					ConwayApp window = new ConwayApp();
					window.m_frame.setVisible(true);
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConwayApp(){
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		m_frame = new JFrame();
		m_frame.setBounds(100,100,700,650);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		m_frame.getContentPane().add(toolBar, BorderLayout.NORTH);

		m_game = new Game();
		
		m_conwayCanvas = new ConwayCanvas(m_game);
		m_conwayCanvas.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				m_conwayCanvas.componentResized(e);
			}
		});
		m_conwayCanvas.addMouseMotionListener(new MouseMotionAdapter(){
			@Override
			public void mouseDragged(MouseEvent e){
				m_conwayCanvas.mouseDragged(e);
			}
		});
		m_conwayCanvas.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				m_conwayCanvas.mousePressed(e);
			}
			@Override
			public void mouseReleased(MouseEvent e){
				m_conwayCanvas.mouseReleased(e);
			}
		});
		m_frame.getContentPane().add(m_conwayCanvas, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		m_frame.getContentPane().add(panel, BorderLayout.WEST);
		
		JButton btnNextStep = new JButton("Next Step");
		btnNextStep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m_game.nextStep();
				m_conwayCanvas.repaint();
			}
		});
		panel.add(btnNextStep);
		
		Component verticalStrut = Box.createVerticalStrut(14);
		m_frame.getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(14);
		m_frame.getContentPane().add(horizontalStrut, BorderLayout.EAST);
		
	}
	
	

}
