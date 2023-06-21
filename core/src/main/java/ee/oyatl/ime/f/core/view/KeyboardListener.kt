package ee.oyatl.ime.f.core.view


interface KeyboardListener {
    fun onKeyClick(code: Int, output: String?)
    fun onKeyLongClick(code: Int, output: String?)
    fun onKeyDown(code: Int, output: String?)
    fun onKeyUp(code: Int, output: String?)
    fun onKeyFlick(direction: FlickDirection, code: Int, output: String?)
}