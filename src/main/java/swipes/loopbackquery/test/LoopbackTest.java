package swipes.loopbackquery.test;

import swipes.loopbackquery.Filter;
import swipes.loopbackquery.Include;
import swipes.loopbackquery.LoopbackQuery;
import swipes.loopbackquery.Query;
import swipes.loopbackquery.Scope;

/**
 * Created by Helson Taveras on 9/15/15.
 */
public class LoopbackTest {

    public static void main(String[] args) {
        new LoopbackTest();
    }

    public LoopbackTest() {
        setUp();
        testPreConditions();

        // Filter
        testFilter_limitOnly();
        testFilter_offsetAndLimit();
        testFilter_orderLimitAndOffset();

        // Scope
        testInclude_orderAndRelation();
        testInclude_orderRelationAndSimpleScope();

        // Complex
        testComplex();
    }

    public static void setUp() {
        LoopbackQuery.get().build();
    }

    public void testPreConditions() {
        LoopbackQuery.get();
        assertNotEquals(LoopbackQuery.get(), null);
    }

    /**
     * Although only the limit is specified, the 'offset' value appears with a value of 0. Not a
     * problem; however, it's not optimal.
     */
    public void testFilter_limitOnly() {
        print("testFilter_limitOnly");
        int limit = 10;
        Query query = new Query.Builder().filter(new Filter.FilterBuilder().limit(limit)).build();
        assertEquals(query.filter.limit, limit);
        print(query);
    }

    public void testFilter_offsetAndLimit() {
        print("testFilter_offsetAndLimit");
        int offset = 10;
        int limit = 20;
        Query query = new Query.Builder()
                .filter(new Filter.FilterBuilder()
                        .offset(offset)
                        .limit(limit))
                .build();
        assertEquals(query.filter.offset, offset);
        assertEquals(query.filter.limit, limit);
        print(query);
    }

    public void testFilter_orderLimitAndOffset() {
        print("testFilter_orderLimitAndOffset");
        String order = "dateCreated DESC";
        int offset = 200;
        int limit = 5;
        Query query = new Query.Builder()
                .filter(new Filter.FilterBuilder()
                        .offset(offset)
                        .limit(limit)
                        .order(order))
                .build();

        assertEquals(query.filter.offset, offset);
        assertEquals(query.filter.limit, limit);
        assertEquals(query.filter.order, order);
        print(query);
    }

    public void testInclude_orderAndRelation() {
        print("testInclude_orderAndRelation");

        String order = "lastName";
        String relation = "children";
        Query query = new Query.Builder().include(
                new Include.Builder()
                        .order(order)
                        .relation(relation)).build();
        assertEquals(query.include.order, order);
        assertEquals(query.include.relation, relation);
        print(query);
    }

    public void testInclude_orderRelationAndSimpleScope() {
        print("testInclude_orderRelationAndScope");

        String order = "dateModified ASC";
        String[] fields = {"transactions", "houses", "devices"};
        Query query = new Query.Builder().include(
                new Include.Builder()
                        .order(order).scope(new Scope.Builder().fields(fields))).build();
        assertEquals(query.include.order, order);
        assertEquals(query.include.scope.fields, fields);
        print(query);
    }

    public void testComplex() {
      /*
        {
            limit: limit,
                    offset: offset,
                order: 'dateCreated DESC',
                include: [
            {
                relation: 'user',
                        scope: {
                fields: ['id', 'fullName', 'picture']
            }
            },
            {
                relation: 'likes',
                        order: 'dateCreated ASC',
                    limit: 200,
                    scope: {
                include: {
                    relation: 'user',
                            scope: {
                        fields: ['id', 'fullName', 'picture']
                    }
                }
            }
            },
            {
                relation: 'comments',
                        limit: 1,
                    scope: {
                order: 'dateCreated ASC',
                        fields: ['id', 'content', 'dateCreated', 'userId'],
                include: {
                    relation: 'user',
                            scope: {
                        fields: ['id', 'fullName']
                    }
                }
            }
            }
            ]
        }*/
        print("testComplex");

        int limit = 10;
        int offset = 10;
        String order = "dateCreated DESC";

        String includeFirstRelation = "user";
        String[] includeFirstFields = {"id", "fullName", "picture"};

        String includeSecondRelation = "likes";
        String includeSecondOrder = "dateCreated ASC";
        int includeSecondLimit = 200;
        String includeSecondScopeRelation = "user";
        String[] includeSecondScopeFields = { "id", "fullName", "picture" };

        String includeThirdRelation = "comments";
        int includeThirdLimit = 1;
        String includeThirdOrder = "dateCreated ASC";
        String[] includeThirdFields = {"id", "content", "userId" };
        String includeThirdScopeRelation = "user";
        String[] includeThirdScopeRelationFields = {"id", "fullName"};

        // Not complete
        Query query = new Query.Builder()
                .filter(new Filter.FilterBuilder().order(order).limit(limit).offset(offset)
                    .include(new Include.Builder().relation(includeFirstRelation).scope(new Scope.Builder().fields(includeFirstFields)))
                    .include(new Include.Builder().relation(includeSecondRelation)
                            .order(includeSecondOrder)
                            .limit(includeSecondLimit)
                            .scope(
                                    new Scope.Builder().include(
                                            new Include.Builder().relation(
                                                    includeSecondScopeRelation)).fields(includeSecondScopeFields)))

                    .include(new Include.Builder()
                            .relation(includeThirdRelation).limit(includeThirdLimit).scope(
                                    new Scope.Builder().order(includeThirdOrder).fields(includeThirdFields)
                                            .include(new Include.Builder().scope(
                                                    new Scope.Builder().relation(includeThirdScopeRelation).fields(includeThirdScopeRelationFields))))))
                .build();


        print(query);
    }

    public void assertEquals(Object a, Object b) {
        if (!a.equals(b)) {
            throw new AssertionError("Assertion failed.");
        }
    }

    public void assertNotEquals(Object a, Object b) {
        if (a.equals(b)) {
            throw new AssertionError("Assertion failed.");
        }
    }

    public void print(Object o) {
        System.out.println(o.toString());
    }
}
