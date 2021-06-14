package util

class ProductUtil {
    companion object {
        fun getProductName(): String {
            return InputUtil.getString("Enter name: ")
        }

        fun getProductDescription(): String {
            return InputUtil.getString("Enter description: ")
        }

        fun getProductPrice(): Float {
            return InputUtil.getFloat(
                "Enter price: ",
                "Illegal price value. You need to enter number value. Please try again!"
            )
        }
    }
}