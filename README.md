# 🏘️ Neighborhood Help Exchange

A Java-based community platform where neighbors can post help requests or offers, get matched, and support each other. Fully GUI-based using Java Swing — no console interaction needed for the user.

---

## 📌 Project Status
> 🚧 In Development — 2-Week Build Challenge

| Week | Focus |
|------|-------|
| Week 1 | Models, file storage, GUI Login & Register, Post logic |
| Week 2 | Home Screen, Dashboard, Matching, Polish |

---

## ✨ Features

- 👤 GUI-based Registration & Login
- 📋 Post a Help Request ("I need a tutor")
- 🙋 Post a Help Offer ("I can help with groceries")
- 🔍 Browse listings via GUI Home Screen
- 🤝 Accept and match with others
- 📁 File-based data storage (no database needed)

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java |
| GUI | Java Swing |
| Storage | File I/O (txt) |
| IDE | IntelliJ IDEA |
| Version Control | Git & GitHub |

---

## 📁 Folder Structure

```
NeighborhoodHelpExchange/
├── src/
│   ├── main/
│   │   └── Main.java               # Entry point — launches LoginScreen
│   ├── console/
│   │   └── MatchEngine.java        # Matching logic only
│   ├── gui/
│   │   ├── LoginScreen.java        # GUI Login
│   │   ├── RegisterScreen.java     # GUI Register
│   │   ├── HomeScreen.java         # Browse listings
│   │   ├── PostFormScreen.java     # Submit new post
│   │   └── DashboardScreen.java    # My posts & matches
│   ├── models/
│   │   ├── User.java               # User data model
│   │   ├── Post.java               # Post data model
│   │   └── Match.java              # Match data model
│   └── storage/
│       └── FileHandler.java        # Save & load data
├── data/
│   ├── users.txt                   # Stored user data
│   └── posts.txt                   # Stored post data
├── .gitignore
└── README.md
```

---

## 🖥️ App Flow

```
App Starts
    ↓
GUI Login Screen
    ↓
New User? → Register Screen
    ↓
After Login → Home Screen (browse posts)
    ↓
Create Post / View Dashboard / Accept Help
```

---

## 🚀 How to Run

1. Clone the repository
```bash
git clone https://github.com/haiqakhalil/NeighborhoodHelpExchange.git
```

2. Open the project in **IntelliJ IDEA**

3. Run `Main.java` inside `src/main/`

---

## 📅 Daily Progress Log

| Day | Date | What Was Done |
|-----|------|---------------|
| Day 1 | - | Project setup, GitHub repo, folder structure |
| Day 2 | - | User.java and Post.java models |
| Day 3 | - | FileHandler.java — save & load data |
| Day 4 | - | GUI Login Screen |
| Day 5 | - | GUI Register Screen |
| Day 6 | - | GUI Home Screen — browse posts |
| Day 7 | - | GUI Post Form Screen |
| Day 8 | - | GUI Dashboard Screen |
| Day 9 | - | Matching Engine logic |
| Day 10 | - | Connect all screens with real data |
| Day 11 | - | Accept button & match status |
| Day 12 | - | Navigation between screens |
| Day 13 | - | UI polish & bug fixes |
| Day 14 | - | Final testing & README update |

---

## 🔮 Future Scope

- 🗄️ MySQL database integration
- ⭐ Rating system after help is completed
- 📍 Location/area filter
- 💬 In-app chat between matched users
- 📱 Android app version

---

## 👨‍💻 Author

**Haiqa Khalil**
- GitHub: [@haiqakhalil](https://github.com/haiqakhalil)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
