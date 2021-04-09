/**
 * $Id$
 * 
 * Copyright (c) 2008-17 Stephane GALLAND.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * This program is free software; you can redistribute it and/or modify
 */
package fr.utbm.info.ia51.labworks.pacman.ui;

import fr.utbm.info.ia51.framework.math.Point2i;
import fr.utbm.info.ia51.framework.util.Resources;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.Controller;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.EnvironmentEvent;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.EnvironmentListener;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.Player;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.Direction;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.GhostBody;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.PacmanBody;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.PacmanObject;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.PillObject;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.WallObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Swing UI for the PacMan game.
 * 
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class PacManGUI extends JFrame implements KeyListener, EnvironmentListener {
  /**
   * Swing panel that is displaying the environment state.
   * 
   * @author $Author: sgalland$
   * @version $FullVersion$
   * @mavengroupid $GroupId$
   * @mavenartifactid $ArtifactId$
   */
  @SarlSpecification("0.11")
  @SarlElementType(10)
  private static class GridPanel extends JPanel {
    private Map<Point2i, PacmanObject> objects;
    
    private AtomicInteger time = new AtomicInteger();
    
    public GridPanel() {
      this.setBackground(Color.BLACK);
    }
    
    public void setObjects(final int time, final Map<Point2i, PacmanObject> objects) {
      synchronized (this.getTreeLock()) {
        this.time.set(time);
        this.objects = objects;
        this.repaint();
      }
    }
    
    public void paint(final Graphics g) {
      super.paint(g);
      int px = 0;
      int py = 0;
      Graphics2D g2d = ((Graphics2D) g);
      int _get = this.time.get();
      boolean isEvenTime = ((_get % 2) == 0);
      if ((this.objects != null)) {
        Set<Map.Entry<Point2i, PacmanObject>> _entrySet = this.objects.entrySet();
        for (final Map.Entry<Point2i, PacmanObject> entry : _entrySet) {
          {
            Point2i pos = entry.getKey();
            PacmanObject obj = entry.getValue();
            int _x = pos.getX();
            px = (PacManGUI.CELL_WIDTH * _x);
            int _y = pos.getY();
            py = (PacManGUI.CELL_HEIGHT * _y);
            if ((obj instanceof WallObject)) {
              g2d.setColor(Color.BLUE);
              g2d.fillRect(px, py, PacManGUI.CELL_WIDTH, PacManGUI.CELL_HEIGHT);
            } else {
              if ((obj instanceof PacmanBody)) {
                boolean _isSuperPacman = ((PacmanBody)obj).isSuperPacman();
                if (_isSuperPacman) {
                  g2d.setColor(Color.MAGENTA);
                } else {
                  g2d.setColor(Color.YELLOW);
                }
                if (isEvenTime) {
                  g2d.fillArc(
                    (px + 1), (py + 1), (PacManGUI.CELL_WIDTH - 2), (PacManGUI.CELL_HEIGHT - 2), 
                    45, 270);
                } else {
                  g2d.fillArc(
                    (px + 1), (py + 1), (PacManGUI.CELL_WIDTH - 2), (PacManGUI.CELL_HEIGHT - 2), 
                    5, 350);
                }
                g2d.setColor(this.getBackground());
                int eyex = ((px + PacManGUI.DEMI_CELL_WIDTH) - 2);
                int eyey = ((py + (PacManGUI.DEMI_CELL_HEIGHT / 2)) - 2);
                if ((!isEvenTime)) {
                  eyex++;
                }
                g2d.fillOval(eyex, eyey, 4, 4);
              } else {
                if ((obj instanceof GhostBody)) {
                  g2d.setColor(Color.WHITE);
                  g2d.fillArc(
                    (px + 3), (py + 1), (PacManGUI.CELL_WIDTH - 6), (PacManGUI.CELL_HEIGHT - 2), 
                    0, 180);
                  int up = (py + PacManGUI.DEMI_CELL_HEIGHT);
                  int bottom1 = ((py + PacManGUI.CELL_HEIGHT) - 2);
                  int bottom2 = (bottom1 - 4);
                  int lleft = (px + 3);
                  int left = (px + (PacManGUI.DEMI_CELL_WIDTH / 2));
                  int middle = (px + PacManGUI.DEMI_CELL_WIDTH);
                  int right = ((px + PacManGUI.DEMI_CELL_WIDTH) + (PacManGUI.DEMI_CELL_WIDTH / 2));
                  int rright = ((px + PacManGUI.CELL_WIDTH) - 3);
                  if ((!isEvenTime)) {
                    left--;
                    middle = (middle - 2);
                    bottom2 = (bottom2 + 2);
                  }
                  int[] xx = ((int[])Conversions.unwrapArray(Collections.<Integer>unmodifiableSet(CollectionLiterals.<Integer>newHashSet(Integer.valueOf(lleft), Integer.valueOf(lleft), Integer.valueOf(left), Integer.valueOf(middle), Integer.valueOf(right), Integer.valueOf(rright), Integer.valueOf(rright))), int.class));
                  int[] yy = ((int[])Conversions.unwrapArray(Collections.<Integer>unmodifiableSet(CollectionLiterals.<Integer>newHashSet(Integer.valueOf(up), Integer.valueOf(bottom1), Integer.valueOf(bottom2), Integer.valueOf(bottom1), Integer.valueOf(bottom2), Integer.valueOf(bottom1), Integer.valueOf(up))), int.class));
                  g2d.fillPolygon(xx, yy, Math.min(xx.length, yy.length));
                  int dx = 0;
                  int dy = 0;
                  int eyex_1 = (((px + PacManGUI.DEMI_CELL_WIDTH) - 5) + dx);
                  int eyey_1 = ((py + (PacManGUI.DEMI_CELL_HEIGHT / 2)) + dy);
                  g2d.setColor(this.getBackground());
                  g2d.fillOval(eyex_1, eyey_1, 5, 5);
                  g2d.setColor(Color.WHITE);
                  eyex_1 = (((px + PacManGUI.DEMI_CELL_WIDTH) + 2) + dx);
                  g2d.setColor(this.getBackground());
                  g2d.fillOval(eyex_1, eyey_1, 5, 5);
                } else {
                  if ((obj instanceof PillObject)) {
                    boolean _isSuperPill = ((PillObject)obj).isSuperPill();
                    if (_isSuperPill) {
                      g2d.setColor(Color.MAGENTA);
                      if (isEvenTime) {
                        g2d.fillOval(((px + PacManGUI.DEMI_CELL_WIDTH) - 4), ((py + PacManGUI.DEMI_CELL_HEIGHT) - 4), 8, 8);
                      } else {
                        g2d.fillOval(((px + PacManGUI.DEMI_CELL_WIDTH) - 5), ((py + PacManGUI.DEMI_CELL_HEIGHT) - 5), 10, 10);
                      }
                    } else {
                      g2d.setColor(Color.WHITE);
                      if (isEvenTime) {
                        g2d.fillOval(((px + PacManGUI.DEMI_CELL_WIDTH) - 2), ((py + PacManGUI.DEMI_CELL_HEIGHT) - 2), 4, 4);
                      } else {
                        g2d.fillOval(((px + PacManGUI.DEMI_CELL_WIDTH) - 3), ((py + PacManGUI.DEMI_CELL_HEIGHT) - 3), 6, 6);
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    
    @Override
    @Pure
    @SyntheticMember
    public boolean equals(final Object obj) {
      return super.equals(obj);
    }
    
    @Override
    @Pure
    @SyntheticMember
    public int hashCode() {
      int result = super.hashCode();
      return result;
    }
    
    @SyntheticMember
    private static final long serialVersionUID = 1003808637L;
  }
  
  /**
   * Width in pixels of a cell.
   */
  public static final int CELL_WIDTH = 20;
  
  /**
   * Height in pixels of a cell.
   */
  public static final int CELL_HEIGHT = 20;
  
  /**
   * Demi-width in pixels of a cell.
   */
  public static final int DEMI_CELL_WIDTH = (PacManGUI.CELL_WIDTH / 2);
  
  /**
   * Demi-height in pixels of a cell.
   */
  public static final int DEMI_CELL_HEIGHT = (PacManGUI.CELL_HEIGHT / 2);
  
  private final AtomicBoolean isInit = new AtomicBoolean();
  
  private final PacManGUI.GridPanel gridPanel;
  
  private final JButton startButton;
  
  private final long waitingDuration;
  
  private Player player;
  
  private Controller controller;
  
  /**
   * @param waitingDuration - the duration of sleeping before giving the hand to the simulator back.
   */
  public PacManGUI(final long waitingDuration) {
    try {
      this.waitingDuration = waitingDuration;
      this.setTitle("Pacman Simulator");
      URL pacmanIcon = Resources.getResource(PacManGUI.class, "pacman.png");
      this.setIconImage(ImageIO.read(pacmanIcon));
      URL iconURL = Resources.getResource(this.getClass(), "play.png");
      ImageIcon _imageIcon = new ImageIcon(iconURL);
      JButton _jButton = new JButton(_imageIcon);
      this.startButton = _jButton;
      this.startButton.setToolTipText("Start simulation");
      this.startButton.setEnabled(false);
      final ActionListener _function = (ActionEvent it) -> {
        Controller ctrl = this.controller;
        if (((ctrl != null) && (!ctrl.isStarted()))) {
          this.startButton.setEnabled(false);
          ctrl.startSimulation();
        }
      };
      this.startButton.addActionListener(_function);
      JPanel topPanel = new JPanel();
      BoxLayout _boxLayout = new BoxLayout(topPanel, BoxLayout.X_AXIS);
      topPanel.setLayout(_boxLayout);
      topPanel.add(this.startButton);
      PacManGUI.GridPanel _gridPanel = new PacManGUI.GridPanel();
      this.gridPanel = _gridPanel;
      this.gridPanel.setFocusable(true);
      this.gridPanel.addKeyListener(this);
      JScrollPane sc = new JScrollPane(this.gridPanel);
      Container _contentPane = this.getContentPane();
      BorderLayout _borderLayout = new BorderLayout();
      _contentPane.setLayout(_borderLayout);
      this.getContentPane().add(BorderLayout.NORTH, topPanel);
      this.getContentPane().add(BorderLayout.CENTER, sc);
      Dimension _dimension = new Dimension(600, 600);
      this.setPreferredSize(_dimension);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.pack();
      this.requestFocus();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void bindPlayer(final Player player) {
    synchronized (this) {
      this.player = player;
    }
  }
  
  public void unbindPlayer(final Player player) {
    synchronized (this) {
      this.player = null;
    }
  }
  
  public void bindController(final Controller controller) {
    synchronized (this) {
      this.controller = controller;
      boolean _isStarted = this.controller.isStarted();
      this.startButton.setEnabled((!_isStarted));
    }
  }
  
  public void unbindController(final Controller controller) {
    synchronized (this) {
      this.startButton.setEnabled(false);
      this.controller = null;
    }
  }
  
  public void gameOver() {
    this.setVisible(false);
    JOptionPane.showMessageDialog(this, 
      "The Pacman is dead!", 
      this.getTitle(), 
      JOptionPane.INFORMATION_MESSAGE);
    this.dispose();
  }
  
  public void environmentChanged(final EnvironmentEvent event) {
    boolean _get = this.isInit.get();
    if ((!_get)) {
      this.isInit.set(true);
      int _width = event.getWidth();
      int _height = event.getHeight();
      Dimension _dimension = new Dimension(
        (PacManGUI.CELL_WIDTH * _width), 
        (PacManGUI.CELL_HEIGHT * _height));
      this.gridPanel.setPreferredSize(_dimension);
      this.revalidate();
      this.pack();
      this.setVisible(true);
      this.gridPanel.requestFocus();
    }
    this.gridPanel.setObjects(event.getTime(), event.getObjects());
    if ((this.waitingDuration > 0)) {
      try {
        Thread.sleep(this.waitingDuration);
      } catch (final Throwable _t) {
        if (_t instanceof InterruptedException) {
          final InterruptedException e = (InterruptedException)_t;
          throw new RuntimeException(e);
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    }
  }
  
  public void keyPressed(final KeyEvent e) {
    Player player = null;
    synchronized (this) {
      player = this.player;
    }
    if ((player != null)) {
      int _keyCode = e.getKeyCode();
      switch (_keyCode) {
        case KeyEvent.VK_LEFT:
          player.move(Direction.WEST);
          break;
        case KeyEvent.VK_RIGHT:
          player.move(Direction.EAST);
          break;
        case KeyEvent.VK_UP:
          player.move(Direction.NORTH);
          break;
        case KeyEvent.VK_DOWN:
          player.move(Direction.SOUTH);
          break;
      }
    }
  }
  
  public void keyReleased(final KeyEvent e) {
  }
  
  public void keyTyped(final KeyEvent e) {
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PacManGUI other = (PacManGUI) obj;
    if (other.waitingDuration != this.waitingDuration)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Long.hashCode(this.waitingDuration);
    return result;
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 3932571013L;
}
