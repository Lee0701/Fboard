package ee.oyatl.ime.f.common.switcher

interface IMESwitcher {
    fun previous(): Boolean
    fun next(onlyFboard: Boolean = true, onlyCurrentIme: Boolean = false): Boolean
    fun current(): String
    fun list(): List<String>
}