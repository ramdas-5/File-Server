document.addEventListener("DOMContentLoaded", function () {
    const uploadForm = document.querySelector("form");

    uploadForm.addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent page reload

        const formData = new FormData(uploadForm);

        fetch("http://localhost:8080/api/files/upload", {
            method: "POST",
            body: formData,
        })
            .then(response => response.text())
            .then(message => {
                showPopup(message); // Show popup with success message
                listFiles(); // Refresh file list
            })
            .catch(error => {
                showPopup("Error uploading file!");
            });
    });
});

function listFiles() {
    fetch("http://localhost:8080/api/files/list")
        .then(response => response.json())
        .then(files => {
            let fileList = document.getElementById("fileList");
            fileList.innerHTML = "";
            files.forEach(file => {
                let li = document.createElement("li");
                li.innerHTML = `
                    <a href="http://localhost:8080/api/files/view/${file}" target="_blank">${file}</a>
                    <button class="delete-btn" onclick="deleteFile('${file}')">Delete</button>
                `;
                fileList.appendChild(li);
            });
        });
}

function deleteFile(filename) {
    if (confirm(`Are you sure you want to delete "${filename}"?`)) {
        fetch(`http://localhost:8080/api/files/delete/${filename}`, { method: "DELETE" })
            .then(response => response.text())
            .then(message => {
                showPopup(message); // Show popup for delete success
                listFiles();
            })
            .catch(error => showPopup("Error deleting file!"));
    }
}

// ✅ Function to Show Popup
function showPopup(message) {
    const popup = document.getElementById("popup");
    const popupMessage = document.getElementById("popupMessage");

    popupMessage.innerText = message;
    popup.style.display = "block";
    popup.style.opacity = "1"; // Make it fully visible

    // Ensure previous timeout is cleared
    if (popup.timeoutId) {
        clearTimeout(popup.timeoutId);
    }

    // Keep the popup visible for 5 seconds
    popup.timeoutId = setTimeout(() => {
        popup.style.opacity = "0"; // Start fade-out
        setTimeout(() => {
            popup.style.display = "none";
        }, 2000); // Remove from DOM after fade-out (2s)
    }, 5000); // Visible for 5 seconds before fade-out starts
}