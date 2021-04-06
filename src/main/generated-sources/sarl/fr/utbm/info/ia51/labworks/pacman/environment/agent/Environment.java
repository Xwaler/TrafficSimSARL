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

import com.google.common.base.Objects;
import fr.utbm.info.ia51.framework.math.Point2i;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.Action;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.Controller;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.DefaultMazeFrontEndSkill;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.DefaultMazeManagerSkill;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.EnvironmentEvent;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.EnvironmentListener;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.MazeChangeQuery;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.MazeFrontEnd;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.MazeManager;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.PacManIsDead;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.Player;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.RunBeginingOfStep;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.RunEndOfStep;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.AgentBody;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.Direction;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.GhostBody;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.PacmanBody;
import fr.utbm.info.ia51.labworks.pacman.environment.maze.PacmanObject;
import fr.utbm.info.ia51.labworks.pacman.players.Ghost;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.EventSpace;
import io.sarl.lang.core.Scope;
import io.sarl.lang.util.SerializableProxy;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Definition of the agent that is managing the environment.
 * 
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SarlSpecification("0.11")
@SarlElementType(19)
@SuppressWarnings("all")
public class Environment extends Agent {
  private ArrayList<EnvironmentListener> listeners = CollectionLiterals.<EnvironmentListener>newArrayList();
  
  private Player player;
  
  private Controller controller;
  
  private int time = 0;
  
