/**
 * $Id$
 * 
 * Copyright (c) 2015-17 Stephane GALLAND.
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
package fr.utbm.info.ia51.labworks.pacman.environment.agent;

import fr.utbm.info.ia51.labworks.pacman.environment.agent.MazeChangeQuery;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.AgentBody;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.GhostBody;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.PacmanBody;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.PacmanObject;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.SuperPowerAccessor;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Capacity to manage a maze.
 * 
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SarlSpecification("0.11")
@SarlElementType(20)
@SuppressWarnings("all")
public interface MazeManager extends Capacity {
  /**
   * Replies the number of bodies in the maze
   */
  public abstract int getBodyCount();
  
  /**
   * Apply the list of actions.
   * @return true if the pacman is dead.
   */
  public abstract boolean applyActions(final List<MazeChangeQuery> actions);
  
  /**
   * Replies the perceptions.
   */
  public abstract Map<AgentBody, List<PacmanObject>> getPerceptions();
  
  /**
   * Replies the width of the maze.
   */
  public abstract int getMazeWidth();
  
  /**
   * Replies the height of the maze.
   */
  public abstract int getMazeHeight();
  
  /**
   * Replies all the objects.
   */
  public abstract List<PacmanObject> getPacmanObjects();
  
  /**
   * Replies a specific body.
   */
  public abstract AgentBody getAgentBody(final UUID id);
  
  /**
   * Replies super power accessor.
   */
  public abstract SuperPowerAccessor getSuperPowerAccessor(final UUID id);
  
  /**
   * Create a ghost body.
   */
  public abstract GhostBody createGhost(final int perceptionDistance);
  
  /**
   * Create a pacman body.
   */
  public abstract PacmanBody createPacman();
  
  /**
   * @ExcludeFromApidoc
   */
  public static class ContextAwareCapacityWrapper<C extends MazeManager> extends Capacity.ContextAwareCapacityWrapper<C> implements MazeManager {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public int getBodyCount() {
      try {
        ensureCallerInLocalThread();
        return this.capacity.getBodyCount();
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public boolean applyActions(final List<MazeChangeQuery> actions) {
      try {
        ensureCallerInLocalThread();
        return this.capacity.applyActions(actions);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public Map<AgentBody, List<PacmanObject>> getPerceptions() {
      try {
        ensureCallerInLocalThread();
        return this.capacity.getPerceptions();
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public int getMazeWidth() {
      try {
        ensureCallerInLocalThread();
        return this.capacity.getMazeWidth();
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public int getMazeHeight() {
      try {
        ensureCallerInLocalThread();
        return this.capacity.getMazeHeight();
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public List<PacmanObject> getPacmanObjects() {
      try {
        ensureCallerInLocalThread();
        return this.capacity.getPacmanObjects();
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public AgentBody getAgentBody(final UUID id) {
      try {
        ensureCallerInLocalThread();
        return this.capacity.getAgentBody(id);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public SuperPowerAccessor getSuperPowerAccessor(final UUID id) {
      try {
        ensureCallerInLocalThread();
        return this.capacity.getSuperPowerAccessor(id);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public GhostBody createGhost(final int perceptionDistance) {
      try {
        ensureCallerInLocalThread();
        return this.capacity.createGhost(perceptionDistance);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public PacmanBody createPacman() {
      try {
        ensureCallerInLocalThread();
        return this.capacity.createPacman();
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
