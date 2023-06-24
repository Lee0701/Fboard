package ee.oyatl.ime.f.common

import ee.oyatl.ime.f.core.table.CharOverrideTable
import ee.oyatl.ime.f.core.table.CodeConvertTable

interface TableIME {
    val convertTable: CodeConvertTable
    val overrideTable: CharOverrideTable
}