/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.infinispan.quickstart.clusteredcache.util;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;
//add
import org.infinispan.notifications.cachelistener.event.DataRehashedEvent;
import org.infinispan.notifications.cachelistener.event.TopologyChangedEvent;
import org.infinispan.notifications.cachelistener.event.TransactionCompletedEvent;
import org.infinispan.notifications.cachelistener.event.TransactionRegisteredEvent;
import org.infinispan.notifications.cachelistener.annotation.DataRehashed;
import org.infinispan.notifications.cachelistener.annotation.TopologyChanged;
import org.infinispan.notifications.cachelistener.annotation.TransactionCompleted;
import org.infinispan.notifications.cachelistener.annotation.TransactionRegistered;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryEvicted;
import org.infinispan.notifications.cachelistener.event.CacheEntryEvictedEvent;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryInvalidated;
import org.infinispan.notifications.cachelistener.event.CacheEntryInvalidatedEvent;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryLoaded;
import org.infinispan.notifications.cachelistener.event.CacheEntryLoadedEvent;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryActivated;
import org.infinispan.notifications.cachelistener.event.CacheEntryActivatedEvent;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryPassivated;
import org.infinispan.notifications.cachelistener.event.CacheEntryPassivatedEvent;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryVisited;
import org.infinispan.notifications.cachelistener.event.CacheEntryVisitedEvent;
import org.infinispan.notifications.cachemanagerlistener.annotation.ViewChanged;
import org.infinispan.notifications.cachemanagerlistener.event.ViewChangedEvent;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStarted;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStartedEvent;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStopped;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStoppedEvent;
import org.infinispan.notifications.cachelistener.event.Event.Type.*;
import org.infinispan.notifications.cachemanagerlistener.event.Event.Type.*;
import org.infinispan.notifications.cachemanagerlistener.event.MergeEvent;
import org.infinispan.notifications.cachemanagerlistener.annotation.Merged;
// add end
import org.infinispan.util.logging.Log;
import org.infinispan.util.logging.LogFactory;

/**
 * An Infinispan listener that simply logs cache entries being created and
 * removed
 * 
 * @author Pete Muir
 * 
 */
@Listener
public class LoggingListener {

   private Log log = LogFactory.getLog(LoggingListener.class);
   @CacheEntryCreated
   public void observeAdd(CacheEntryCreatedEvent<?, ?> event) {
         log.infof("### Cache entry with key %s added in cache %s", event.getKey(), event.getCache());
   }
   @CacheEntryRemoved
   public void observeRemove(CacheEntryRemovedEvent<?, ?> event) {
      log.infof("### Cache entry with key %s removed in cache %s", event.getKey(), event.getCache());
   }
   @CacheEntryModified
   public void observeModified(CacheEntryModifiedEvent<?, ?> event) {
      log.infof("### Cache entry with key %s modified in cache %s", event.getKey(), event.getCache());
   }
   @CacheEntryEvicted
   public void observeEvicted(CacheEntryEvictedEvent<?, ?> event) {
      log.infof("### Cache entry with key %s evicted in cache %s", event.getKey(), event.getCache());
   }
   @CacheEntryInvalidated
   public void observeEvicted(CacheEntryInvalidatedEvent<?, ?> event) {
      log.infof("### Cache entry with key %s invalidated in cache %s", event.getKey(), event.getCache());
   }
   @CacheEntryLoaded
   public void observeEvicted(CacheEntryLoadedEvent<?, ?> event) {
      log.infof("### Cache entry with key %s loaded in cache %s", event.getKey(), event.getCache());
   }
   @CacheEntryActivated
   public void observeActivated(CacheEntryActivatedEvent<?, ?> event) {
      log.infof("### Cache entry with key %s activated in cache %s", event.getKey(), event.getCache());
   }
   @CacheEntryPassivated
   public void observePassivated(CacheEntryPassivatedEvent<?, ?> event) {
      log.infof("### Cache entry with key %s passivated in cache %s", event.getKey(), event.getCache());
   }
   @CacheEntryVisited
   public void observePassivated(CacheEntryVisitedEvent<?, ?> event) {
      log.infof("### Cache entry with key %s visited in cache %s", event.getKey(), event.getCache());
   }
   @ViewChanged
   public void observeViewChanged(ViewChangedEvent event) {
      log.infof("###Cache entry View Changed ");
   }
   @DataRehashed
   public void observeDataRehashed(DataRehashedEvent event) {
      log.infof("###Cache entry Data Rehashed ");
   }
   @TopologyChanged
   public void observeTopologyChanged(TopologyChangedEvent event) {
      log.infof("###Cluster Topology Changed ");
   }
   @TransactionCompleted
   public void observeTransactionCompleted(TransactionCompletedEvent event) {
      log.infof("###Transaction Completed ");
   }
   @TransactionRegistered
   public void observeTransactionRegistered(TransactionRegisteredEvent event) {
      log.infof("###Transaction Registered ");
   }
   @CacheStarted
   public void observeCacheStarted(CacheStartedEvent event) {
      log.infof("###Cache started cache ");
   }
   @CacheStopped
   public void observeCacheStopped(CacheStoppedEvent event) {
      log.infof("### CacheStopped cache ");
   }
   @Merged
   public void observeCacheStarted(MergeEvent event) {
      log.infof("###Cache merged ");
   }
// add end
}
