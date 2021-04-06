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

import fr.utbm.info.ia51.labworks.pacman.environment.agent.Perception;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.Direction;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.EventListener;
import io.sarl.lang.core.Space;
import java.util.UUID;

/**
 * Space that is representing the Jaak environment.
 * 
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SarlSpecification("0.11")
@SarlElementType(11)
@SuppressWarnings("all")
public interface MazeSpace extends Space {
  /**
   * Destroy the space.
   */
  public abstract void destroy();
  
  /**
   * Spawn the body with the given ID.
   * 
   * @param binder - the binder between the skill and the space.
   */
  public abstract void spawnBody(final EventListener binder);
  
  /**
   * Destroy the body with the given ID.
   * 
   * @param binder - the binder between the skill and the space.
   */
  public abstract void killBody(final EventListener binder);
  
  /**
   * Give the perceptions to the agents that is owning the given body.
   * 
   * @param sender - the identifier of the sender.
   * @param perception - the event to give to the agent.
   */
  public abstract void notifyPerception(final UUID sender, final Perception perception);
  
  /**
   * Emit an influence for the given agent.
   * 
   * @param influenceTime - the time at which the influence is applied.
   * @param emitter - the identifier of the emitter.
   * @param influence - the influence to emit.
   */
  public abstract void influence(final int influenceTime, final UUID emitter, final Direction direction);
}
