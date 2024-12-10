class InputView {

    fun InputCoach():List<String>{
        println("코치의 이름을 입력해 주세요. (, 로 구분)")
        val input = readln().split(",")
        return input
    }

    fun cantEatInput(coach:String):List<String>{
        println("${coach}(이)가 못 먹는 메뉴를 입력해 주세요")
        val input = readln().split("")
        return input
    }

}