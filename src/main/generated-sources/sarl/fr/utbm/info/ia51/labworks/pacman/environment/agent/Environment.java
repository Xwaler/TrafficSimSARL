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

import fr.utbm.info.ia51.labworks.pacman.environment.agent.Action;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.Car;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.Controller;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.EnvironmentListener;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.MazeChangeQuery;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.MazeFrontEnd;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.MazeManager;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.RunBeginingOfStep;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.RunEndOfStep;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.11")
@SarlElementType(19)
@SuppressWarnings("all")
public class Environment extends Agent {
  private ArrayList<EnvironmentListener> listeners = CollectionLiterals.<EnvironmentListener>newArrayList();
  
  private Car car;
  
  private Controller controller;
  
  private int time = 0;
  
  private final LinkedList<MazeChangeQuery> actions = CollectionLiterals.<MazeChangeQuery>newLinkedList();
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
  }
  
  protected void fireEnvironmentChange() {
  }
  
  protected void firePlayerBinding() {
  }
  
  protected void firePlayerUnbinding() {
  }
  
  protected void fireControllerBinding() {
  }
  
  protected void fireControllerUnbinding() {
  }
  
  protected void fireGameOver() {
  }
  
  private void $behaviorUnit$Destroy$1(final Destroy occurrence) {
  }
  
  private void $behaviorUnit$Action$2(final Action occurrence) {
  }
  
  @SyntheticMember
  @Pure
  private boolean $behaviorUnitGuard$Action$2(final Action it, final Action occurrence) {
    return (occurrence.time >= it.time);
  }
  
  private void $behaviorUnit$RunEndOfStep$3(final RunEndOfStep occurrence) {
  }
  
  private void $behaviorUnit$RunBeginingOfStep$4(final RunBeginingOfStep occurrence) {
  }
  
  @Extension
  @ImportedCapacityFeature(MazeManager.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER;
  
  @SyntheticMember
  @Pure
  private MazeManager $CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER() {
    if (this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER == null || this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER.get() == null) {
      this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER = $getSkill(MazeManager.class);
    }
    return $castSkill(MazeManager.class, this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER);
  }
  
  @Extension
  @ImportedCapacityFeature(MazeFrontEnd.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEFRONTEND;
  
  @SyntheticMember
  @Pure
  private MazeFrontEnd $CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEFRONTEND$CALLER() {
    if (this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEFRONTEND == null || this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEFRONTEND.get() == null) {
      this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEFRONTEND = $getSkill(MazeFrontEnd.class);
    }
    return $castSkill(MazeFrontEnd.class, this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEFRONTEND);
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
  private void $guardEvaluator$Action(final Action occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    if ($behaviorUnitGuard$Action$2(occurrence, occurrence)) {
      ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Action$2(occurrence));
    }
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$RunEndOfStep(final RunEndOfStep occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$RunEndOfStep$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Destroy(final Destroy occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Destroy$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$RunBeginingOfStep(final RunBeginingOfStep occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$RunBeginingOfStep$4(occurrence));
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
    Environment other = (Environment) obj;
    if (other.time != this.time)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.time);
    return result;
  }
  
  @SyntheticMember
  public Environment(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public Environment(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public Environment(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
