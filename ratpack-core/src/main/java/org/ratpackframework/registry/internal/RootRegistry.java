/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ratpackframework.registry.internal;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class RootRegistry<T> extends RegistrySupport<T> {

  private final ImmutableList<? extends T> objects;

  public RootRegistry(ImmutableList<? extends T> objects) {
    this.objects = objects;
  }

  @Override
  public String toString() {
    return "RootRegistry{" + objects + '}';
  }

  protected <O extends T> O doMaybeGet(Class<O> type) {
    for (Object object : objects) {
      if (type.isInstance(object)) {
        return type.cast(object);
      }
    }

    return null;
  }

  public <O extends T> List<O> getAll(Class<O> type) {
    ImmutableList.Builder<O> builder = ImmutableList.builder();
    for (T object : objects) {
      if (type.isInstance(object)) {
        @SuppressWarnings("unchecked") O castObject = (O) object;
        builder.add(castObject);
      }
    }
    return builder.build();
  }
}
