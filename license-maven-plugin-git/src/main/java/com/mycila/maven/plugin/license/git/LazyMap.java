/*
 * Copyright (C) 2008-2024 Mycila (mathieu.carbou@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycila.maven.plugin.license.git;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

class LazyMap<K, V> extends HashMap<K, V> {
    final private Map<K, Supplier<V>> load;

    LazyMap(Map<K, Supplier<V>> load) {
        this.load = load;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(Object key) {
        return super.compute((K) key, (k, v) -> v != null ? v : load.get(key).get());
    }

    @Override
    public boolean containsKey(Object key) {
        return load.containsKey(key);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

}