package edu.tpu.ruban

import kotlin.test.Test


class ApplicationTest {
    @Test
    fun testRoot() {
//        val table = Table("""application.user""")
//        val ref = table.refer()
//        val login = ref[table["login"]]
//        val hash = ref[table["hash"]]
//        val age = ref[table["age"]]
//        val select = SelectQuery()
//        select.addToSelection(login)
//        select.addToSelection(hash)
//        select.addCriteria(Criteria.notLess(age, Literal.of(18)))
//
//        println(select.toString(POSTGRE_SQL_SYNTAX))
//        val ctx = DSL.using(SQLDialect.POSTGRES)
//        val schema = schema("accounts")
//        val sa = schema.getTable("sa")
//        val utable = table("dsa.lk")
//        val ulogin = field("login", String::class.java)
//        val uhash = field("hash", String::class.java)
//        val uage = field("age", Int::class.java)
//
//        val q = ctx.select(ulogin, uhash).from(utable).where(uage.lessOrEqual(5))
//        println(q.sql)
//
//
//        val iq = ctx.insertInto(utable, ulogin, uage).values("dsa", 45).values("asd", 99)
//        println(iq.getSQL(ParamType.INLINED))
//        withTestApplication({ configureRouting() }) {
//            handleRequest(HttpMethod.Get, "/").apply {
//                assertEquals(HttpStatusCode.OK, response.status())
//                assertEquals("Hello World!", response.content)
//            }
//        }
    }
}