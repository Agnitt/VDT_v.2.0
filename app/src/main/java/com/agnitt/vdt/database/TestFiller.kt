import com.agnitt.vdt.R
import com.agnitt.vdt.builders.PageBuilder.Companion.pages
import com.agnitt.vdt.models.*
import com.agnitt.vdt.utils.get
import com.agnitt.vdt.utils.getUniqueID
import kotlin.random.Random

fun testWithJSON(){

}

fun testPageFill() {
    val pMain = Page(
        getUniqueID().toLong(),
        "Ключевые факторы",
        Types.CHART.name,
        mutableListOf(),
        mutableListOf()
    )
    val pOpex =
        Page(getUniqueID().toLong(), "ОРЕХ", Types.CHART.name, mutableListOf(), mutableListOf())
    val pMacro = Page(
        getUniqueID().toLong(),
        "Макро и ЦБ",
        Types.CHART.name,
        mutableListOf(),
        mutableListOf()
    )
    val pEco = Page(
        getUniqueID().toLong(),
        "Экосистема",
        Types.CHART.name,
        mutableListOf(),
        mutableListOf()
    )
    val pSens = Page(
        getUniqueID().toLong(),
        "Чувствительность",
        Types.TABLE.name,
        mutableListOf(),
        mutableListOf()
    )

    pages.addAll(
        listOf(
            pMain.initMain(),
            pOpex.initOpex(),
            pMacro.initMacro(),
            pEco.initEco(),
            pSens.initSens()
        )
    )


}

fun Page.initMain() = this.apply {
    fillMainPart(
        initChart("Чистая прибыль", "млрд.руб", Types.BIG.name),
        initChart("EPS", "руб", Types.SMALL.name),
        initChart("ROE", "%", Types.SMALL.name),
        initChart("COR", "%", Types.SMALL.name),
        initChart("CIR", "%", Types.SMALL.name),
        initChart("ЧКД/OPEX", "%", Types.SMALL.name),
        initChart("CAR Basel III", "%", Types.SMALL.name)
    )
    fillSidePart(
        initSideItem(
            get(R.string.text_main_factors_seekBar1),
            Types.DISCRETE_SLIDER.name,
            mutableListOf(45f, 65f)
        ),
        initSideItem(
            get(R.string.text_main_factors_switch1),
            Types.SWITCH.name,
            mutableListOf(0f, 1f),
            0
        ),
        initSideItem(
            get(R.string.text_main_factors_seekBar2),
            Types.DISCRETE_SLIDER.name,
            mutableListOf(11f, 15f)
        ),
        initSideItem(
            get(R.string.text_main_factors_seekBar3),
            Types.SWITCH_SLIDER.name,
            mutableListOf(1.5f, 2f),
            0
        ),
        initSideItem(get(R.string.text_main_factors_popMenuNim), Types.POPUP.name, mutableListOf()),
        initSideItem(
            get(R.string.text_main_factors_seekBar1),
            "${Types.POPUP.name}.${Types.DISCRETE_SLIDER.name}",
            mutableListOf(45f, 65f)
        ),
        initSideItem(
            get(R.string.text_main_factors_switch1),
            "${Types.POPUP.name}.${Types.SWITCH.name}",
            mutableListOf(0f, 1f),
            0
        )
    )
}

fun Page.initOpex() = this.apply {
    fillMainPart(
        initChart("OPEX", "млрд.руб", Types.BIG.name),
        initChart("Персонал", "млрд.руб", Types.SMALL.name),
        initChart("IT", "млрд.руб", Types.SMALL.name),
        initChart("Недвижимость", "млрд.руб", Types.SMALL.name),
        initChart("Бизнес-расходы", "млрд.руб", Types.SMALL.name),
        initChart("Маркетинг", "млрд.руб", Types.SMALL.name),
        initChart("Численность", "тыс.чел.", Types.SMALL.name)
    )
    fillSidePart(
        initSideItem(get(R.string.text_opex_popMenu), Types.POPUP.name, mutableListOf()),
        initSideItem(
            get(R.string.text_opex_seekBar1),
            "${Types.POPUP.name}.${Types.DISCRETE_SLIDER.name}",
            mutableListOf(-5f, 0f),
            5
        ),
        initSideItem(
            get(R.string.text_opex_seekBar2),
            "${Types.POPUP.name}.${Types.DISCRETE_SLIDER.name}",
            mutableListOf(60.3f, 64.3f)
        ),
        initSideItem(
            get(R.string.text_opex_seekBar3),
            "${Types.POPUP.name}.${Types.DISCRETE_SLIDER.name}",
            mutableListOf(4f, 10f),
            3
        )
    )
}

