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
package fr.utbm.info.ia51.labworks.pacman.environment.agent;

import com.google.common.base.Objects;
import fr.utbm.info.ia51.labworks.pacman.environment.agent.RunBeginingOfStep;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.EventSpace;
import io.sarl.lang.core.Scope;
import io.sarl.lang.util.SerializableProxy;
import java.io.ObjectStreamException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Control the simulation.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public final class Controller {
  private final AtomicBoolean started = new AtomicBoolean();
  
  private final EventSpace space;
  
  private final Address address;
  
  Controller(final EventSpace space, final Address emitter) {
    this.space = space;
    this.address = emitter;
  }
  
  /**
   * Start the simulation.
   */
  public void startSimulation() {
    boolean _andSet = this.started.getAndSet(true);
    if ((!_andSet)) {
      RunBeginingOfStep event = new RunBeginingOfStep();
      class $SerializableClosureProxy implements Scope<Address> {
        
        private final UUID $_uUID;
        
        public $SerializableClosureProxy(final UUID $_uUID) {
          this.$_uUID = $_uUID;
        }
        
        @Override
        public boolean matches(final Address it) {
          UUID _uUID = it.getUUID();
          return Objects.equal(_uUID, $_uUID);
        }
      }
      final Scope<Address> _function = new Scope<Address>() {
        @Override
        public boolean matches(final Address it) {
          UUID _uUID = it.getUUID();
          UUID _uUID_1 = Controller.this.address.getUUID();
          return Objects.equal(_uUID, _uUID_1);
        }
        private Object writeReplace() throws ObjectStreamException {
          return new SerializableProxy($SerializableClosureProxy.class, Controller.this.address.getUUID());
        }
      };
      this.space.emit(this.address.getUUID(), event, _function);
    }
  }
  
  /**
   * Replies if the simulation was started.
   */
  @Pure
  public boolean isStarted() {
    return this.started.get();
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
}
