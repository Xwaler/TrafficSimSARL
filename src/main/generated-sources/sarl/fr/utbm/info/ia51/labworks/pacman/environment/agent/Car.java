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

import fr.utbm.info.ia51.labworks.pacman.environment.maze.Direction;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Interface that must be used by the player for moving his/her avatar.
 * 
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Car {
  private Direction lastInputDirection;
  
  private final UUID bodyId;
  
  private final Logger logger;
  
  Car(final UUID bodyId, final Logger logger) {
    this.bodyId = bodyId;
    this.logger = logger;
  }
  
  /**
   * Move the player avatar.
   */
  public synchronized void move(final Direction direction) {
    if ((this.logger != null)) {
      if ((direction == null)) {
        this.logger.info("Player wants to stay static.");
      } else {
        this.logger.info(MessageFormat.format("Player wants to move to {0}.", direction.name()));
      }
    }
    this.lastInputDirection = direction;
  }
  
  /**
   * Replies the last input direction.
   */
  @Pure
  synchronized Direction getDirection() {
    return this.lastInputDirection;
  }
  
  /**
   * Replies the identifier of the body.
   */
  @Pure
  public UUID getBodyId() {
    return this.bodyId;
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
    Car other = (Car) obj;
    if (!Objects.equals(this.bodyId, other.bodyId))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.bodyId);
    return result;
  }
}
