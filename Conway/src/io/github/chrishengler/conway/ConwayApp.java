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
import java.awt.GridLayout;
import java.awt.Dimension;
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
import javax.swing.BoxLayout;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextPane;
import javax.swing.text.*;

/**
 * @author chris
 *
 */
public class ConwayApp{

	private JFrame m_frame;
	private ConwayCanvas m_conwayCanvas;
	private Game m_game;
	private double m_fillFrac;
	
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
		
		Dimension controlSize = new Dimension(100,50);
		Dimension controlPanelMaximumSize = new Dimension(120,250);
		
		Box verticalBox = Box.createVerticalBox();
		m_frame.getContentPane().add(verticalBox, BorderLayout.WEST);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(0,1));
		controlPanel.setMaximumSize(controlPanelMaximumSize);
		verticalBox.add(controlPanel);
		
		JButton btnNextStep = new JButton("Next Step");
		btnNextStep.setPreferredSize(controlSize);
		btnNextStep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m_game.nextStep();
				m_conwayCanvas.repaint();
			}
		});
		controlPanel.add(btnNextStep);
		
		Box fillFracBox = Box.createVerticalBox();
		controlPanel.add(fillFracBox);
		
		JSlider fillFracSlider = new JSlider(0,100);
		fillFracSlider.setAlignmentX(0);
		fillFracBox.add(fillFracSlider);
		fillFracSlider.setPreferredSize(controlSize);
		
		JButton btnRandomFill = new JButton("Random Fill");
		btnRandomFill.setAlignmentX(0);
		fillFracBox.add(btnRandomFill);
		btnRandomFill.setPreferredSize(controlSize);
		btnRandomFill.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				m_game.fillRandom(m_fillFrac);
			}
		});
		
		fillFracSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				m_fillFrac = fillFracSlider.getValue()/100.;
			}
		});
				
		Component verticalStrut = Box.createVerticalStrut(14);
		m_frame.getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(14);
		m_frame.getContentPane().add(horizontalStrut, BorderLayout.EAST);
		
	}
	
	

}
