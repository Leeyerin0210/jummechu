
enum class CuisineCategory(val number:Int, val dishes: List<String>) {
    일식(1,listOf("규동", "우동", "미소시루", "스시", "가츠동", "오니기리", "하이라이스", "라멘", "오코노미야끼")),
    한식(2,listOf("김밥", "김치찌개", "쌈밥", "된장찌개", "비빔밥", "칼국수", "불고기", "떡볶이", "제육볶음")),
    중식(3,listOf("깐풍기", "볶음면", "동파육", "짜장면", "짬뽕", "마파두부", "탕수육", "토마토 달걀볶음", "고추잡채")),
    아시안(4,listOf("팟타이", "카오 팟", "나시고렝", "파인애플 볶음밥", "쌀국수", "똠얌꿍", "반미", "월남쌈", "분짜")),
    양식(5,listOf("라자냐", "그라탱", "뇨끼", "끼슈", "프렌치 토스트", "바게트", "스파게티", "피자", "파니니"));
    companion object {
        fun getFoodByCategory(category: Int): List<String> {
            for (value in entries) {
                if (value.number == category) {
                    return value.dishes
                }
            }
            throw (IllegalArgumentException("잘못된 값입니다."))
        }

        fun getCategoryNameByNumber(number:Int):String{
            for (value in entries) {
                if (value.number == number) {
                    return value.name
                }
            }
            throw (IllegalArgumentException("잘못된 값입니다."))
        }
    }
}

class Model {
    private var coach: MutableList<String> = mutableListOf()
    private var coachCantEat: MutableList<List<String>> = mutableListOf()
    var outputView = OutputView()
    private var inputView = InputView()
    private var category: MutableList<Int> = mutableListOf(0,0,0,0,0,0)
    private var menu: MutableList<MutableList<String>> = mutableListOf()


    fun run() {
        outputView.start()
        println("A")
        addCoach()
        println("B")
        selectCategory()
        println("C")
        selectMenuAll()
        println("D")
        outputView.result(menu,coach,category)
    }


    fun addCoach() {
        val coachList = inputView.InputCoach()
        for (coachOne in coachList) {
            coach.add(coachOne)
            coachCantEat.add(inputView.cantEatInput(coachOne))
        }
    }

    fun selectCategory() {
        var returnList: MutableList<Int> = mutableListOf()
        for (i in 0..4) {
            var random = (1..5).random()
            while (checkCategoryNum(random,returnList) >= 2) {
                random = (1..5).random()
            }
            returnList.add(random)
        }
        category = returnList
    }

    private fun checkCategoryNum(categoryNumber: Int,returnList: MutableList<Int>): Int {
        var count = 0
        for (i in 0..returnList.size-1) {
            if (returnList[i] == categoryNumber) {
                count++
            }
        }
        return count
    }

    fun selectMenuAll() {
        for (i in 0..coach.size - 1) {
            selectMenu(i)
        }
    }

    private fun selectMenu(coachNumber:Int){
        var list : MutableList<String> = mutableListOf()
        for(i in 0..4){
            var nowCategory = CuisineCategory.getFoodByCategory(category[i])
            list.add(nowCategory[(0..8).random()])
        }
        if(checkMenu(coachNumber,list)){
            menu.add(list)
            return
        }
        selectMenu(coachNumber)
    }
    private fun checkMenu(coachNumber: Int,list:MutableList<String>):Boolean{
        var countMap :MutableMap<String,Int> = mutableMapOf()
        for(item in list){
            if(!checkNumber(item,countMap)){
                return false
            }
            if(!checkMenu(item,coachNumber)){
                return false
            }
            countMap[item]=1
        }
        return true
    }

    private fun checkMenu(item:String,coachNumber:Int):Boolean{
        for(food in coachCantEat[coachNumber]){
            if(item==food){
                return false
            }
        }
        return true
    }

    private fun checkNumber(item:String,countMap:MutableMap<String,Int>):Boolean{
        if(countMap[item]==null){
            return true
        }
        return false
    }

}