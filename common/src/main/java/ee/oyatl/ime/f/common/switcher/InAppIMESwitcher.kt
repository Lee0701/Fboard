package ee.oyatl.ime.f.common.switcher

import ee.oyatl.ime.f.common.table.MoreKeysTable
import ee.oyatl.ime.f.common.view.InputViewManager
import ee.oyatl.ime.f.core.table.CharOverrideTable
import ee.oyatl.ime.f.core.table.CodeConvertTable

class InAppIMESwitcher: IMESwitcher {

    val list: MutableMap<String, State> = mutableMapOf()
    val transition: MutableList<String> = mutableListOf()
    val currentState: State get() = list[transition[currentStateIndex]]!!

    private var currentStateIndex: Int = 0

    override fun previous(): Boolean {
        currentStateIndex -= 1
        if(currentStateIndex < 0) currentStateIndex = transition.size - 1
        return true
    }

    override fun next(onlyFboard: Boolean, onlyCurrentIme: Boolean): Boolean {
        currentStateIndex += 1
        if(currentStateIndex >= transition.size) currentStateIndex = 0
        return true
    }

    override fun current(): String {
        return transition[currentStateIndex]
    }

    override fun list(): List<String> {
        return transition
    }

    interface State {
        val inputViewManager: InputViewManager
        val moreKeysTable: MoreKeysTable
        val convertTable: CodeConvertTable
        val overrideTable: CharOverrideTable

        data class Default(
            override val inputViewManager: InputViewManager,
            override val moreKeysTable: MoreKeysTable,
            override val convertTable: CodeConvertTable,
            override val overrideTable: CharOverrideTable,
        ): State
    }

}