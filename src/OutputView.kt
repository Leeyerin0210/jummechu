class OutputView {
    fun start() {
        println("점심 메뉴 추천을 시작합니다.")
    }

    fun result(menu: MutableList<MutableList<String>>, coach: MutableList<String>, category: MutableList<Int>) {
        println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]")
        printCategory(category)
        printAllMenu(menu, coach)
        println("추천을 완료했습니다.")
    }

    private fun printCategory(category: MutableList<Int>) {
        print("[ 카테고리")
        for (nowCategory in category) {
            print(" | ${CuisineCategory.getCategoryNameByNumber(nowCategory)}")
        }
        println(" ]")
    }

    private fun printAllMenu(menu: MutableList<MutableList<String>>, coach: MutableList<String>) {
        for (i in 0..coach.size - 1) {
            print("[ ${coach[i]}")
            printMenu(menu[i])
            println(" ]")
        }
    }

    private fun printMenu(menu: MutableList<String>) {
        for (item in menu) {
            print(" | ${item}")
        }
    }
}