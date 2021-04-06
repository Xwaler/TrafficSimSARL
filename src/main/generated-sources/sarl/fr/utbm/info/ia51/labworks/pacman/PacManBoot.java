/**
 * $Id$
 * 
 * Copyright (c) 2014-15 Stephane GALLAND <stephane.galland@utbm.fr>.
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
package fr.utbm.info.ia51.labworks.pacman;

import fr.utbm.info.ia51.labworks.pacman.environment.agent.Environment;
import fr.utbm.info.ia51.labworks.pacman.ui.PacManGUI;
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
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Launcher of the simulation framework.
 * 
 * This launcher needs the {@link http://www.janusproject.io Janus platform}.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(19)
@SuppressWarnings("all")
public class PacManBoot extends Agent {
  /**
   * Width of the world (in number of cells).
   */
  private final int WIDTH = 25;
  
  /**
   * Height of the world (in number of cells).
   */
  private final int HEIGHT = 25;
  
  /**
   * Number of ghosts at the start-up.
   */
  private final int NB_GHOSTS = 3;
  
  /**
   * Percpetion distance for the agents (usually the ghosts).
   */
  private final int PERCEPTION_DISTANCE = 5;
  
  /**
   * The UI will force the environment agent to wait for it.
   */
  private final int WAITING_DURATION = 500;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    PacManGUI ui = new PacManGUI(this.WAITING_DURATION);
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawn(Environment.class, Integer.valueOf(this.WIDTH), Integer.valueOf(this.HEIGHT), Integer.valueOf(this.NB_GHOSTS), Integer.valueOf(this.PERCEPTION_DISTANCE), ui);
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
    PacManBoot other = (PacManBoot) obj;
    if (other.WIDTH != this.WIDTH)
      return false;
    if (other.HEIGHT != this.HEIGHT)
      return false;
    if (other.NB_GHOSTS != this.NB_GHOSTS)
      return false;
    if (other.PERCEPTION_DISTANCE != this.PERCEPTION_DISTANCE)
      return false;
    if (other.WAITING_DURATION != this.WAITING_DURATION)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.WIDTH);
    result = prime * result + Integer.hashCode(this.HEIGHT);
    result = prime * result + Integer.hashCode(this.NB_GHOSTS);
    result = prime * result + Integer.hashCode(this.PERCEPTION_DISTANCE);
    result = prime * result + Integer.hashCode(this.WAITING_DURATION);
    return result;
  }
  
  @SyntheticMember
  public PacManBoot(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public PacManBoot(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public PacManBoot(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
