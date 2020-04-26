import com.agnitt.vdt.R
import com.agnitt.vdt.builders.PageBuilder.Companion.pages
import com.agnitt.vdt.models.*
import com.agnitt.vdt.utils.get
import com.agnitt.vdt.utils.uniqueID
import kotlin.random.Random

fun testPageFill() {
    val pMain = Page(
        uniqueID().toLong(),
        "Ключевые факторы",
        Types.CHART,
        mutableListOf(),
        mutableListOf()
    )
    val pOpex =
        Page(uniqueID().toLong(), "ОРЕХ", Types.CHART, mutableListOf(), mutableListOf())
    val pMacro = Page(
        uniqueID().toLong(),
        "Макро и ЦБ",
        Types.CHART,
        mutableListOf(),
        mutableListOf()
    )
    val pEco = Page(
        uniqueID().toLong(),
        "Экосистема",
        Types.CHART,
        mutableListOf(),
        mutableListOf()
    )
    val pSens = Page(
        uniqueID().toLong(),
        "Чувствительность",
        Types.TABLE,
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
        initChart("Чистая прибыль", "млрд.руб", Types.BIG),
        initChart("EPS", "руб", Types.SMALL),
        initChart("ROE", "%", Types.SMALL),
        initChart("COR", "%", Types.SMALL),
        initChart("CIR", "%", Types.SMALL),
        initChart("ЧКД/OPEX", "%", Types.SMALL),
        initChart("CAR Basel III", "%", Types.SMALL)
    )
    fillSidePart(
        initSideItem(
            get(R.string.text_main_factors_seekBar1),
            Types.DISCRETE_SLIDER,
            mutableListOf(45f, 65f)
        ),
        initSideItem(
            get(R.string.text_main_factors_switch1),
            Types.SWITCH,
            mutableListOf(0f, 1f),
            0
        ),
        initSideItem(
            get(R.string.text_main_factors_seekBar2),
            Types.DISCRETE_SLIDER,
            mutableListOf(11f, 15f)
        ),
        initSideItem(
            get(R.string.text_main_factors_seekBar3),
            Types.SWITCH_SLIDER,
            mutableListOf(1.5f, 2f),
            0
        ),
        initSideItem(get(R.string.text_main_factors_popMenuNim), Types.POPUP, mutableListOf()),
        initSideItem(
            get(R.string.text_main_factors_seekBar1),
            Types.POPUP_DISCRETE_SLIDER,
            mutableListOf(45f, 65f)
        ),
        initSideItem(
            get(R.string.text_main_factors_switch1),
            Types.POPUP_SWITCH,
            mutableListOf(0f, 1f),
            0
        )
    )
}

fun Page.initOpex() = this.apply {
    fillMainPart(
        initChart("OPEX", "млрд.руб", Types.BIG),
        initChart("Персонал", "млрд.руб", Types.SMALL),
        initChart("IT", "млрд.руб", Types.SMALL),
        initChart("Недвижимость", "млрд.руб", Types.SMALL),
        initChart("Бизнес-расходы", "млрд.руб", Types.SMALL),
        initChart("Маркетинг", "млрд.руб", Types.SMALL),
        initChart("Численность", "тыс.чел.", Types.SMALL)
    )
    fillSidePart(
        initSideItem(get(R.string.text_opex_popMenu), Types.POPUP, mutableListOf()),
        initSideItem(
            get(R.string.text_opex_seekBar1),
            Types.POPUP_DISCRETE_SLIDER,
            mutableListOf(-5f, 0f),
            5
        ),
        initSideItem(
            get(R.string.text_opex_seekBar2),
            Types.POPUP_DISCRETE_SLIDER,
            mutableListOf(60.3f, 64.3f)
        ),
        initSideItem(
            get(R.string.text_opex_seekBar3),
            Types.POPUP_DISCRETE_SLIDER,
            mutableListOf(4f, 10f),
            3
        )
    )
}

