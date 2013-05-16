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
package org.infinispan.quickstart.clusteredcache.distribution;

import org.infinispan.Cache;
import org.infinispan.quickstart.clusteredcache.util.LoggingListener;
// add
import org.infinispan.manager.EmbeddedCacheManager;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.concurrent.Future;
import org.infinispan.util.concurrent.FutureListener;
// add end
// tx add
import javax.transaction.TransactionManager;
import java.io.*;
// tx add end
public class Node2 extends AbstractNode {

   public static void main(String[] args) throws Exception {
      new Node2().run();
   }

   public void run() throws java.io.IOException{
   	EmbeddedCacheManager cm = getCacheManager();
      Cache<String, String> cache = cm.getCache("Demo");

      waitForClusterToForm();

   	// tx add start
    TransactionManager tm = cache.getAdvancedCache().getTransactionManager();
   	// tx add end

   	// tx add start 
   	try{
   	  tm.begin();
   	}catch(javax.transaction.NotSupportedException e){
   		e.printStackTrace();
   	}catch(javax.transaction.SystemException e){
   		e.printStackTrace();
   	}
   	// tx add end
   	
      // Add a listener so that we can see the puts to this node
      cache.addListener(new LoggingListener());
      
   	BufferedReader r =
            new BufferedReader(new InputStreamReader(System.in), 1);
   	boolean flag=true;
   	String command = "";
   	String key = "";
   	String value = "";
   	String old_value = "";
   	while(flag){
   	  System.out.print("command? "); 
      System.out.flush();
      command = r.readLine();
      System.out.println("command:" + command);
      if(command.compareTo("commit")==0){
        flag=false;
        break;
      }else if(command.compareTo("update")==0){
        System.out.print("key? "); 
        System.out.flush();
        key = r.readLine();
        System.out.println("enter key:" + key);
        System.out.print("value? ");
        System.out.flush();
        value = r.readLine();
        System.out.println("entered value:" + value);
        old_value = cache.get(key);
        System.out.println("old value:" + old_value);
        cache.put(key,value);
        continue;
      }else if(command.compareTo("lock")==0){
        System.out.print("key? "); 
        System.out.flush();
        key = r.readLine();
        System.out.println("enter key:" + key);
        System.out.print("value? ");
        System.out.flush();
        value = r.readLine();
        System.out.println("entered value:" + value);
        cache.getAdvancedCache().lock(key);
        continue;
      }
   }

   	// tx add start
   	try{
      tm.commit();
   	}catch(javax.transaction.RollbackException e){
   		e.printStackTrace();
   	}catch(javax.transaction.HeuristicMixedException e){
   		e.printStackTrace();
   	}catch(javax.transaction.HeuristicRollbackException e){
   		e.printStackTrace();
   	}catch(javax.transaction.SystemException e){
   		e.printStackTrace();
   	}
   	/*
   	}catch(javax.transaction.HeuristicMixedException e){
   		e.printStackTrace();
   	}catch(javax.transaction.RollbackException e){
   		e.printStackTrace();
   	}
   	*/
   	// tx add end

   	
   	if(cache.containsKey("key30")){
   		System.out.println("key30 is exist");
   	}else{
   		System.out.println("key30 is not exist");
   	}
   	if(cache.containsKey("key30")){
   		System.out.println("key30 is exist");
   	}else{
   		System.out.println("key30 is not exist");
   	}
   	cache.stop();
   	cache.start();
   	
   }
   
   @Override
   protected int getNodeId() {
      return 2;
   }

}
