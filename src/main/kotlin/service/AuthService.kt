package service

class AuthService {
    private var isAuth: Boolean = false

     fun authentication() {
        while (!isAuth) {
            print("Enter username: ")
            val username = readLine()
            print("Enter password: ")
            val password = readLine()
            if (getAuthentication(username, password)) {
                print("You connected")
                isAuth = true
            } else {
                print("Illegal credential. Please, try again")
            }
            print("\n")
        }
    }

    private fun getAuthentication(username: String?, password: String?): Boolean {
        return username == "test"
    }
}