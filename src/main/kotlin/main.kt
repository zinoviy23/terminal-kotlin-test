import com.github.ajalt.mordant.animation.animation
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
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
                    style = TextStyle(bold = true)
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
    println("Chineze:")
    println(CHINESE)
    println("Emoji:")
    println(EMOJI)
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
    terminal.println(TextColors.gray("I am gray!"))
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

private const val CHINESE = """
珍招権頭竹圧戦誇末北也聞域審熱。長薄屋脱曲資和向外発質地法暮管条医場。生併州載平覚過手受聞応著田本。在政意力火懇応浸転視橋自点半金窃銀県敗月。意陸講索儀黒対別訃芸林今枠柔名可。元談近業近的権大組更梨雪。政身部力芸逆育申節康木双真勢榊。導神関出全情能競物並品気要實断終終復孝権。京聞出球静唯削本暮芸統検。
拠定文矯問細突合責最連援測陽。基犬射今版点花治面陣存定農豪車述。題動無禁立低芸逮物結挑記塁灯優将織件売。改話円日転末首独湖側働開理。思氷淡悩強反記置易回除出好求説監民容念。走図就田白研連却賞封媛写部東子周氏合。心害済分平前選杉界芸同面透虐。覧扱書姿格集校大連登疑記。動属行携出藤伸責化岐線駅員寺以代先断。
"""

