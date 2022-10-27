import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class LicenseKeyFormattingTest : FreeSpec({
    "formats license key" - {
        withData(
            nameFn = { (licenceKey, k, formatted) ->
                "\"$licenceKey\" by $k groups as \"$formatted\""
            },
            row("5F3Z-2e-9-w", 4, "5F3Z-2E9W"),
            row("2-5g-3-J", 2, "2-5G-3J"),
            row("---", 3, "")
        ) { (licenceKey, k, formatted) ->
            val sut = LicenseKeyFormatting()

            val res = sut.licenseKeyFormatting(licenceKey, k)

            res shouldBe formatted
        }
    }
})