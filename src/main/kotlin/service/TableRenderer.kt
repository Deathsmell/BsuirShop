package service


class TableRenderer {
    private val columns: MutableList<Column> = mutableListOf()
    private val rows: MutableList<Array<Any?>> = mutableListOf()

    fun addColumn(name: String, size: Int, format: String = "s") {
        val fixedSize = if (size < name.length) name.length else size
        columns.add(Column(name, fixedSize, format))
    }

    fun addContent(contents: Array<Any?>) {
        rows.add(contents)
    }

    fun render() {
        System.out.format(getVerticalLine())
        System.out.format(getHeader())
        System.out.format(getVerticalLine())
        rows.forEach {
            System.out.format(getFormat(), *it)
        }
        System.out.format(getVerticalLine())
    }

    private fun getFormat(): String {
        return "|" + columns.joinToString("|") { "%-${it.size}${it.format}" } + "|%n"
    }

    private fun getVerticalLine(): String {
        return "+" + columns.joinToString("+") { "-".repeat(it.size) } + "+%n"
    }

    private fun getHeader(): String {
        return "|" + columns.joinToString("|") { it.getHeader() } + "|%n"
    }


    private class Column(val name: String, val size: Int, val format: String) {
        fun getHeader(): String {
            return " ".repeat((size - name.length) / 2) + name + " ".repeat((size - name.length) / 2)
        }
    }
}