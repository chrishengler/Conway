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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
        
/**
 *
 * @author Chris Hengler
 */
@SuppressWarnings("serial")
public class ConwayCanvas extends javax.swing.JPanel implements MouseListener, MouseMotionListener, ComponentListener, Runnable{

  private int m_cellsize;
  private Game m_game;
  private int m_lastx, m_lasty;
  /**
   * used as trigger to stop running game in background thread
   */
	public boolean m_stop;
	private int m_steptime;

  /**
   * Creates new form ConwayCanvas
   * 
   * @param g the Game we'll be drawing
   */
  public ConwayCanvas(Game g){
    setVisible(true);
    m_game = g;
    initComponents();
  }
  
  /**
   * return Game m_game
   * 
   * @return m_game
   */
  public Game getGame(){
  	return m_game;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Dimension getPreferredSize(){
    return new Dimension(600,600);
  }
  
  private void calcCellSize(){
    int dx = this.getWidth()/m_game.getX();
    int dy = this.getHeight()/m_game.getY();
    m_cellsize = dx < dy ? dx : dy;
    if(m_cellsize<5){
    	m_cellsize=5; //too small, remove rows/columns to get something better
    }
   	verifyGridDimensions(); 
  }
  
  private void verifyGridDimensions(){
  	int nx,ny;
  	nx=(this.getWidth()-1)/m_cellsize; //-1 as extra space is needed for border line
  	ny=(this.getHeight()-1)/m_cellsize;
  	if(nx!=m_game.getX() || ny!=m_game.getY()){
  		m_game.resizeBoard(nx,ny);
  	}
  	repaint();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
  	calcCellSize();
    g.setColor(Color.black);
    for(int ii=0;ii<=m_game.getX();++ii){
      g.drawLine((int)(ii*m_cellsize),0,(int)(ii*m_cellsize),(int)(m_game.getY()*m_cellsize));
      if(ii==m_game.getX()) continue;
    	for(int jj=0;jj<=m_game.getY();++jj){
        if(ii==0){
    		  g.drawLine(0, (int)(jj*m_cellsize), (int)(m_game.getX()*m_cellsize), (int)(jj*m_cellsize));
    	  }
        if(jj==m_game.getY()) continue;
        if(m_game.isAlive(ii,jj)){
        	g.fillRect((int)(ii*m_cellsize),(int)(jj*m_cellsize),m_cellsize+1,m_cellsize+1);
        }
      }
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    setPreferredSize(new java.awt.Dimension(600, 600));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 600, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 350, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents

  /**
   * {@inheritDoc}
   */
	@Override
	public void mouseReleased(MouseEvent e){

	}
	
  /**
   * {@inheritDoc}
   */
	@Override
	public void mouseDragged(MouseEvent e){
		if(!isLastCell(e)){
			toggleCell(e);
			setLastCell(e);
		}
	}
  
  /**
   * {@inheritDoc}
   */
	@Override
	public void mouseClicked(MouseEvent e){	
	}

  /**
   * {@inheritDoc}
   */
	@Override
	public void mousePressed(MouseEvent e){	
		toggleCell(e);
		setLastCell(e);
	}

  /**
   * {@inheritDoc}
   */
	@Override
	public void mouseEntered(MouseEvent e){ }

  /**
   * {@inheritDoc}
   */
	@Override
	public void mouseExited(MouseEvent e){ }

  /**
   * {@inheritDoc}
   */
	@Override
	public void mouseMoved(MouseEvent e){	}

	private void toggleCell(MouseEvent e){
		int x = (int)((e.getPoint().x)/m_cellsize);
		int y = (int)((e.getPoint().y)/m_cellsize);
		if(x>=0 && x<m_game.getX() && y>=0 && y<m_game.getY()){
			m_game.toggleCell(x,y);
		}
		repaint();
	}
	
	private void setLastCell(MouseEvent e){
		m_lastx = (int)((e.getPoint().x)/m_cellsize);
		m_lasty = (int)((e.getPoint().y)/m_cellsize);
	}
	
	private boolean isLastCell(MouseEvent e){
		int x = (int)((e.getPoint().x)/m_cellsize);
		int y = (int)((e.getPoint().y)/m_cellsize);
		return (x==m_lastx && y==m_lasty);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
	 */
  /**
   * {@inheritDoc}
   */
	@Override
	public void componentResized(ComponentEvent e){
		calcCellSize();
		repaint();
	}

  /**
   * {@inheritDoc}
   */
	@Override
	public void componentMoved(ComponentEvent e){	}

  /**
   * {@inheritDoc}
   */
	@Override
	public void componentShown(ComponentEvent e){	}

  /**
   * {@inheritDoc}
   */
	@Override
	public void componentHidden(ComponentEvent e){	}

  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
	

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
  /**
   * {@inheritDoc}
   */
	@Override
	public void run(){
		m_stop = false;
		try{
			while(!m_stop){
				m_game.nextStep();
				repaint();
				Thread.sleep(m_steptime);
				if(Thread.interrupted()){
					m_stop = true;
					throw new InterruptedException();
				}
			};
		}
		catch(InterruptedException e){ 
			return;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			throw e;
		}
		
	}
	
	/**
	 * set time between steps in ms
	 * 
	 * @param time delay in ms beween iterations
	 */
	public void setStepTime(int time){
		m_steptime = time;
	}
	
}
