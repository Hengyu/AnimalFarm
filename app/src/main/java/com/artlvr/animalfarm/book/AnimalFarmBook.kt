package com.artlvr.animalfarm.book

import com.artlvr.animalfarm.poetry.Poetry
import java.util.GregorianCalendar

class AnimalFarmBook : SyncPoetryProviding {
    companion object {
        fun makePoetry(): Poetry {
            return Poetry(
                name = "21世纪农场",
                sections = listOf(
                    Poetry.Section(
                        title = "《二十一世纪农场》",
                        content = """
                            序：

                            接下来的文字描述的是二十一世纪农场在2019年初至2021年底发生的一系列故事。

                            这些故事将会串成一个童话世界。在这个世界里，只有善意（goodness）、信任（trust）和温暖（love）。祝大家阅读玉快！

                            Ready, Set, Go 🚀
                        """.trimIndent(),
                        date = GregorianCalendar(2022, 3, 16).time
                    ),
                    Poetry.Section(
                        title = "故事的开始",
                        content = """
                            有一家叫21世纪农场的地方，农场主白手起家，通过十年努力，将它越做越大。但是最近遇到了瓶颈，没有增长了。

                            21世纪农场不同于传统农场，动物们其乐融融，得益于在创立之初，农场主设立的四大规则：
                            1. 主人翁意识：农场是全体动物的；
                            2. 使命感精神：多做对农场有益的事情；
                            3. 企业家之心：拥有理想主义和现实主义；
                            4. 探索者身躯：保持探索以及拥抱变化。

                            起初，几乎全体动物都相信这些规则并一直遵守着；农场就这样稳定运行了许多年，直到一只羊的出现。
                        """.trimIndent(),
                        date = GregorianCalendar(2020, 9, 7).time
                    ),
                    Poetry.Section(
                        title = "园长的加入",
                        content = """
                            彼时，21世纪农场增长放缓，一个只有极少数人知道的激进目标看起来实现不了。为此农场主焦头烂额，他亟需一位能将21世纪农场实现再一次增长的领导者。潘牧此时出现了，他与农场主一见如故，第一次攀谈交心从晚上直到第二天中午。

                            牧潘是一只久居澳洲的羊，准确地来说澳籍山羊。在加入21世纪农场之前，他独自一羊创立了羊群草场，打造农场细分领域第一品牌。

                            潘牧 牧潘、牧潘 潘牧，这两个字折磨着我的心扉。按照南洋的习俗，名在前姓在后，那就是牧潘；按照老祖宗的规矩，姓在前名在后，那就是潘牧。还是按照老祖宗的来吧，潘牧！后文简称：PM。
                        """.trimIndent(),
                        date = GregorianCalendar(2020, 9, 7).time
                    ),
                    Poetry.Section(
                        title = "重要的事情",
                        content = """
                            随着农场主入股澳羊草场，潘牧走马上任21世纪农场的主席。第一天召开动物大会，PM 只说了三件事情：1. 增长；2. 增长；3. 还是TM 的增长。小动物们似懂非懂，老动物们将信将疑，而聪明的🐷在一旁👏。
                        """.trimIndent(),
                        date = GregorianCalendar(2020, 9, 8).time
                    ),
                    Poetry.Section(
                        title = "All hands",
                        content = """
                            PM 问还有谁有问题，没有一个动物说话。他停顿了会儿，换了种方式问：“Does anyone have any questions？”此时，一只新来的小鸟站起来说：“咱们要怎么做增长呀？”PM：“我的朋友，这是个很好的问题。时间不多了，我们把这个问题留在下次动物大会说吧。”随着一阵阵👏，第一次动物大会结束。动物们回到各自岗位，继续做着自己的事情。
                        """.trimIndent(),
                        date = GregorianCalendar(2020, 9, 9).time
                    ),
                    Poetry.Section(
                        title = "Επιμηθέας",
                        content = """
                            这些日子 PM 和🐷走的很近，有时候会消失在大伙视野中随后进入茅房商量着什么。大伙觉得很奇怪，PM 作为领导者没有那么透明了，但也不好说什么。这种情况一直持续到第二次全体动物大会的召开。“我朋友们，欢迎各位参加本次动物大会。本次会议要宣布一件事情，下面有请负责农场体验的佩奇来宣布。”PM说完后将话筒递给了佩奇。“谢谢潘牧，接下来由我介绍新版的《动物手册》。”佩奇话音刚落，整个会议不安静了，能明显听到一些动物的窃窃私语。
                        """.trimIndent(),
                        date = GregorianCalendar(2020, 9, 10).time
                    )
                )
            )
        }
    }

    override fun getPoetry(): Poetry = makePoetry()
}
