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

import fr.utbm.info.ia51.framework.math.Point2i;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.MazeFrontEnd;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.MazeSpace;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.MazeSpaceSpecification;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.MazeSpaceUtils;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.Perception;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.PacmanObject;
import io.sarl.core.Behaviors;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.Skill;
import java.util.List;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Skill for being the front-end of a maze.
 * 
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SarlSpecification("0.11")
@SarlElementType(22)
@SuppressWarnings("all")
public class DefaultMazeFrontEndSkill extends Skill implements MazeFrontEnd {
  private MazeSpace mazeSpace;
  
  public void install() {
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
    UUID spaceId = MazeSpaceUtils.getSpaceIDInContext(_$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.getDefaultContext());
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
    Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER();
    this.mazeSpace = _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.getDefaultContext().<MazeSpace>getOrCreateSpaceWithSpec(MazeSpaceSpecification.class, spaceId, 
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2.getDefaultSpace(), _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.asEventListener());
  }
  
  public void sendPerception(final int time, final UUID bodyID, final List<PacmanObject> objects, final Point2i position) {
    Perception p = new Perception(time, bodyID, objects, position);
    this.mazeSpace.notifyPerception(this.getOwner().getID(), p);
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @Extension
  @ImportedCapacityFeature(Behaviors.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS;
  
  @SyntheticMember
  @Pure
  private Behaviors $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $getSkill(Behaviors.class);
    }
    return $castSkill(Behaviors.class, this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
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
  public DefaultMazeFrontEndSkill() {
    super();
  }
  
  @SyntheticMember
  public DefaultMazeFrontEndSkill(final Agent agent) {
    super(agent);
  }
}
