class Browser {
     private var currentPage = "home"
     private val backStack = NavigationStack()
     private val forwardStack = NavigationStack()

    fun getCurrentPage() = currentPage
    fun access(url: String) {
        backStack.access(currentPage)
        currentPage = url
        forwardStack.clear()
    }
    fun back() {
        if(backStack.head == null) {
            throw Error("Back Error")
        }
        forwardStack.access(currentPage)
        val current = backStack.back()
        currentPage = current?.url ?: "home"
    }

    fun forward() {
        if(forwardStack.head == null) {
            throw Error("Forward Error")
        }
        backStack.access(currentPage)
        val current = forwardStack.back()
        currentPage = current?.url ?: "home"
    }
}
