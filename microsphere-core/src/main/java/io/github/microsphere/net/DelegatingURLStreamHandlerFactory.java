/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.microsphere.net;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

/**
 * Delegating {@link URLStreamHandlerFactory}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class DelegatingURLStreamHandlerFactory implements URLStreamHandlerFactory {

    private final URLStreamHandlerFactory delegate;

    public DelegatingURLStreamHandlerFactory(URLStreamHandlerFactory delegate) {
        if (delegate == null) {
            throw new NullPointerException("The 'delegate' argument must not be null!");
        }
        this.delegate = delegate;
    }

    @Override
    public final URLStreamHandler createURLStreamHandler(String protocol) {
        URLStreamHandler handler = delegate.createURLStreamHandler(protocol);
        if (handler == null) {
            handler = findURLStreamHandler(protocol);
        }
        return handler;
    }

    /**
     * Get the delegate of {@link URLStreamHandlerFactory}
     *
     * @return non-null
     */
    protected final URLStreamHandlerFactory getDelegate() {
        return delegate;
    }

    /**
     * The sub-class overrides this method to find the {@link URLStreamHandler} by the specified protocol if
     * {@link #getDelegate() delegate} can't create any {@link URLStreamHandler}
     *
     * @param protocol the specified protocol
     * @return <code>null</code> if not found
     */
    protected URLStreamHandler findURLStreamHandler(String protocol) {
        return null;
    }
}
