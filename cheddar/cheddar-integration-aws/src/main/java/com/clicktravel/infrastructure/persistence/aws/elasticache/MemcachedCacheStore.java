/*
 * Copyright 2014 Click Travel Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.clicktravel.infrastructure.persistence.aws.elasticache;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.spy.memcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clicktravel.cheddar.infrastructure.persistence.cache.CacheStore;

public class MemcachedCacheStore implements CacheStore {

    private final MemcachedClient memcachedClient;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MemcachedCacheStore(final MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    @Override
    public Serializable getObject(final String key, final int timeout) {
        Serializable myObj = null;
        final Future<Object> f = memcachedClient.asyncGet(key);
        try {
            myObj = (Serializable) f.get(timeout, TimeUnit.SECONDS);
        } catch (final TimeoutException e) {
            f.cancel(false);
            logger.trace("Unable to get cache object within given time", e);
            return null;
        } catch (final InterruptedException | ExecutionException e) {
            logger.debug("Unable to get cache object", e);
        }
        return myObj;
    }

    @Override
    public void setObject(final String key, final int expire, final Serializable object) {
        memcachedClient.set(key, expire, object);
    }

}
