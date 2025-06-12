# Java Socket Chat Application (Multi-Client Server)

### 🧑‍💻 Subject: Computer Networks  
### 📁 Project Title: Multi-Client Chat using TCP Sockets in Java

---

## 📄 Project Overview

This project demonstrates how socket programming works in Java. It allows multiple clients to connect to a server and communicate with each other in real-time. Each client runs on a separate thread, and all messages are broadcast to other connected users.

The project uses:

- **TCP/IP sockets**
- **Multithreading**
- **Buffered Streams**
- A custom class `ClientModeller` to manage client info

---

## 📂 File Structure

| File Name            | Description                                      |
|----------------------|--------------------------------------------------|
| `Server.java`        | Main server program to accept and handle clients |
| `Client.java`        | Simple command-line client to connect to server  |
| `ClientModeller.java`| Class to model each client with streams and name |

---

## ⚙️ How It Works

1. Server runs on port `1234`.
2. Clients connect to the server and enter their names.
3. Each client is handled in its own thread.
4. Messages sent by a client are broadcast to all others.

---

## ▶️ How to Run

1. **Compile all files**
   ```bash
   javac Server.java Client.java ClientModeller.java
   ```

2. **Start the server**
   ```bash
   java Server
   ```

3. **Start one or more clients (in separate terminals)**
   ```bash
   java Client
   ```

---

## 💬 Example Output

**Server**
```
Server started on port 1234
New client connected: /127.0.0.1
Alice joined the chat.
Bob joined the chat.
```

**Client 1 (Alice)**
```
Enter your name:
Alice
Bob has joined the chat.
Bob: Hello everyone!
```

**Client 2 (Bob)**
```
Enter your name:
Bob
Alice: Hi Bob!
```

---

## 📘 Concepts Used

- Java Socket API (TCP)
- Multi-threading for concurrent client handling
- Buffered I/O Streams
- Object-oriented design using helper class (`ClientModeller`)

---

## 🔐 Note

This is a basic educational-level project. Advanced features like authentication, encryption, error handling, and GUI are not included but can be added later for improvement.

---

_This project was created for the Computer Networks course to understand how client-server communication works using Java sockets._
