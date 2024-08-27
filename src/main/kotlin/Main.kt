import kotlin.math.max

fun main() {
    var monthlyAmount = 0
    var dailyAmount = 0
    val paymentMethods = arrayOf(
        "VK Pay",
        "Мир",
        "Visa",
        "Mastercard",
        "Maestro"
    )
    var result: Boolean
    while (true) {
        println("Введите сумму перевода (в руб.) :")
        val currentAmount = readln().toInt()
        if (currentAmount <= 0) {
            println("Сумма не может быть отрицательной")
            break
        }
        println("Сумма перевода (в руб.) : $currentAmount")

        println("Выберите способ оплаты:")

        for (method in paymentMethods) {
            println("$method\t")
        }
        val paymentMethod = readln()

        if (paymentMethod == "VK Pay") {
            result = checkLimitsVKPay(currentAmount, monthlyAmount)
        } else {
            result = checkLimits(dailyAmount, monthlyAmount, currentAmount)
        }
        if (!result) {
            println("Лимит превышен!")
            break
        }
        val transferFee = calculateTransferFee(
            paymentMethod, monthlyAmount, currentAmount
        ).toInt()
        val totalAmount = currentAmount + transferFee
        println("Комиссия за перевод составит (в руб.): $transferFee")
        println("Итого сумма к оплате (в руб.) : $totalAmount")
        dailyAmount += currentAmount
        monthlyAmount += currentAmount
    }

}

fun checkLimits(dailyAmount: Int, monthlyAmount: Int, currentAmount: Int): Boolean {
    val dailyLimit = 150_000
    val monthlyLimit = 600_000


    if (dailyAmount + currentAmount > dailyLimit ||
        monthlyAmount + currentAmount > monthlyLimit
    ) {
        return false
    } else return true
}

fun checkLimitsVKPay(currentAmount: Int, monthlyAmount: Int): Boolean {
    val VKpayLimit = 15_000
    val VKmonthlyLimit = 40_000
    if (currentAmount > VKpayLimit ||
        monthlyAmount + currentAmount > VKmonthlyLimit) {
        return false
    } else return true
}

fun calculateTransferFee(
    paymentMethod: String,
    monthlyAmount: Int,
    currentAmount: Int
): Double {
    val mastercardFreeLimit = 75000

    val transferFee = when (paymentMethod) {
        "Мир",
        "Visa"
        -> max(currentAmount * 0.0075, 35.0)

        "Mastercard",
        "Maestro"
        -> if (monthlyAmount + currentAmount in 300..75_000) {
            0.0
        } else if (monthlyAmount + currentAmount in 1..299 ||
            monthlyAmount > mastercardFreeLimit
        ) {
            currentAmount * 0.006 + 20
        } else if (monthlyAmount in 0..<mastercardFreeLimit) {
            (currentAmount - (mastercardFreeLimit - monthlyAmount)) * 0.006 + 20
        } else 0.0

        else -> 0.0
    }

    return transferFee
}