# 🧑‍💼 Employee Directory API (Backend)

This is the backend part of the Employee Directory App built with **Java Spring Boot**.  
It provides a RESTful API to serve employee data to the frontend.

---

## 🚀 Features

- Exposes `/api/employees` endpoint to get a list of employees
- Structured with Java Spring Boot (using Maven)
- Easily integrable with any frontend via HTTP

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- MYSQL (configurable)
- Maven

---

## 📦 Getting Started

### Prerequisites

- Java 17+
- Maven

### Run the backend

```bash
cd backend
./mvnw spring-boot:run
```

Backend will be available at: `http://localhost:7896`

---

## 📡 API Endpoint

**GET** `/api/employees`

```json
[
  {
 
        "id": 9,
        "firstName": "Frank",
        "lastName": "Bader",
        "email": "frankbader@gmail.com",
        "department": "IT",
        "phoneNumber": "176 200 300 50",
        "image": "/9j/4AAQSvxvxvxkZJRgABsöldsxrtzutzvxAQAAAQABAAD",
  },
  {
        "id": 10,
        "firstName": "Ellen ",
        "lastName": "Nimser",
        "email": "Ellen_Nims@gmail.com",
        "department": "sales",
        "phoneNumber": "0176 500 800 70",
        "image": "/9j/sfssdfr4AsdfAQSkdsdfsdZJRgABAQAAAQABAAD",
  }
]
```

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.