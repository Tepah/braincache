const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const bcrypt = require('bcrypt');

const app = express();
const port = 3000;

app.use(bodyParser.json());

// MongooseDB Connection
mongoose.connect('mongodb+srv://tepah:passwordfortoday@braincache.o5lki.mongodb.net/?retryWrites=true&w=majority&appName=braincache', { useNewUrlParser: true });

// Schemas
const userSchema = new mongoose.Schema({
    username: {type: String, required: true},
    password: {type: String, required: true}
})

const QuizSchema = new mongoose.Schema({
    title: {type: String, required: true},
    userID: {type: String, required: true},
    questions: {type: [String], required: true},
    answers: {type: [String], required: true},
})

const User = mongoose.model('User', userSchema);
const Quiz = mongoose.model('Quiz', QuizSchema);

// Controllers
// ** User Controllers
const createUser = async (req, res) => {
    try {
        console.log("Creating user...");
        const { username, password } = req.body;
        if (!username || !password) {
            return res.status(400).send('Username and password are required');
        }

        const hashedPassword = await bcrypt.hash(password, 10);

        const existingUser = await User.findOne({ username });
        if (existingUser) {
            return res.status(400).send('User already exists');
        }
        const newUser = new User({ username, password: hashedPassword });
        await newUser.save();
        res.send(newUser);
    } catch (error) {
        console.log("Error creating user...", error);
        res.status(500).send('Error creating user');
    }
}

const loginUser = async (req, res) => {
    try {
        console.log("Logging in...");
        const { username, password } = req.body;
        const user = await User.findOne({ username });
        if (!user) {
            return res.status(404).send('User not found');
        }
        const valid = await bcrypt.compare(password, user.password);
        console.log("Valid: ", valid);
        if (!valid) {
            res.status(401).send('Invalid password');
        } else {
            res.send(user);
        }
    } catch {
        res.status(500).send('Error logging in');
    }
}

const getUser = async (req, res) => {
    try {
        console.log("Getting user..")
        const user = await User.findById(req.params.id, '-password');
        res.json(user);
    } catch {
        res.status(404).send('User not found');
    }
}

// ** Quiz Controllers
const getAllQuizzes = async (req, res) => {
    try {
        console.log("Getting all Quizzes..")
        const quizzes = await Quiz.find();
        res.json(quizzes);
    } catch {
        res.status(500).send('Error fetching quizzes');
    }
}

const getQuizzesByUser = async (req, res) => {
    try {
        console.log("Getting quiz by user..")
        const quizzes = await Quiz.find({ userID: req.params.userID });
        res.json(quizzes);
    } catch {
        res.status(500).send('Error fetching quizzes');
    }
}

const createQuiz = async (req, res) => {
    try {
        console.log("Creating quiz...");
        const { title, userID, questions, answers } = req.body;
        if (!title || !userID || !questions || !answers) {
            console.log("Error creating quiz: Title, userID, questions and answers are required: ",
                title, userID, questions, answers);
            return res.status(400).send('Title, userID, questions and answers are required');
        }

        const newQuiz = new Quiz({ title, userID, questions, answers });
        await newQuiz.save();
        console.log("Quiz created")
        res.send(newQuiz);
    } catch (error) {
        console.log("Error Creating quiz: ", error)
        res.status(500).send('Error creating quiz');
    }
}

const deleteQuiz = async (req, res) => {
    try {
        const quiz = await Quiz.findByIdAndDelete(req.params.id);
        if (!quiz) {
            return res.status(404).send('Quiz not found');
        }
        res.json(quiz);
    } catch {
        res.status(500).send('Error deleting quiz');
    }
}


// Routes
// ** User Routes
app.get('/users/:id', getUser);
app.post('/users', createUser);
app.post('/login', loginUser);

// ** Quiz Routes
app.get('/quizzes', getAllQuizzes);
app.get('/quizzes/:userID', getQuizzesByUser);
app.post('/quizzes', createQuiz);
app.delete('/quizzes/:id', deleteQuiz);

app.listen(port, () => {
    console.log(`Server listening on port ${port}`);
})