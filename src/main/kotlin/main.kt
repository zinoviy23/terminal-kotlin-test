import com.github.ajalt.mordant.animation.animation
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.progressLayout
import java.lang.management.ManagementFactory
import java.util.concurrent.TimeUnit
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

fun main() {
    println("wait for debug")
    Thread.sleep(TimeUnit.SECONDS.toMillis(5))
    val terminal = createTerminal()
    val myTable = table {
        header {
            row {
                cell("Some Information about terminal") {
                    columnSpan = 2
                }
            }
        }
        body {
            row {
                cell("Console")
                cell("${System.console()}")
            }
            row {
                cell("crClearsLine")
                cell("${terminal.info.crClearsLine}")
            }
            row {
                cell("interactive")
                cell("${terminal.info.interactive}")
            }
        }
    }
    terminal.println(myTable)
    println("inputArguments = ${ManagementFactory.getRuntimeMXBean().inputArguments}")
    println("----------------env---------------------")
    val env = System.getenv().entries
        .sortedBy { it.key }
        .joinToString("\n") { (key, value) -> "$key = $value" }
    println(env)
    Path("out")
        .createDirectories()
        .resolve("file-${System.currentTimeMillis()}.txt")
        .writeText(env)
    println("----------------------------------------")
    println("------------------props-----------------")
    println(System.getProperties().entries.sortedBy { it.key.toString() }
        .joinToString("\n") { (key, value) -> "$key = $value" })
    println("----------------------------------------")
    println("Some strange values:")
    println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
    println("┌┬┐├┼┤└┴┘─│╷╵╴╶")
    println("----------------------------------------")
    println("----------------------------------------")
    println("----------------------------------------")
    println("----------------------------------------")
    println("----------------------------------------")
    println("----------------------------------------")
    Thread.sleep(1000)
    val progress = terminal.animation<Int> {
        progressLayout {
            percentage()
            progressBar()
            completed(includeTotal = true)
            text("Running...\n")
        }.build(it.toLong(), 3)
    }
    progress.update(0)

    terminal.info("HELLO!\n")
    terminal.println("1\n")
    terminal.println("2\n")
    terminal.println("3\n")
    Thread.sleep(1000)
    progress.update(1)
    terminal.info("HAAHAHAHAHA\n")
    terminal.println("1\n")
    terminal.println("2\n")
    terminal.println("3\n")
    Thread.sleep(1000)
    progress.update(2)
    terminal.info("kek!!!")
    terminal.println("1\n")
    terminal.println("2\n")
    terminal.println("3\n")
    Thread.sleep(1000)
    progress.update(3)
    Thread.sleep(1000)
    terminal.info("ALLL\n")
    terminal.println("1\n")
    terminal.println("2\n")
    terminal.println("3\n")
    Thread.sleep(1000)

    progress.clear()
}

private val isCurrentWindowsEnvInteractive: Boolean
    get() =
        System.getenv("WT_SESSION") != null ||
                System.getenv("MY_IDEA_TERMINAL") == "true"

private val isWindows: Boolean
    get() = "windows" in System.getProperty("os.name")?.lowercase().orEmpty()

private fun createTerminal(): Terminal {
    val tmpTerminal = Terminal()
    if (!tmpTerminal.info.interactive || !isWindows || isCurrentWindowsEnvInteractive) {
        return tmpTerminal
    }
    return Terminal(interactive = false)
}