fun Page.initMacro() = this.apply {
    fillMainPart(
        initChart("Чистая прибыль", "млрд.руб", Types.BIG.name),
        initChart("EPS", "руб", Types.SMALL.name),
        initChart("ROE", "%", Types.SMALL.name),
        initChart("COR", "%", Types.SMALL.name),
        initChart("CIR", "%", Types.SMALL.name),
        initChart("ЧКД/OPEX", "%", Types.SMALL.name),
        initChart("CAR Basel III", "%", Types.SMALL.name)
    )
    fillSidePart(
        initSideItem(get(R.string.text_macro_cb_popMenuUSD), Types.POPUP.name, mutableListOf()),
        initSideItem(
            get(R.string.text_macro_cb_popMenuUSD),
            "${Types.POPUP.name}.${Types.DISCRETE_SLIDER.name}",
            mutableListOf(60.5f, 80.5f)
        ),
        initSideItem(
            get(R.string.text_macro_cb_popMenuUSD),
            "${Types.POPUP.name}.${Types.DISCRETE_SLIDER.name}",
            mutableListOf(61f, 81f)
        ),
        initSideItem(
            get(R.string.text_macro_cb_popMenuUSD),
            "${Types.POPUP.name}.${Types.DISCRETE_SLIDER.name}",
            mutableListOf(61.5f, 81.5f)
        ),
        initSideItem(
            get(R.string.text_macro_cb_popMenuUSD),
            "${Types.POPUP.name}.${Types.DISCRETE_SLIDER.name}",
            mutableListOf(62f, 82f)
        )
    )
}

fun Page.initEco() = this.apply {
    fillMainPart(
        initChart("Чистая прибыль", "млрд.руб", Types.BIG.name),
        initChart("EPS", "руб", Types.SMALL.name),
        initChart("ROE", "%", Types.SMALL.name),
        initChart("CAR Basel III", "%", Types.SMALL.name),
        initChart("CIR", "%", Types.SMALL.name),
        initChart("ЧКД/OPEX", "%", Types.SMALL.name),
        initChart("Инвест. в Экосистему", "млрд.руб", Types.SMALL.name)
    )
    fillSidePart(
        initSideItem(get(R.string.text_ecosystem_popMenu), Types.POPUP.name, mutableListOf()),
        initSideItem(
            get(R.string.text_ecosystem_popMenu),
            "${Types.POPUP.name}.${Types.DISCRETE_SLIDER.name}",
            mutableListOf(27.6f, 133.6f)
        )
    )
}

fun Page.initSens() = this.apply {
    fillMainPart(initTable())
    fillSidePart(
        initSideItem(get(R.string.text_sens_nameMenu), Types.TEXT_VIEW.name, mutableListOf()),
        initSideItem(
            get(R.string.text_sens_radioGroup1),
            Types.RADIO_GROUP.name,
            mutableListOf(12.0f, 14.0f, 16.0f, 18.0f),
            2
        ),
        initSideItem(
            get(R.string.text_sens_radioGroup2),
            Types.RADIO_GROUP.name,
            mutableListOf(4.9f, 5.1f, 5.2f),
            2
        ),
        initSideItem(
            get(R.string.text_sens_radioGroup3),
            Types.RADIO_GROUP.name,
            mutableListOf(31.1f, 32.1f, 33.1f, 34.1f),
            3
        ),
        initSideItem(
            get(R.string.text_sens_radioGroup4),
            Types.RADIO_GROUP.name,
            mutableListOf(1.0f, 1.1f, 1.2f, 1.3f, 1.4f),
            3
        )
    )
}

fun Page.fillMainPart(vararg items: MainItem): Page? {
    val ownerId = this.id
    val mainItems = mutableListOf<MainItem>()
    items.forEach {
        it.ownerId = ownerId
        when (it) {
            is Chart -> it.apply {
                it.basicDataList = randomMutableListFloat(5, 20, 132)
                it.modelDataList =
                    (it.basicDataList.map { num -> num + randomFloat(10, 20) }).toMutableList()
                it.strategyData = randomFloat(30, 140)
            }
            is Table -> it.dataList = randomMutableListFloat(12 * 20, 983, 1012)
            else -> return@forEach
        }
        mainItems.add(it)
    }
    this.mainItems = mainItems
    return this
}

fun Page.fillSidePart(vararg items: SideItem) {
    val ownerId = this.id
    val sideItems = mutableListOf<SideItem>()
    items.forEach {
        it.ownerId = ownerId
        it.currentValue = if (it.dataList.isNotEmpty()) it.dataList[0] else 0f
        sideItems.add(it)
    }
    this.sideItems = sideItems
}

fun initChart(name: String, measure: String, type: String) =
    Chart(getUniqueID().toLong(), 0, name, measure, type, mutableListOf(), mutableListOf(), 0f)

fun initSideItem(name: String, type: String, dataList: MutableList<Float>, sectionCount: Int = 4) =
    SideItem(getUniqueID().toLong(), 0, name, type, dataList, sectionCount, 0f)

fun initTable() = Table(getUniqueID().toLong(), 0, mutableListOf())

val randomMutableListFloat =
    { size: Int, start: Int, end: Int ->
        MutableList(size) {
            Random.nextInt(start, end).toFloat()
        }
    }

var randomFloat = { start: Int, end: Int -> Random.nextInt(start, end).toFloat() }

