fun main() {
    println("first input:")
    testNavigation(
        browser = Browser(),
        commands = arrayOf(
            "get-current",
            "access,https://amazon.com",
            "access,https://cnn.com",
            "get-current",
            "back",
            "get-current",
            "back",
            "get-current",
            "back"
        )
    )
    println()
    println("second input:")
    testNavigation(
        browser = Browser(),
        commands = arrayOf(
            "access,https://amazon.com",
            "access,https://cnn.com",
            "get-current",
            "forward"
        )
    )
    println()
    println("third input:")
    testNavigation(
        browser = Browser(),
        commands = arrayOf(
            "access,https://amazon.com",
            "access,https://cnn.com",
            "access,https://gmail.com",
            "access,https://outlook.com",
            "get-current",
            "back",
            "back",
            "back",
            "get-current",
            "forward",
            "forward",
            "get-current"
        )
    )
    println()
    println("fourth input:")
    testNavigation(
        browser = Browser(),
        commands = arrayOf(
            "access,https://amazon.com",
            "access,https://cnn.com",
            "access,https://gmail.com",
            "access,https://outlook.com",
            "get-current",
            "back",
            "back",
            "back",
            "get-current",
            "forward",
            "forward",
            "get-current",
            "access,https://devsuperior.com.br",
            "back",
            "forward",
            "get-current",
            "forward"
        )
    )

}

fun testNavigation (browser: Browser, commands: Array<String>) {
    commands.forEach { command ->
        when {
            command == "get-current" -> {
                println(browser.getCurrentPage())
            }
            command == "back" -> {
                try {
                    browser.back()
                } catch (e: Error) {
                    println("Error: ${e.message}")
                }
            }
            command == "forward" -> {
                try {
                    browser.forward()
                } catch (e: Error) {
                    println("Error: ${e.message}")
                }
            }
            command.startsWith("access") -> {
                val newArr = command.split(",")
                browser.access(newArr[1])
            }
            else -> {
                println("Invalid Command: $command")
            }
        }
    }
}
