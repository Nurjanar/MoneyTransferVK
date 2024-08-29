import org.junit.Assert.assertEquals
import org.junit.Test


class MainKtTest {
    @Test
    fun calculateTransferFeeVKPay() {
        val paymentMethod = "VK Pay"
        val monthlyAmount = 35000
        val currentAmount = 4000

        val result = calculateTransferFee(
            paymentMethod, monthlyAmount, currentAmount
        ).toInt()
        assertEquals(0, result)
    }

    @Test
    fun calculateTransferFeeVisa() {
        val paymentMethod = "Visa"
        val monthlyAmount = 0
        val currentAmount = 4000

        val result = calculateTransferFee(
            paymentMethod, monthlyAmount, currentAmount
        ).toInt()
        assertEquals(35, result)
    }

    @Test
    fun calculateTransferFeeMaestro() {
        val paymentMethod = "Maestro"
        val monthlyAmount = 0
        val currentAmount = 80000

        val result = calculateTransferFee(
            paymentMethod, monthlyAmount, currentAmount
        ).toInt()
        assertEquals(50, result)
    }

    @Test
    fun calculateTransferFeeMastercard() {
        val paymentMethod = "Mastercard"
        val monthlyAmount = 0
        val currentAmount = 200

        val result = calculateTransferFee(
            paymentMethod, monthlyAmount, currentAmount
        ).toInt()
        assertEquals(21, result)
    }

    @Test
    fun calculateTransferFeeMup() {
        val paymentMethod = "Мир"
        val monthlyAmount = 30_000
        val currentAmount = 45_000

        val result = calculateTransferFee(
            paymentMethod, monthlyAmount, currentAmount
        ).toInt()
        assertEquals(337, result)
    }

    @Test
    fun calculateTransferFee() {
        val paymentMethod = "Mastercard"
        val monthlyAmount = 35000
        val currentAmount = 40000

        val result = calculateTransferFee(
            paymentMethod, monthlyAmount, currentAmount
        ).toInt()
        assertEquals(0, result)
    }

    @Test
    fun calculatePrice() {
        val paymentMethod = "Maestro"
        val monthlyAmount = 150_000
        val currentAmount = 40000

        val result = calculateTransferFee(
            paymentMethod, monthlyAmount, currentAmount
        ).toInt()
        assertEquals(26, result)
    }
}