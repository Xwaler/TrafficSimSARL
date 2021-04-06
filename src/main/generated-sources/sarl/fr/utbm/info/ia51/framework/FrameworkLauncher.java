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
package fr.utbm.info.ia51.framework;

import com.google.common.collect.Iterables;
import fr.utbm.info.ia51.framework.SimulatorAgent;
import fr.utbm.info.ia51.framework.environment.DynamicType;
import fr.utbm.info.ia51.framework.environment.Environment;
import fr.utbm.info.ia51.framework.environment.StopSimulation;
import fr.utbm.info.ia51.framework.gui.FrameworkGUI;
import fr.utbm.info.ia51.framework.util.SpawnMapping;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
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
public abstract class FrameworkLauncher extends Agent {
  private FrameworkGUI uiSingleton;
  
  private Environment environmentSingleton;
  
  private final AtomicBoolean canStop = new AtomicBoolean(false);
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    boolean _initializeSimulation = this.initializeSimulation(((List<Object>)Conversions.doWrapArray(occurrence.parameters)));
    if ((!_initializeSimulation)) {
      Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
      _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
    }
  }
  
  protected abstract boolean initializeSimulation(final List<Object> parameters);
  
  protected final boolean initializeSimulation(final Environment environment, final DynamicType behaviorType, final FrameworkGUI gui, final List<Object> parameters, final SpawnMapping spawnMapping) {
    UUID environmentInteractionSpace = UUID.randomUUID();
    this.uiSingleton = gui;
    this.environmentSingleton = environment;
    ArrayList<Object> params = CollectionLiterals.<Object>newArrayList();
    Iterables.<Object>addAll(params, Collections.<Object>unmodifiableList(CollectionLiterals.<Object>newArrayList(this.environmentSingleton, environmentInteractionSpace, spawnMapping, behaviorType)));
    Iterables.<Object>addAll(params, parameters);
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawn(SimulatorAgent.class, params.toArray());
    if ((this.uiSingleton != null)) {
      if ((this.environmentSingleton != null)) {
        this.environmentSingleton.addEnvironmentListener(this.uiSingleton);
      }
      final Procedure0 _function = () -> {
        this.stopSimulation();
      };
      this.uiSingleton.setTerminationHandler(_function);
      this.uiSingleton.setVisible(true);
    }
    this.canStop.set(true);
    return true;
  }
  
  protected void stopSimulation() {
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
    StopSimulation _stopSimulation = new StopSimulation();
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_stopSimulation);
    if ((this.uiSingleton != null)) {
      this.uiSingleton.setVisible(false);
      if ((this.environmentSingleton != null)) {
        this.environmentSingleton.removeEnvironmentListener(this.uiSingleton);
      }
      this.uiSingleton.dispose();
    }
    this.environmentSingleton = null;
    this.uiSingleton = null;
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
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
  public FrameworkLauncher(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public FrameworkLauncher(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public FrameworkLauncher(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