private const val EMOJI = """
🏥🔫🕠👘🔟 🐠🕑👫🎷🕖🍁 📏📎🔗📗 🐉🔜🐪🐳🏢🔺📊 🐽🎐🔯🕥 🍃🌿🔑👅🍐 🔈🕔🍶📘 💁🎒🍥🔴📮🌔 👭📉🕚💎🍻🐫🌉📖 💶🐍🍳🍜 🏀📎🐤📁💄👚 📳📠💰👊 🐑🕁🔲💢🍍🎱. 📡👟🔉🕕🔩🌵 🕖🔠🐞🌞💜🐹🔇 🔁🔥🔣👾🔌🕁 🐓💔🎢💡🎱 🍉🔻🌹📗👃🌕 🕞🕜🎩👀📷💙🍦 🌷💓🍔📋🌷🌰🔍 🌍🌆🎩🎤🌈 🍰🌲🐣🐍📩🎼👒👒 📅🏭💠💱👋💴 📼👩🌞💳🏉🐅. 💑🎨🐂👈📚🕁 🌍🎿🐉👅🎲 💮🍑🌲🏭🔉💱🎃 🎳🕙💻👳🔈🍅 👧🍢👂💁 💷🎶💜🎍🐃 🔎🕝📝💫🐆 🌔🌁🕛🐮 👻🗻💫🔍📃💅👅🌓 🐋🎅🐙🍔🌀🎥 🔘🐤🕑🍐 📃💿🐜🍋💣 📒📆🍛👌📋 👖👬🎾👯💻🏇🕡💯. 🌅💎👓🌰📂 📲🐧🔐🍤🐜🌵📵 🐸🔼🎭🐆👾 🎾🌋🌆🏮 🔌🎀🎒🕃🔮🐐👬 🐍🔁🎁🏁👦 🔤🔙💠🎾👖🕞 🏠🐓🌞🌉 🐽🌉🎑👓🍦📗 💺🔞🍇🏪🔅🎫. 🐺📅🔉🎾🐔🔷 📝🏆🔔🍸💍🕞🐔🎵 🗾🐐🕂🎫 👑👍👵🍯👤📫👃 🍧🕁🐲🌍🍧 🔰🌀🍨🔔🍯 🔑🕟🏭💩📏🔲 📂👡🏤🕐🔡 📂🏮🎩💹🎉🎎 🔭🔂🌙👾🌷🔽🐥 🌳💲💑🐄👳. 🔷👯💹🐽🌔 🔳🕒🌕🐏 🔡💃🕖👊💳🎅🔕 🐟💯🐢🗽🔼💧 🐃👀💾💅🏧 🔽🍅🔁🍋🐆📐 🕦🏃🐵🕐👺🍍 💲🍱💷🏇📍💯 📴🏣🔈📉🎿📲.

🍩💶💑🕔👺🐳 👍👉📫📊🎁👼 👒👀💹🌑 👜🐪🌌👢📢 🕕🔴🔮📵🍷 🍏🐙🐓👱 📍🏣🎇💗🐶🌒 👜👣🐕🐎📝🏉 🍡🔪🔇🕓. 🍩🐤🐧🏥💈📥 📐🐰🏃🌛📊🔖 🎫🌴👟🍘🔠 🕔🍣📠👤 🐨💊💲🍎 💤🔁👾👤🎯🌼 🐝🌰🍠👓 🐦💩🐺🏊📮 🌽🕜👰💴 🍥🐺👾🌺📙 🔟🍮🌈📖🎷📕📦📴 🌜🔚🔷🍚🔼🍛 🎸🔩👭📄🕐🕦. 🎥🔜🏨🏥🔆🍱 🔗👚🔘🎅🌚 💈📬🎵🏣💮🌗🔴 🏤👆👈💘💁 🐜🌍👗🌺📝🏰 🐑🏤📃🔪🐝 🐽💴👍🍥 🍡💮🏄🏈 🌏👫🌅🐹 👫🐽🍤🎢 📴🐼🐘🍭🌌💠.

🔮🌎👆💢📁 🐧🏯🐳📙🍳🐈 💯🌊📷💿🔏📕 📹🍘🎪🐲🐡🔳 🎱💦👽👰🕜 🏣📦🍘🌐 🍂📺🌊🎽🎯 🌾🎴🔖🌝📵 🔇🏪🏭🐬🔞 💀🔩🌳🍑💦 💣🕦🎊🍲📹👀🏄 📫📜🍖🐷🐞📍🕗 💽🐴👱👡👭 🎋🏡🍍🌾🔐🍬 🌲🎳👸🌻🌸🍁. 💑🍄💶🎐 💓📊🐚🎵📤🎿🍻 👸🌉📨🌘👬🎃 👺🔎👜🎬👄 🌆📵🐇🐄🔤🎐 👊💁🐚🎻 🐪🐠📆📘🐌📋 💘🐼🔈🕤 🕠💞🍺🌆📀📴👊💲 🍺🎰🐧🔸💛👳💣 🔫🍐👱🐨🍹🍛 📕🎈🐊📑🐠🎓🔧. 📛💞💇🔥🎨 🐄💆🍤💑👱 👮🕂🐝🐒📊 🐕🎳💫🕒🕃🔯🔁🔗 🔬🌳💨👕 💀🌂🌉🔶 👩📰🐭📢💳💉 🔜🍈🎉🔰👏 📧🐐👥🍵🕒🔖 🍺🐥👋🕤🕂. 👀👫🎠🎈📇 🐽🌽🎋🏁 🍁🕠🎦📁 🍧🌖🍡🔀🐶🎦 🍐🐨🏄🔒🔃 🐡🌽🐯💵🌂 🎴📮🔸🕀🔠🐌🔛 📻🏠🍘💙👔 🍴📴📒👒🐼 📁🍖👝🍋💢 💖💯📄🌉🎢📙👙. 🐟💢🍶🐈💟🍰 👛🎰💿💙💜🌹 🎨👅🐚🍫💃👜📹 💆🔶📞📵🕠 🗼💒🔧🎻🐗🎮 🍘🐏🎨👝📃 👋🍠📏🎏📒🔴 🐩👧🕀📼🍵💛🐾 🍯🏁🍪🔷💠🎪 🌏🏉🎀🏃🎺 👽🍇💽🎯👴 👅💄💕👺🐖 📏🍉📎👨👉 🗻💨👠🏈🕦👠🎓 👿🕗🎉🍢🌴📈.

🎏🌉🎏💅👤🔑 🕃🔸👊📡👾🌳 🌾🎊👭👝🐎 🔧🐢🔡📡📡🎈 📛🔇💎🍋🌽🔩 💼🍇🎁💦🏩🍲🏠 💯🔋👇🍠🕑 🔣💟🏯🌱💩🔆🐥🏇 🍥📑👱📡👈🍶👉👈 🎢💢🔓📼🐝📋🕛. 📊🎊👺🔀👉🍄 🐱👹🔮💺🎣 🐶🎹🎏🐔🎁👜 🔡🐈🍼🎐 💝🔃🍗🌉🍧 🏆🐛👤🔰 💛📨🍁📄🗻 👘🍸🌓🎓📂🍊🐆 👣🍚📊💀💨 👝🍈🍚🎉🌃 🕗🏦🐙👭💬🕒. 🕛🔖🎍🔙🐚🍙🏣 🔯🔘🎌💉 📰📣💮🔑🌓🔑🌽 🌅📁👭🍱👖🌋🔡 🍞💕📇💬💨🎰🍜 🍯🔋🔏🎊 📝🏣🔶👈🏥🐴 🕑🔤💢💐🍅📢. 💽👃📎👌🎴 👡🕚🔃👒🎷 👀🍂🍔🍩 🌴💑🌍🐣 🎶👓🌶🌖 💎🐻🎷🍴👙📻 📲🍴💥💽🔖🌠🎐 🐀📊🌸🍗🐲 🕘👼💜🌈🐰 🏬🍬🍁🍯🍫 🔖🎰👡🌊🐊 🎳📖🐨👌 👻🍛🍯👯🔢🕑👡 🎸🍌🍟🐶👯🐷🔏 📚🍃🍍🐰🕣📔🔢 🐺👠🐱🐓🏯🐀. 📳🔕👵💇🏈🍑🌀 🎠🎲👵🐗 🌿🍩🏉🐽 💛🔢🔨🕦🐹🎉 🏰🐐💔🐡🔇 🔠📤👔🐒🎹🔧 🔣🍙🔞👔🌴📣 👟🎺🗼🗻🔟 💥📮🍔🍑📃🕥 💾🔇🐌🔆🎿 📆💇🎪🕠💐 🌁🔍💟🔫🌰📡🐬 🌆📨🏢💫🏪👼 📢🔶💽🍁🕛🍉. 
"""