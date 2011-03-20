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

public final class Relation {
    private final Head head;
    private final Set<Tuple> body;

    /**
     * Here is how to construct a new Books relation with three attributes
     * (title, pages, and price):
     *
     * <code>
     * Relation books = new Relation(
     *         new Attribute("title", String.class),
     *         new Attribute("pages", Integer.class),
     *         new Attribute("price", Double.class));
     *
     * r.add("The Iliad", 576, 26.40);
     * r.add("The Odyssey", 416, 19.95);
     * r.add("Oliver Twist", 608, 12.99);
     * </code>
     */
    public Relation(Attribute ... attrs) {
        this.head = new Head(attrs);
        this.body = new TreeSet<Tuple>(new TupleCmp(this.head.getIndex()));
    }

    private Relation(Head head, Set<Tuple> body) {
        this.head = head;
        this.body = body;
    }

    public void add(Comparable ... vals) {
        // TODO: check the attribute types match the attribute values
        body.add(new Tuple(head, vals));
    }

    public int size() {
        return body.size();
    }

    public String toString() {
        return body.toString();
    }

    /**
     * Project a relation to the specified attributes. E.g. project the books
     * relation to titles (see below) produces a unique set of book titles.
     *
     * <code>
     * Relation allTitles = project(books, "titles");
     * </code>
     */
    public static Relation project(Relation r, String ... attrs) {
        Head h = r.head.project(attrs);
        TupleCmp cmp = new TupleCmp(h.getIndex());
        TreeSet<Tuple> body = new TreeSet<Tuple>(cmp);
        int[] index = r.head.getIndex(attrs);

        for (Tuple t : r.body) {
            Comparable[] vals = new Comparable[attrs.length];

            int i = 0;
            for (int pos : index)
                vals[i++] = t.vals[pos];

            body.add(new Tuple(h, vals));
        }

        return new Relation(h, body);
    }

    /**
     * <code>
     * Relation myBooks = join(books, selectedTitles);
     * </code>
     */
    public static Relation join(Relation left, Relation right) {
        return null;
    }

    /**
     * <code>
     * summary(books, cnt("count"));
     * </code>
     */
    public static Relation summary(Relation r, Aggregate ... aggrs) {
        return null;
    }

    /**
     * <code>
     * extend(books, new Extension("newPrice") {
     *     public Tuple extend(Tuple t) {
     *         t.extend(0.90 * t.getDouble("price"));
     *     }
     * }
     * </code>
     */
    public static Relation extend(Relation r, Extension ... ext) {
        return null;
    }

    /**
     * <code>
     * sort(books, "price");
     * </code>
     */
    public static Relation sort(Relation r, String ... attrs) {
        TupleCmp cmp = new TupleCmp(r.head.getSortIndex(attrs));
        TreeSet<Tuple> sorted = new TreeSet<Tuple>(cmp);
        sorted.addAll(r.body);

        return new Relation(r.head, sorted);
    }
}
