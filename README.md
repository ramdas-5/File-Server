# 🚀 Java File Server (Spring Boot)
A simple file server using **Spring Boot** that allows users to **upload, view, download, and delete files** via a web interface.

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
