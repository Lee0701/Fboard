package ee.oyatl.ime.f.common

interface IMESwitcher {
    fun previous(): Boolean
    fun next(onlyFboard: Boolean = true, onlyCurrentIme: Boolean = false): Boolean
    fun symbols(): Boolean
    fun current(): String
    fun list(): List<String>
}