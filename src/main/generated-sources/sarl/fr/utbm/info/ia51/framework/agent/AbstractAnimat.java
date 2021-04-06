/**
 * $Id$
 * 
 * Copyright (c) 2014-17 Stephane GALLAND <stephane.galland@utbm.fr>.
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
package fr.utbm.info.ia51.framework.agent;

import com.google.common.base.Objects;
import fr.utbm.info.ia51.framework.agent.MotionAlgorithmOutput;
import fr.utbm.info.ia51.framework.agent.PhysicEnvironment;
import fr.utbm.info.ia51.framework.agent.StandardPhysicEnvironment;
import fr.utbm.info.ia51.framework.environment.DynamicType;
import fr.utbm.info.ia51.framework.environment.Influence;
import fr.utbm.info.ia51.framework.environment.Percept;
import fr.utbm.info.ia51.framework.environment.SimulationAgentReady;
import fr.utbm.info.ia51.framework.environment.StopSimulation;
import fr.utbm.info.ia51.framework.math.Vector2f;
import io.sarl.core.DefaultContextInteractions;
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
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.11")
@SarlElementType(19)
@SuppressWarnings("all")
public class AbstractAnimat extends Agent {
  private DynamicType behaviorType;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    this.overridableInitializationStage(occurrence);
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
    SimulationAgentReady _simulationAgentReady = new SimulationAgentReady();
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_simulationAgentReady);
  }
  
  /**
   * Change the type of the behavior.
   * 
   * @param type the type.
   */
  protected void setBehaviorType(final DynamicType type) {
    this.behaviorType = type;
  }
  
  /**
   * Replies the type of the behavior.
   * 
   * @param type the type.
   */
  @Pure
  protected DynamicType getBehaviorType() {
    return this.behaviorType;
  }
  
  protected void overridableInitializationStage(final Initialize it) {
    Object _get = it.parameters[0];
    Object _get_1 = it.parameters[1];
    StandardPhysicEnvironment physicSkill = new StandardPhysicEnvironment(((UUID) _get), 
      ((UUID) _get_1));
    Object _get_2 = it.parameters[2];
    this.behaviorType = ((DynamicType) _get_2);
    this.<StandardPhysicEnvironment>setSkill(physicSkill, PhysicEnvironment.class);
  }
  
  @Pure
  protected float getMaxLinear(final Percept p) {
    float _xifexpression = (float) 0;
    boolean _equals = Objects.equal(this.behaviorType, DynamicType.STEERING);
    if (_equals) {
      _xifexpression = p.getMaxLinearAcceleration();
    } else {
      _xifexpression = p.getMaxLinearSpeed();
    }
    return _xifexpression;
  }
  
  @Pure
  protected float getMaxAngular(final Percept p) {
    float _xifexpression = (float) 0;
    boolean _equals = Objects.equal(this.behaviorType, DynamicType.STEERING);
    if (_equals) {
      _xifexpression = p.getMaxAngularAcceleration();
    } else {
      _xifexpression = p.getMaxAngularSpeed();
    }
    return _xifexpression;
  }
  
  protected void emitInfluence(final MotionAlgorithmOutput output, final Influence... influences) {
    if ((output != null)) {
      DynamicType _type = output.getType();
      if ((_type == DynamicType.STEERING)) {
        PhysicEnvironment _$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER();
        _$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER.influenceSteering(output.getLinear(), output.getAngular(), influences);
      } else {
        PhysicEnvironment _$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER_1 = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER();
        _$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER_1.influenceKinematic(output.getLinear(), output.getAngular(), influences);
      }
    } else {
      PhysicEnvironment _$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER_2 = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER();
      Vector2f _vector2f = new Vector2f();
      _$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER_2.influenceSteering(_vector2f, 0f, influences);
    }
  }
  
  protected void doNothing() {
    PhysicEnvironment _$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER();
    Vector2f _vector2f = new Vector2f();
    _$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER.influenceKinematic(_vector2f, 0f);
  }
  
  @Pure
  protected Percept first(final List<Percept> list) {
    Percept _xifexpression = null;
    boolean _isEmpty = list.isEmpty();
    if (_isEmpty) {
      _xifexpression = null;
    } else {
      _xifexpression = list.get(0);
    }
    return _xifexpression;
  }
  
  private void $behaviorUnit$StopSimulation$1(final StopSimulation occurrence) {
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  @Extension
  @ImportedCapacityFeature(PhysicEnvironment.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT;
  
  @SyntheticMember
  @Pure
  private PhysicEnvironment $CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT$CALLER() {
    if (this.$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT == null || this.$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT.get() == null) {
      this.$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT = $getSkill(PhysicEnvironment.class);
    }
    return $castSkill(PhysicEnvironment.class, this.$CAPACITY_USE$FR_UTBM_INFO_IA51_FRAMEWORK_AGENT_PHYSICENVIRONMENT);
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
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$StopSimulation(final StopSimulation occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$StopSimulation$1(occurrence));
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
  public AbstractAnimat(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public AbstractAnimat(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public AbstractAnimat(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
