import com.github.ajalt.mordant.animation.animation
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.widgets.progressLayout
import java.lang.management.ManagementFactory
import java.util.concurrent.TimeUnit

fun main() {
    println("wait for debug")
    Thread.sleep(TimeUnit.SECONDS.toMillis(5))
    val terminal = Terminal()
    println("Console = ${System.console()}")
    println("crClearsLine = ${terminal.info.crClearsLine}")
    println("inputArguments = ${ManagementFactory.getRuntimeMXBean().inputArguments}")
    println("----------------env---------------------")
    println(System.getenv().entries.joinToString("\n") { (key, value) -> "$key = $value"})
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