fun Page.initMacro() = this.apply {
    fillMainPart(
        initChart("Чистая прибыль", "млрд.руб", Types.BIG),
        initChart("EPS", "руб", Types.SMALL),
        initChart("ROE", "%", Types.SMALL),
        initChart("COR", "%", Types.SMALL),
        initChart("CIR", "%", Types.SMALL),
        initChart("ЧКД/OPEX", "%", Types.SMALL),
        initChart("CAR Basel III", "%", Types.SMALL)
    )
    fillSidePart(
        initSideItem(get(R.string.text_macro_cb_popMenuUSD), Types.POPUP, mutableListOf()),
        initSideItem(
            get(R.string.text_macro_cb_popMenuUSD),
            Types.POPUP_DISCRETE_SLIDER,
            mutableListOf(60.5f, 80.5f)
        ),
        initSideItem(
            get(R.string.text_macro_cb_popMenuUSD),
            Types.POPUP_DISCRETE_SLIDER,
            mutableListOf(61f, 81f)
        ),
        initSideItem(
            get(R.string.text_macro_cb_popMenuUSD),
            Types.POPUP_DISCRETE_SLIDER,
            mutableListOf(61.5f, 81.5f)
        ),
        initSideItem(
            get(R.string.text_macro_cb_popMenuUSD),
            Types.POPUP_DISCRETE_SLIDER,
            mutableListOf(62f, 82f)
        )
    )
}

fun Page.initEco() = this.apply {
    fillMainPart(
        initChart("Чистая прибыль", "млрд.руб", Types.BIG),
        initChart("EPS", "руб", Types.SMALL),
        initChart("ROE", "%", Types.SMALL),
        initChart("CAR Basel III", "%", Types.SMALL),
        initChart("CIR", "%", Types.SMALL),
        initChart("ЧКД/OPEX", "%", Types.SMALL),
        initChart("Инвест. в Экосистему", "млрд.руб", Types.SMALL)
    )
    fillSidePart(
        initSideItem(get(R.string.text_ecosystem_popMenu), Types.POPUP, mutableListOf()),
        initSideItem(
            get(R.string.text_ecosystem_popMenu),
            Types.POPUP_DISCRETE_SLIDER,
            mutableListOf(27.6f, 133.6f)
        )
    )
}

fun Page.initSens() = this.apply {
    fillMainPart(initTable())
    fillSidePart(
        initSideItem(get(R.string.text_sens_nameMenu), Types.TEXT_VIEW, mutableListOf()),
        initSideItem(
            get(R.string.text_sens_radioGroup1),
            Types.RADIO_GROUP,
            mutableListOf(12.0f, 14.0f, 16.0f, 18.0f),
            2
        ),
        initSideItem(
            get(R.string.text_sens_radioGroup2),
            Types.RADIO_GROUP,
            mutableListOf(4.9f, 5.1f, 5.2f),
            2
        ),
        initSideItem(
            get(R.string.text_sens_radioGroup3),
            Types.RADIO_GROUP,
            mutableListOf(31.1f, 32.1f, 33.1f, 34.1f),
            3
        ),
        initSideItem(
            get(R.string.text_sens_radioGroup4),
            Types.RADIO_GROUP,
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

fun initChart(name: String, measure: String, type: Types) =
    Chart(uniqueID().toLong(), 0, name, measure, type, mutableListOf(), mutableListOf(), 0f)

fun initSideItem(name: String, type: Types, dataList: MutableList<Float>, sectionCount: Int = 4) =
    SideItem(uniqueID().toLong(), 0, name, type, dataList, sectionCount, 0f)

fun initTable() = Table(uniqueID().toLong(), 0, mutableListOf())

val randomMutableListFloat =
    { size: Int, start: Int, end: Int ->
        MutableList(size) {
            Random.nextInt(start, end).toFloat()
        }
    }

var randomFloat = { start: Int, end: Int -> Random.nextInt(start, end).toFloat() }

