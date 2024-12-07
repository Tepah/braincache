How to run the code:
1. Clone the repository
2. Run the following command in the terminal from the "backend" folder:
```npm start```
3. Run the app through android studio

Sample usage of RetrofitClient functions:
- loginUser function:
```
    val username = this.loginInput.text
    val password = this.passwordInput.text
    val user = User(null, username = username.toString(), password = password.toString())
    loginUser(user) { id ->
        println("User logged in: $id")
    }
```
- createUserData function:
```
    val username = this.loginInput.text
    val password = this.passwordInput.text
    val user = User(null, username = username.toString(), password = password.toString())
    createUserData(user) { id ->
        println("User created: $id")
    }
```
- createQuiz function:
```
    val quiz = Quiz(null, "Quiz 1", "1", ["Description 1"], ["Answer 1"])
    createQuiz(quiz) { id ->
        println("Quiz created: $id")
    }
```
- getAllQuizzes function:
```
    getAllQuizzes { quizzes ->
        println("Quizzes: $quizzes")
    }
```
