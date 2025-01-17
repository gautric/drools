/*
 * Copyright 2005 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.core.util;

import java.util.List;

import org.drools.core.common.ActivationGroupNode;
import org.drools.core.common.ActivationNode;
import org.drools.core.common.InternalAgendaGroup;
import org.drools.core.common.InternalFactHandle;
import org.drools.core.common.InternalRuleFlowGroup;
import org.drools.core.definitions.rule.impl.RuleImpl;
import org.drools.core.reteoo.LeftTupleImpl;
import org.drools.core.rule.GroupElement;
import org.drools.core.spi.Activation;
import org.drools.core.spi.Consequence;
import org.drools.core.spi.PropagationContext;
import org.kie.api.runtime.rule.FactHandle;

public class BaseQueueable implements Activation {

    private BinaryHeapQueue queue;
    private int   index;
    
    public BaseQueueable(BinaryHeapQueue queue) {
        this.queue = queue;
    }

    public void setQueueIndex(final int index) {
        this.index = index;
    }

    public int getQueueIndex() {
        return this.index;
    }

    public void dequeue() {
        this.queue.dequeue( this.index );
    }

    public ActivationGroupNode getActivationGroupNode() {
        return null;
    }

    public long getActivationNumber() {
        return 0;
    }

    public InternalAgendaGroup getAgendaGroup() {
        return null;
    }

    public PropagationContext getPropagationContext() {
        return null;
    }

    public RuleImpl getRule() {
        return null;
    }

    public Consequence getConsequence() {
        // TODO Auto-generated method stub
        return null;
    }

    public ActivationNode getActivationNode() {
        return null;
    }

    public int getSalience() {
        return 0;
    }

    public GroupElement getSubRule() {
        return null;
    }

    public LeftTupleImpl getTuple() {
        return null;
    }

    public boolean isQueued() {
        return false;
    }

    public void remove() {
    }

    public void setQueued(boolean activated) {
    }

    public void setActivationGroupNode(ActivationGroupNode activationGroupNode) {
    }

    public void setActivationNode(ActivationNode ruleFlowGroupNode) {
    }

    public List<FactHandle> getFactHandles() {
        return null;
    }

    public List<Object> getObjects() {
        return null;
    }

    public Object getDeclarationValue(String variableName) {
        return null;
    }

    public List<String> getDeclarationIds() {
        return null;
    }

    public InternalFactHandle getActivationFactHandle() {
        return null;
    }

    public boolean isAdded() {
        return false;
    }

    public boolean isMatched() {
        return false;
    }

    public void setMatched(boolean matched) { }
  
    public boolean isActive() {
        return false;
    }

    public void setActive(boolean active) { }

    public boolean isRuleAgendaItem() {
        return false;
    }

    public InternalRuleFlowGroup getRuleFlowGroup() {
        return null;
    }
}
