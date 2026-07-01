# 🏘️ Neighborhood Help Exchange

A Java-based community platform where neighbors can post help requests or offers, get matched, and support each other. Built with a console-based backend and Java Swing GUI frontend.

---

## 📌 Project Status
> 🚧 In Development — 2-Week Build Challenge

| Week | Focus |
|------|-------|
| Week 1 | Core logic, models, file storage, console features |
| Week 2 | Java Swing GUI, integration, polish |

---

## ✨ Features

- 👤 User Registration & Login
- 📋 Post a Help Request ("I need a tutor")
- 🙋 Post a Help Offer ("I can help with groceries")
- 🔍 Browse listings via GUI
- 🤝 Accept and match with others
- 📁 File-based data storage (no database needed)

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java |
| GUI | Java Swing |
| Storage | File I/O (txt/CSV) |
| IDE | IntelliJ IDEA |
| Version Control | Git & GitHub |

---

## 📁 Folder Structure

```
NeighborhoodHelpExchange/
├── src/
│   ├── main/
│   │   └── Main.java               # Entry point
│   ├── console/
│   │   ├── AuthManager.java        # Login & Registration
│   │   ├── PostManager.java        # Create/view posts
│   │   └── MatchEngine.java        # Matching logic
│   ├── gui/
│   │   ├── HomeScreen.java         # Browse listings
│   │   ├── PostFormScreen.java     # Submit new post
│   │   └── DashboardScreen.java    # My posts & matches
│   ├── models/
│   │   ├── User.java               # User data models
│   │   ├── Post.java               # Post data models
│   │   └── Match.java              # Match data models
│   └── storage/
│       └── FileHandler.java        # Save & load data
├── data/
│   ├── users.txt                   # Stored user data
│   └── posts.txt                   # Stored post data
├── .gitignore
└── README.md
```

---

## 🚀 How to Run

1. Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/NeighborhoodHelpExchange.git
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
| Day 4 | - | Console login & registration |
| Day 5 | - | Post request/offer logic |
| Day 6 | - | Matching engine |
| Day 7 | - | Console testing & bug fixes |
| Day 8 | - | GUI Home Screen |
| Day 9 | - | GUI Post Form |
| Day 10 | - | GUI Dashboard |
| Day 11 | - | Connect GUI to real data |
| Day 12 | - | Accept button & match status |
| Day 13 | - | UI polish |
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

**Your Name**
- GitHub: [haiqakhalil](https://github.com/haiqakhalil)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
