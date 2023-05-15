package pl.glitchguru.issuetracker.authentication

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class ExampleTest : ShouldSpec({
    should("return the length of the string") {
        "sammy".length shouldBe 5
        "".length shouldBe 0
    }
})
