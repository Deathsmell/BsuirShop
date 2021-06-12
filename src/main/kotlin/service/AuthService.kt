package service

class AuthService(
    private val databaseService: DatabaseService
) {
    private var isAuth: Boolean = false

     fun authentication() {
        do {
            print("Enter username: ")
            val username = readLine()
            print("Enter password: ")
            val password = readLine()
            if (getAuthentication(username, password)) {
                println("You connected")
                isAuth = true
            } else {
                println("Illegal credential. Please, try again")
            }
        } while (!isAuth)
    }

    private fun getAuthentication(username: String?, password: String?): Boolean {
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            return false
        }
        return databaseService.createConnection(username, password)
    }
}