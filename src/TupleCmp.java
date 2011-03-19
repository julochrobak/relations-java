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

final class TupleCmp implements Comparator<Tuple> {
    private final int[] idx;

    TupleCmp(int[] idx) {
        this.idx = idx;
    }

    public int compare(Tuple l, Tuple r) {
        int result = 0;
        for (int i = 0; i < idx.length; ++i) {
            int pos = idx[i];
            result = l.attrs[pos].compareTo(r.attrs[pos]);
            if (result != 0)
                break;
        }

        return result;
    }
}