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
package fr.utbm.info.ia51.labworks.pacman.players;

import fr.utbm.info.ia51.labworks.pacman.environment.agent.DefaultMazeMotionSkill;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.MazeMotion;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.PacManIsDead;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.Perception;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.Direction;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Ghost.
 * 
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SarlSpecification("0.11")
@SarlElementType(19)
@SuppressWarnings("all")
public class Ghost extends Agent {
  private final Random random = new Random();
  
  private Direction previousDirection;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    DefaultMazeMotionSkill _defaultMazeMotionSkill = new DefaultMazeMotionSkill();
    this.<DefaultMazeMotionSkill>setSkill(_defaultMazeMotionSkill);
  }
  
  private void $behaviorUnit$PacManIsDead$1(final PacManIsDead occurrence) {
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  private void $behaviorUnit$Perception$2(final Perception occurrence) {
  }
  
  @Extension
  @ImportedCapacityFeature(MazeMotion.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMOTION;
  
  @SyntheticMember
  @Pure
  private MazeMotion $CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMOTION$CALLER() {
    if (this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMOTION == null || this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMOTION.get() == null) {
      this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMOTION = $getSkill(MazeMotion.class);
    }
    return $castSkill(MazeMotion.class, this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMOTION);
  }
  
  @Extension
  @ImportedCapacityFeature(Lifecycle.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  
  @SyntheticMember
  @Pure
  private Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class);
    }
    return $castSkill(Lifecycle.class, this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$PacManIsDead(final PacManIsDead occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$PacManIsDead$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Perception(final Perception occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Perception$2(occurrence));
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
  public Ghost(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public Ghost(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public Ghost(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
