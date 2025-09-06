# Livelock Example in Java

[![Java](https://img.shields.io/badge/Java-11%2B-orange?logo=java)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
![Build](https://img.shields.io/badge/build-manual-lightgrey)
[![Docs](https://img.shields.io/badge/docs-Javadoc-green)](./doc/index.html)

An educational repository demonstrating livelocks in Java multithreading. The learning objectives are:

- Understand what a livelock is in concurrent programming.  
- See a minimal reproducible example of two threads competing for resources.

This project is part of the **Concurrent Programming** module at the [Federal University of Rio Grande do Norte (UFRN)](https://www.ufrn.br), Natal, Brazil.

## 📃 Description

This example simulates transfers from one bank account to another. In this example, there is a transfer from *Account-A* to *Account-B* and, concurrently, another transfer from *Account-B* to *Account-A*. Each transaction is performed by a thread. Before doing each operation, the threads attempt to acquire locks over the bank accounts they are operating on.

Remember that a transfer is an atomic transaction consisting of two operations: a withdrawal of an amount from the origin account and the deposit of this amount into the target account. A transfer transaction is considered successful if both withdrawal and deposit operations are completed. Otherwise, the money initially reserved is returned to the original account. In this example (purposely forged for pedagogical objectives), if it is not possible to acquire the lock to deposit into the target account, the lock acquired on the origin account is released, and the entire transaction is restarted.

---

## 📂 Repository Structure

```
.
├── src/                   # Source code
│   ├── BankAccount.java       # Represents a bank account as a shared resource to be locked by threads
│   ├── LivelockExample.java   # Demonstrates a livelock between two threads
└── README.md
```

---

## 🚀 Getting Started

### ✅ Prerequisites
- Java 11+ (works with any modern JDK)
- A terminal or IDE (IntelliJ, Eclipse, VS Code, etc.)

### 🔧 Compilation
Inside the project root, compile all sources:

```bash
javac src/*.java -d out
```

This will place compiled `.class` files inside the `out/` directory.

### ▶️ Running

```bash
java -cp out LivelockExample
```

Expected output:

```
[Transaction-1] reserved 100.00 from Account-A
[Transaction-1] target Account-B is busy; rolling back and retrying
[Transaction-2] reserved 50.00 from Account-B
[Transaction-2] restart transaction to avoid conflict
[Transaction-2] reserved 50.00 from Account-B
[Transaction-2] restart transaction to avoid conflict
[Transaction-1] reserved 100.00 from Account-A
[Transaction-1] restart transaction to avoid conflict
...
```

This represents a livelock, as the operations attempt to run indefinitely, and the program does not terminate.

---

## 🤝 Contributing

Contributions are welcome! You can:
- Extend with other livelock scenarios
- Improve documentation or examples

Fork this repository and submit a pull request 🚀

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).
