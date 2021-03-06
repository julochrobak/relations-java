/*
Copyright 2011 Ostap Cherkashin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.bandilab;

import java.util.*;

public final class Tuple {
    final Head head;
    final Comparable[] vals;

    Tuple(Head head, Comparable[] vals) {
        this.head = head;
        this.vals = vals;
    }

    public int getInt(String attr) {
        return (Integer) vals[head.getPos(attr)];
    }

    public long getLong(String attr) {
        return (Long) vals[head.getPos(attr)];
    }

    public double getDouble(String attr) {
        return (Double) vals[head.getPos(attr)];
    }

    public String getString(String attr) {
        return (String) vals[head.getPos(attr)];
    }

    public String toString() {
        return Arrays.asList(vals).toString();
    }
}
