import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class UniqueEmailAddressesTest : FreeSpec({
    "solve" - {
        withData(
            nameFn = { (emails, unique) ->
                "${emails.toList()} has $unique unique"
            },
            row(
                arrayOf(
                    "test.email+alex@leetcode.com", "test.email.leet+alex@code.com"
                ),
                2
            ),
            row(
                arrayOf(
                    "test.email+alex@leetcode.com",
                    "test.e.mail+bob.cathy@leetcode.com",
                    "testemail+david@lee.tcode.com"
                ),
                2,
            ),
            row(
                arrayOf(
                    "a@leetcode.com", "b@leetcode.com", "c@leetcode.com"
                ),
                3
            ),
        ) { (emails, unique) ->
            val sut = UniqueEmailAddresses()

            val res = sut.numUniqueEmails(emails)

            res shouldBe unique
        }
    }
})