  private final LinkedList<MazeChangeQuery> actions = CollectionLiterals.<MazeChangeQuery>newLinkedList();
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Object _get = occurrence.parameters[0];
    Integer width = ((Integer) _get);
    Object _get_1 = occurrence.parameters[1];
    Integer height = ((Integer) _get_1);
    Object _get_2 = occurrence.parameters[2];
    Integer nbGhosts = ((Integer) _get_2);
    Object _get_3 = occurrence.parameters[3];
    Integer perceptionDistance = ((Integer) _get_3);
    int _size = ((List<Object>)Conversions.doWrapArray(occurrence.parameters)).size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(4, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        Object o = occurrence.parameters[((i) == null ? 0 : (i).intValue())];
        if ((o instanceof EnvironmentListener)) {
          this.listeners.add(((EnvironmentListener)o));
        }
      }
    }
    DefaultMazeManagerSkill _defaultMazeManagerSkill = new DefaultMazeManagerSkill(((width) == null ? 0 : (width).intValue()), ((height) == null ? 0 : (height).intValue()));
    this.<DefaultMazeManagerSkill>setSkill(_defaultMazeManagerSkill);
    DefaultMazeFrontEndSkill _defaultMazeFrontEndSkill = new DefaultMazeFrontEndSkill();
    this.<DefaultMazeFrontEndSkill>setSkill(_defaultMazeFrontEndSkill);
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
    EventSpace _defaultSpace = _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.getDefaultSpace();
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
    Address _defaultAddress = _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.getDefaultAddress();
    Controller _controller = new Controller(_defaultSpace, _defaultAddress);
    this.controller = _controller;
    MazeManager _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER();
    PacmanBody pacmanBody = _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER.createPacman();
    UUID _agentId = pacmanBody.getAgentId();
    Logger _logger = Logger.getLogger(this.getID().toString());
    Player _player = new Player(_agentId, _logger);
    this.player = _player;
    ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, ((nbGhosts) == null ? 0 : (nbGhosts).intValue()), true);
    for (final Integer i_1 : _doubleDotLessThan_1) {
      {
        MazeManager _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER_1 = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER();
        GhostBody ghostBody = _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER_1.createGhost(((perceptionDistance) == null ? 0 : (perceptionDistance).intValue()));
        Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
        _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawnInContextWithID(Ghost.class, ghostBody.getAgentId(), _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2.getDefaultContext());
      }
    }
    this.firePlayerBinding();
    this.fireControllerBinding();
    this.fireEnvironmentChange();
  }
  
  protected void fireEnvironmentChange() {
    TreeMap<Point2i, PacmanObject> objects = new TreeMap<Point2i, PacmanObject>();
    MazeManager _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER();
    List<PacmanObject> _pacmanObjects = _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER.getPacmanObjects();
    for (final PacmanObject obj : _pacmanObjects) {
      objects.put(obj.getPosition(), obj);
    }
    UUID _iD = this.getID();
    MazeManager _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER_1 = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER();
    int _mazeWidth = _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER_1.getMazeWidth();
    MazeManager _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER_2 = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER();
    int _mazeHeight = _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER_2.getMazeHeight();
    Map<Point2i, PacmanObject> _unmodifiableMap = Collections.<Point2i, PacmanObject>unmodifiableMap(objects);
    EnvironmentEvent event = new EnvironmentEvent(_iD, this.time, _mazeWidth, _mazeHeight, _unmodifiableMap);
    for (final EnvironmentListener listener : this.listeners) {
      listener.environmentChanged(event);
    }
  }
  
  protected void firePlayerBinding() {
    for (final EnvironmentListener listener : this.listeners) {
      listener.bindPlayer(this.player);
    }
  }
  
  protected void firePlayerUnbinding() {
    for (final EnvironmentListener listener : this.listeners) {
      listener.unbindPlayer(this.player);
    }
  }
  
  protected void fireControllerBinding() {
    for (final EnvironmentListener listener : this.listeners) {
      listener.bindController(this.controller);
    }
  }
  
  protected void fireControllerUnbinding() {
    for (final EnvironmentListener listener : this.listeners) {
      listener.unbindController(this.controller);
    }
  }
  
  protected void fireGameOver() {
    for (final EnvironmentListener listener : this.listeners) {
      listener.gameOver();
    }
  }
  
  private void $behaviorUnit$Destroy$1(final Destroy occurrence) {
    this.firePlayerUnbinding();
    this.fireControllerUnbinding();
    this.<MazeManager>clearSkill(MazeManager.class);
    this.<MazeFrontEnd>clearSkill(MazeFrontEnd.class);
  }
  
  private void $behaviorUnit$Action$2(final Action occurrence) {
    synchronized (this) {
      UUID _uUID = occurrence.getSource().getUUID();
      MazeChangeQuery _mazeChangeQuery = new MazeChangeQuery(_uUID, occurrence.direction);
      this.actions.add(_mazeChangeQuery);
      int _size = this.actions.size();
      MazeManager _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER();
      int _bodyCount = _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER.getBodyCount();
      if ((_size >= (_bodyCount - 1))) {
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
        RunEndOfStep _runEndOfStep = new RunEndOfStep();
        class $SerializableClosureProxy implements Scope<Address> {
          
          private final Address $_defaultAddress_1;
          
          public $SerializableClosureProxy(final Address $_defaultAddress_1) {
            this.$_defaultAddress_1 = $_defaultAddress_1;
          }
          
          @Override
          public boolean matches(final Address it) {
            return Objects.equal(it, $_defaultAddress_1);
          }
        }
        final Scope<Address> _function = new Scope<Address>() {
          @Override
          public boolean matches(final Address it) {
            DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = Environment.this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
            Address _defaultAddress = _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.getDefaultAddress();
            return Objects.equal(it, _defaultAddress);
          }
          private Object writeReplace() throws ObjectStreamException {
            return new SerializableProxy($SerializableClosureProxy.class, Environment.this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER().getDefaultAddress());
          }
        };
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_runEndOfStep, _function);
      }
    }
  }
  
  @SyntheticMember
  @Pure
  private boolean $behaviorUnitGuard$Action$2(final Action it, final Action occurrence) {
    return (occurrence.time >= it.time);
  }
  
  private void $behaviorUnit$RunEndOfStep$3(final RunEndOfStep occurrence) {
    synchronized (this) {
      Direction avatarDirection = this.player.getDirection();
      UUID _bodyId = this.player.getBodyId();
      MazeChangeQuery avatarAction = new MazeChangeQuery(_bodyId, avatarDirection);
      this.actions.add(avatarAction);
      MazeManager _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER();
      _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER.getSuperPowerAccessor(this.player.getBodyId()).decreaseSuperPower();
      MazeManager _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER_1 = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER();
      boolean _applyActions = _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER_1.applyActions(this.actions);
      if (_applyActions) {
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
        PacManIsDead _pacManIsDead = new PacManIsDead();
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_pacManIsDead);
        this.fireGameOver();
        Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
        _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
      }
      this.time++;
      this.fireEnvironmentChange();
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
      RunBeginingOfStep _runBeginingOfStep = new RunBeginingOfStep();
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.emit(_runBeginingOfStep);
    }
  }
  
  private void $behaviorUnit$RunBeginingOfStep$4(final RunBeginingOfStep occurrence) {
    synchronized (this) {
      this.actions.clear();
      MazeManager _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER();
      Set<Map.Entry<AgentBody, List<PacmanObject>>> _entrySet = _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEMANAGER$CALLER.getPerceptions().entrySet();
      for (final Map.Entry<AgentBody, List<PacmanObject>> e : _entrySet) {
        MazeFrontEnd _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEFRONTEND$CALLER = this.$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEFRONTEND$CALLER();
        _$CAPACITY_USE$FR_UTBM_INFO_IA51_LABWORKS_PACMAN_ENVIRONMENT_AGENT_MAZEFRONTEND$CALLER.sendPerception(this.time, e.getKey().getAgentId(), e.getValue(), e.getKey().getPosition());
      }
    }
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
