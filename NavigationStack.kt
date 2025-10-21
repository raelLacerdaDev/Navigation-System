data class Page(
    val url: String,
    var nextPage: Page? = null,
)
class NavigationStack() {
    var size = 0
        private set
    var head: Page? = null
        private set
    fun access (url: String) {
        val newPage =  Page(url, null)
        newPage.nextPage = head
        head = newPage
        size += 1
    }
    fun back() : Page? {
        if (size == 0) return null
        val pageToReturn = head
        head = head?.nextPage
        pageToReturn?.nextPage = null
        size -= 1
        return pageToReturn
    }

    fun clear() {
        head = null
        size = 0
    }
}
