# 🚀 Java File Server (Spring Boot)

## 1️⃣ Introduction  
The **Java File Server** is a simple yet powerful web-based file management system built using **Spring Boot**. It allows users to **upload, view, download, and delete files** through a REST API and a user-friendly frontend interface. This project is ideal for learning about file handling in Java, Spring Boot APIs, and basic frontend development.

---

## 2️⃣ Forward  
This project is designed to help developers understand the core concepts of **file management with Java and Spring Boot**. It provides a practical implementation of **RESTful APIs** for handling file uploads and serves as a foundation for building more complex file storage solutions.

---

## 3️⃣ Acknowledgement  
I would like to extend my sincere gratitude to:
- **Spring Boot Community** for providing excellent documentation and support.
- **Online Resources & Open-Source Contributors** for sharing knowledge and insights.
- **Mentors & Peers** for their guidance and feedback.

---

## 4️⃣ Objective  
The main goal of this project is to:
✅ Provide a **simple and effective** file server using Spring Boot.  
✅ Enable users to **upload, view, download, and delete files** easily.  
✅ Offer a user-friendly **frontend interface** for managing files.  
✅ Help developers learn about **Spring Boot REST APIs** and **file handling in Java**.

---

## 📂 Project Structure
```
file-server/
│── src/
│   ├── main/java/com/example/demo/
│   │   ├── controller/  
│   │   │   ├── FileController.java  # Handles file operations
│   │   │   ├── HomeController.java  # Home page controller
│   │   ├── DemoApplication.java  # Main Spring Boot Application
│   ├── resources/
│   │   ├── application.properties  # Spring Boot configuration
│── frontend/  
│   ├── index.html  # File upload and display page
│   ├── script.js  # Handles UI interactions & API requests
│   ├── styles.css  # Basic styling
│── pom.xml  # Maven dependencies
│── README.md  # Project documentation
```

## 🔧 How to Run the Project
### 1️⃣ **Clone the Repository**
```sh
git clone https://github.com/yourusername/file-server.git
cd file-server
```

### 2️⃣ **Set Up the Backend**
Ensure you have **Java 17+** and **Maven** installed.

```sh
mvn clean install
mvn spring-boot:run
```
> The backend will start on **http://localhost:9090**

### 3️⃣ **Set Up the Frontend**
Open `index.html` in a browser or use a simple local server.

```sh
cd frontend
python -m http.server 5500   # For Python users
```
> The frontend will be available at **http://localhost:5500**

## ⚙️ API Endpoints
| Method  | Endpoint                      | Description              |
|---------|--------------------------------|--------------------------|
| `POST`  | `/api/files/upload`           | Upload a file           |
| `GET`   | `/api/files/list`             | List all uploaded files |
| `GET`   | `/api/files/view/{filename}`  | View a file in the browser |
| `GET`   | `/api/files/download/{filename}` | Download a file    |
| `DELETE`| `/api/files/delete/{filename}` | Delete a file      |

## 🎨 Frontend Features
- **File Upload**: Upload files using an HTML form.
- **List Files**: View uploaded files in a list.
- **View Files**: Open files in the browser.
- **Delete Files**: Remove files directly from the UI.

## 🛠 Built With
- **Java 17**
- **Spring Boot 3**
- **Maven**
- **HTML, CSS, JavaScript**

## 📜 License
This project is open-source. Feel free to modify and use it.

---
💡 **Tip:** Modify `application.properties` to change the **server port** and **file size limits**.
