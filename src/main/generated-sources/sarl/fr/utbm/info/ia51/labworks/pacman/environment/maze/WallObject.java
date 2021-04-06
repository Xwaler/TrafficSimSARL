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
package fr.utbm.info.ia51.labworks.pacman.environment.maze;

import fr.utbm.info.ia51.framework.math.Point2i;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.Maze;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.PacmanObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Wall in the PacMan environment.
 * 
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class WallObject extends PacmanObject {
  public WallObject(final int x, final int y, final Maze maze) {
    super(x, y, maze);
  }
  
  public WallObject(final Point2i position, final Maze maze) {
    super(position, maze);
  }
  
  @Pure
  public final boolean isOccluder() {
    return true;
  }
  
  @Pure
  public final boolean isPickable() {
    return false;
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 783871754L;
}
